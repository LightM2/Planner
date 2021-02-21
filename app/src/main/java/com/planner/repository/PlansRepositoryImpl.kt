package com.planner.repository

import android.util.Log
import com.planner.domain.model.DayPlans
import com.planner.domain.model.Plan
import com.planner.room.DayPlansDao
import com.planner.room.model.DayPlansMapperEntity

class PlansRepositoryImpl(
    private val dayPlansDao: DayPlansDao,
    private val dayPlansMapperEntity: DayPlansMapperEntity
) : PlansRepository {
    private val TAG = "PlansRepository"

    override suspend fun setNewDayPlansInDB(dayPlans: DayPlans) {
        try {
            dayPlansDao.insert(dayPlansMapperEntity.mapFromDomainModel(dayPlans))
            Log.d(TAG, "setNewDayPlansInDB Success")

        } catch (e: Exception) {
            Log.d(TAG, "setNewDayPlansInDB Exception $e")
        }

    }

    override suspend fun getAllDaysPlansFromDB(): List<DayPlans>? {
        var result: List<DayPlans>? = null
        try {
            result = dayPlansMapperEntity.toDomainList(dayPlansDao.get())
            Log.d(TAG, "getAllDaysPlansFromDB Success")

        } catch (e: Exception) {
            Log.d(TAG, "getAllDaysPlansFromDB Exception $e")
        }
        return result
    }


    override suspend fun updateDayPlansInDB(dayPlans: DayPlans) {
        try {
            dayPlansDao.updateDayPlans(dayPlansMapperEntity.mapFromDomainModel(dayPlans))
            Log.d(TAG, "updateDayPlansInDB Success")

        } catch (e: Exception) {
            Log.d(TAG, "updateDayPlansInDB Exception $e")
        }
    }

    override suspend fun updateSpecialPlanInSpecialDay(dayPlans: DayPlans, plan: Plan, index: Int) {
        try {
            if (!dayPlans.plans.isNullOrEmpty()){
                Log.d(TAG, "updateSpecialPlanInSpecialDay dayPlan.plans " +
                        "${dayPlans.plans!![index].newPlan} - ${dayPlans.plans!![index].done}")
                dayPlans.plans!![index] = plan
                Log.d(TAG, "updateSpecialPlanInSpecialDay dayPlan.plans " +
                        "${dayPlans.plans!![index].newPlan} - ${dayPlans.plans!![index].done}")
                updateDayPlansInDB(dayPlans)
                Log.d(TAG, "updateSpecialPlanInSpecialDay Success")
            }

        } catch (e: Exception) {
            Log.d(TAG, "updateSpecialPlanInSpecialDay Exception $e")
        }
    }

    override suspend fun deleteDayPlansFromDB(dayPlans: DayPlans) {
        try {
            dayPlansDao.delete(dayPlansMapperEntity.mapFromDomainModel(dayPlans))
            Log.d(TAG, "deleteDayPlansFromDB Success")

        } catch (e: Exception) {
            Log.d(TAG, "deleteDayPlansFromDB Exception $e")
        }
    }

    override suspend fun deleteSpecialPlanInSpecialDay(dayPlans: DayPlans, plan: Plan) {
        try {
            if (!dayPlans.plans.isNullOrEmpty()){
                if (dayPlans.plans!!.size == 1){
                    deleteDayPlansFromDB(dayPlans)
                }else {
                    dayPlans.plans!!.remove(plan)
                    updateDayPlansInDB(dayPlans)
                }

                Log.d(TAG, "deleteSpecialPlanInSpecialDay Success")
            }

        } catch (e: Exception) {
            Log.d(TAG, "deleteSpecialPlanInSpecialDay Exception $e")
        }
    }

    override suspend fun getSpecialDayPlansFromDB(
        yearSecond: Int,
        monthSecond: Int,
        daySecond: Int
    ): List<DayPlans>? {
        var result: List<DayPlans>? = null
        try {
            val listOfDayPlans = dayPlansMapperEntity.toDomainList(
                dayPlansDao.getSpecialDayPlans(
                    yearSecond,
                    monthSecond,
                    daySecond
                )
            )
            if (listOfDayPlans.isNotEmpty()){
                result = listOfDayPlans
            }
            Log.d(TAG, "getSpecialDayPlansFromDB Success")

        } catch (e: Exception) {
            Log.d(TAG, "getSpecialDayPlansFromDB Exception $e")
        }
        return result
    }

    override suspend fun getDayPlansUsingIDFromDB(id: Int): DayPlans? {
        var result: DayPlans? = null
        try {
            result = dayPlansMapperEntity.mapToDomainModel(dayPlansDao.getDayPlansUsingID(id))
            Log.d(TAG, "getDayPlansUsingIDFromDB Success")

        } catch (e: Exception) {
            Log.d(TAG, "getDayPlansUsingIDFromDB Exception $e")
        }
        return result
    }

}