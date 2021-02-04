package com.planner.repository

import android.util.Log
import com.planner.domain.model.DayPlans
import com.planner.room.DayPlansDao
import com.planner.room.model.DayPlansMapperEntity

class PlansRepositoryImpl(
    private val dayPlansDao: DayPlansDao,
    private val dayPlansMapperEntity: DayPlansMapperEntity
) : PlansRepository {
    override suspend fun setNewDayPlansInDB(dayPlans: DayPlans) {
        try {
            dayPlansDao.insert(dayPlansMapperEntity.mapFromDomainModel(dayPlans))
            Log.d("Room setNewDayPlansInDB", "Success")

        } catch (e: Exception) {
            Log.d("Room setNewDayPlansInDB", "Exception $e")
        }

    }

    override suspend fun getAllDaysPlansFromDB(): List<DayPlans>? {
        var result: List<DayPlans>? = null
        try {
            result = dayPlansMapperEntity.toDomainList(dayPlansDao.get())
            Log.d("Room getAllDaysPlansFromDB", "Success")

        } catch (e: Exception) {
            Log.d("Room getAllDaysPlansFromDB", "Exception $e")
        }
        return result
    }


    override suspend fun updateDayPlansInDB(dayPlans: DayPlans) {
        try {
            dayPlansDao.updateDrinkCategories(dayPlansMapperEntity.mapFromDomainModel(dayPlans))
            Log.d("Room updateDayPlansInDB", "Success")

        } catch (e: Exception) {
            Log.d("Room updateDayPlansInDB", "Exception $e")
        }
    }

    override suspend fun deleteDayPlansFromDB(dayPlans: DayPlans) {
        try {
            dayPlansDao.delete(dayPlansMapperEntity.mapFromDomainModel(dayPlans))
            Log.d("Room deleteDayPlansFromDB", "Success")

        } catch (e: Exception) {
            Log.d("Room deleteDayPlansFromDB", "Exception $e")
        }
    }

}