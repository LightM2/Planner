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
    private val dayPlansRepository: PlansRepository,
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
    var counter = 0

    var currentDayPlans: DayPlans = DayPlans(0, 0, 0, 0)

    val weeksList: MutableState<List<List<DayPlans>>> = mutableStateOf(listOf())
    val loading: MutableState<Boolean> = mutableStateOf(false)

    private var weeksListScrollPosition = 0


    init {
        getToday()
    }

    fun onChangeWeeksScrollPosition(position: Int) {
        weeksListScrollPosition = position
    }

    private fun appendWeeksList(weekList: List<DayPlans>) {
        val current = ArrayList(weeksList.value)
        //current.remove(current[0])
        current.add(weekList)
        weeksList.value = current

    }

    fun nextWeek() {
        viewModelScope.launch {
            loading.value = true
            if ((weeksListScrollPosition + 1) >= counter) {
                val result = currentDayPlans.getNextWeekList()
                currentDayPlans = currentDayPlans.getSomeDay(7)
                counter++
                appendWeeksList(result)
                onChangeWeeksScrollPosition(0)
            }
            loading.value = false
        }
    }

    private fun getToday() {
        viewModelScope.launch {
            val rightNow = Calendar.getInstance()
            Log.d(TAG, "rightNow - ${rightNow.time}")

            year = rightNow.get(Calendar.YEAR)
            month = rightNow.get(Calendar.MONTH)
            dayOfWeek = rightNow.get(Calendar.DAY_OF_WEEK)
            day = rightNow.get(Calendar.DAY_OF_MONTH)
            monthName = DateFormatSymbols().months[month]
            dayOfWeekName = DateFormatSymbols().weekdays[dayOfWeek]

            currentDayPlans = DayPlans(year, month, dayOfWeek, day)
            today = DayPlans(year, month, dayOfWeek, day)
            counter++

            weeksList.value = listOf(
                currentDayPlans.getThisWeekList(),
            )

            Log.d(
                TAG, "year - $year, month - $monthName, day of week - $dayOfWeekName, day - $day"
            )
        }

    }

}