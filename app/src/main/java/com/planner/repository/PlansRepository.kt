package com.planner.repository

import com.planner.domain.model.DayPlans
import com.planner.domain.model.Plan

interface PlansRepository {
    suspend fun setNewDayPlansInDB(dayPlans: DayPlans)

    suspend fun getAllDaysPlansFromDB(): List<DayPlans>?

    suspend fun updateDayPlansInDB(dayPlans: DayPlans)

    suspend fun updateSpecialPlanInSpecialDay(dayPlans: DayPlans, plan: Plan, index: Int)

    suspend fun deleteDayPlansFromDB(dayPlans: DayPlans)

    suspend fun getSpecialDayPlansFromDB(yearSecond: Int, monthSecond: Int, daySecond: Int): List<DayPlans>?
}