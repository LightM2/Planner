package com.planner.room.model

import android.icu.util.Calendar
import android.util.Log
import java.text.DateFormatSymbols


data class DayInformation(
    val year: Int,
    val month: Int, //January = 0
    val dayOfWeek: Int,  //Sunday = 1
    val day: Int
){
    fun getMonthName(): String = DateFormatSymbols().months[month]

    fun getDayOfWeekName(): String = DateFormatSymbols().weekdays[dayOfWeek]

    fun getNextDay(): DayInformation = getSomeDay(1)

    fun getPreviousDay(): DayInformation = getSomeDay(-1)

    fun getSomeDay(numberOfDayFromCurrent: Int): DayInformation{
        val rightNow = Calendar.getInstance()
        rightNow.set(year, month, day)
        rightNow.add(Calendar.DATE, numberOfDayFromCurrent)
        Log.d(
            "getPreviousDay", "year - ${rightNow.get(Calendar.YEAR)}, " +
                    "month - ${rightNow.get(Calendar.MONTH)}, " +
                    "day of week - ${rightNow.get(Calendar.DAY_OF_WEEK)}, " +
                    "day - ${rightNow.get(Calendar.DAY_OF_MONTH)}"
        )
        return DayInformation(rightNow.get(Calendar.YEAR), rightNow.get(Calendar.MONTH),
            rightNow.get(Calendar.DAY_OF_WEEK), rightNow.get(Calendar.DAY_OF_MONTH))
    }

    fun getPreviousWeekList(): MutableList<DayInformation>{
        val previousDayOfThisWeekDay = getSomeDay(-7)
        return previousDayOfThisWeekDay.getThisWeekList()
    }

    fun getNextWeekList(): MutableList<DayInformation>{
        val nextDayOfThisWeekDay = getSomeDay(7)
        return nextDayOfThisWeekDay.getThisWeekList()
    }

    fun getThisWeekList(): MutableList<DayInformation> {
        val weekList: MutableList<DayInformation> = mutableListOf()

        when (dayOfWeek) {
            1 -> {
                weekList.add(this)
                for (i in 0..5) {
                    weekList.add(weekList[i].getNextDay())
                }
            }
            7 -> {
                weekList.add(this)
                for (i in 5 downTo 0) {
                    weekList.add(0, weekList[0].getPreviousDay())
                }
            }
            else -> {
                weekList.add(this)
                for (i in (dayOfWeek - 2) downTo 0) {
                    weekList.add(0, weekList[0].getPreviousDay())
                }
                for (j in dayOfWeek..6){
                    weekList.add(weekList[j-1].getNextDay())
                }

            }
        }

        Log.d("getWeekList", "$weekList")

        return weekList
    }
}