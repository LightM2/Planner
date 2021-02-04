package com.planner.repository

import com.planner.domain.model.DayPlans

interface PlansRepository {
    suspend fun setNewDayPlansInDB(dayPlans: DayPlans)

    suspend fun getAllDaysPlansFromDB(): List<DayPlans>?

    suspend fun updateDayPlansInDB(dayPlans: DayPlans)

    suspend fun deleteDayPlansFromDB(dayPlans: DayPlans)
}