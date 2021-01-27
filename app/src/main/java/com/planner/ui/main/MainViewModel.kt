package com.planner.ui.main

import android.icu.util.Calendar
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.planner.repository.PlansRepository
import com.planner.room.model.DayInformation
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.DateFormatSymbols
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val recipeRepository: PlansRepository,
    private val state: SavedStateHandle,
) : ViewModel() {

    private val TAG = "MainViewModel"

    private var year: Int = 0
    private var month: Int = 0
    private var monthName: String = ""
    private var dayOfWeek: Int = 0
    private var dayOfWeekName: String = ""
    private var day: Int = 0

    lateinit var currentDayInformation: DayInformation


    fun getCurrentDate() {
        val rightNow = Calendar.getInstance()
        Log.d(TAG, "rightNow - ${rightNow.time}")

        year = rightNow.get(Calendar.YEAR)
        month = rightNow.get(Calendar.MONTH)
        dayOfWeek = rightNow.get(Calendar.DAY_OF_WEEK)
        day = rightNow.get(Calendar.DAY_OF_MONTH)
        monthName = DateFormatSymbols().months[month]
        dayOfWeekName = DateFormatSymbols().weekdays[dayOfWeek]

        currentDayInformation = DayInformation(year, month, dayOfWeek, day)

        Log.d(TAG, "year - $year, month - $month, day of week - $dayOfWeek, day - $day")
        Log.d(
            TAG, "year - $year, month - $monthName, day of week - $dayOfWeekName, day - $day"
        )

    }

}