package com.planner.repository

import com.planner.room.model.DayPlans

interface PlansRepository {
    suspend fun setNewDayPlansInDB(dayPlans: DayPlans)

    suspend fun getAllDaysPlansFromDB(): List<DayPlans>?

    /*suspend fun getSpecialDayPlansFromDB(year: Int, month: Int, dayOfWeek: Int, day: Int): List<DayPlans>?*/

    suspend fun updateDayPlansInDB(dayPlans: DayPlans)

    suspend fun deleteDayPlansFromDB(dayPlans: DayPlans)
}