package com.planner.ui.main

import android.icu.util.Calendar
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.planner.repository.PlansRepository
import com.planner.domain.model.DayPlans
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.DateFormatSymbols
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val mainRepository: PlansRepository,
    private val state: SavedStateHandle,
) : ViewModel() {

    private val TAG = "MainViewModel"

    private var year: Int = 0
    private var month: Int = 0
    private var monthName: String = ""
    private var dayOfWeek: Int = 0
    private var dayOfWeekName: String = ""
    private var day: Int = 0

    lateinit var today: DayPlans

    var currentDayPlans: DayPlans = DayPlans(0, 0, 0, 0)

    val week: MutableState<List<DayPlans>> = mutableStateOf(listOf())

    private val loading: MutableState<Boolean> = mutableStateOf(false)


    fun getPreviousWeek() {
        viewModelScope.launch {
            loading.value = true
            updateWeekIfDBContainsCurrentDays(currentDayPlans.getPreviousWeekList())
            currentDayPlans = currentDayPlans.getSomeDay(-7)
            loading.value = false
        }
    }

    fun getThisWeek() {
        viewModelScope.launch {
            loading.value = true
            updateWeekIfDBContainsCurrentDays(today.getThisWeekList())
            currentDayPlans = today
            loading.value = false
        }
    }


    fun getNextWeek() {
        viewModelScope.launch {
            loading.value = true
            updateWeekIfDBContainsCurrentDays(currentDayPlans.getNextWeekList())
            currentDayPlans = currentDayPlans.getSomeDay(7)
            loading.value = false
        }
    }

    fun getToday() {
        viewModelScope.launch {
            val rightNow = Calendar.getInstance()

            year = rightNow.get(Calendar.YEAR)
            month = rightNow.get(Calendar.MONTH)
            dayOfWeek = rightNow.get(Calendar.DAY_OF_WEEK)
            day = rightNow.get(Calendar.DAY_OF_MONTH)
            monthName = DateFormatSymbols().months[month]
            dayOfWeekName = DateFormatSymbols().weekdays[dayOfWeek]

            today = DayPlans(year, month, dayOfWeek, day)
            getThisWeek()

            Log.d(
                TAG, "year - $year, month - $monthName, day of week - $dayOfWeekName, day - $day"
            )
        }

    }

    private fun updateWeekIfDBContainsCurrentDays(someWeek: MutableList<DayPlans>) {
        viewModelScope.launch {
            someWeek.forEach { dayPlans ->
                mainRepository.getSpecialDayPlansFromDB(
                    yearSecond = dayPlans.year,
                    monthSecond = dayPlans.month,
                    daySecond = dayPlans.day
                )?.let { dayPlans.updateDayPlan(it[0]) }
            }
            week.value = someWeek
        }

    }


}