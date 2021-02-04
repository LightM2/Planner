package com.planner.domain.model

import android.icu.util.Calendar
import android.util.Log
import java.text.DateFormatSymbols


data class DayPlans(
    val year: Int,
    val month: Int, //January = 0
    val dayOfWeek: Int,  //Sunday = 1
    val day: Int,
    val id:Int = 0,
    var plans: MutableList<Plan>? = null
){
    fun getMonthName(): String = DateFormatSymbols().months[month]

    fun getDayOfWeekName(): String = DateFormatSymbols().weekdays[dayOfWeek]

    fun getNextDay(): DayPlans = getSomeDay(1)

    fun getPreviousDay(): DayPlans = getSomeDay(-1)

    fun getSomeDay(numberOfDayFromCurrent: Int): DayPlans {
        val rightNow = Calendar.getInstance()
        rightNow.set(year, month, day)
        rightNow.add(Calendar.DATE, numberOfDayFromCurrent)
        Log.d(
            "getPreviousDay", "year - ${rightNow.get(Calendar.YEAR)}, " +
                    "month - ${rightNow.get(Calendar.MONTH)}, " +
                    "day of week - ${rightNow.get(Calendar.DAY_OF_WEEK)}, " +
                    "day - ${rightNow.get(Calendar.DAY_OF_MONTH)}"
        )
        return DayPlans(rightNow.get(Calendar.YEAR), rightNow.get(Calendar.MONTH),
            rightNow.get(Calendar.DAY_OF_WEEK), rightNow.get(Calendar.DAY_OF_MONTH))
    }

    fun getPreviousWeekList(): MutableList<DayPlans>{
        val previousDayOfThisWeekDay = getSomeDay(-7)
        return previousDayOfThisWeekDay.getThisWeekList()
    }

    fun getNextWeekList(): MutableList<DayPlans>{
        val nextDayOfThisWeekDay = getSomeDay(7)
        return nextDayOfThisWeekDay.getThisWeekList()
    }

    fun getThisWeekList(): MutableList<DayPlans> {
        val weekList: MutableList<DayPlans> = mutableListOf()

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

    fun countDonePlanes(): Int{
        var donePlans: Int = 0
        if (plans != null){
            donePlans = plans!!.count { it.done }
        }
        return donePlans
    }
}