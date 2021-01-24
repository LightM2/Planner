package com.planner.repository

import android.util.Log
import com.planner.room.DayPlansDao
import com.planner.room.model.DayPlans

class PlansRepositoryImpl(
    private val dayPlansDao: DayPlansDao
) : PlansRepository {
    override suspend fun setNewDayPlansInDB(dayPlans: DayPlans) {
        try {
            dayPlansDao.insert(dayPlans)
            Log.d("Room setNewDayPlansInDB", "Success")

        } catch (e: Exception) {
            Log.d("Room setNewDayPlansInDB", "Exception $e")
        }

    }

    override suspend fun getAllDaysPlansFromDB(): List<DayPlans>? {
        var result: List<DayPlans>? = null
        try {
            result = dayPlansDao.get()
            Log.d("Room getAllDaysPlansFromDB", "Success")

        } catch (e: Exception) {
            Log.d("Room getAllDaysPlansFromDB", "Exception $e")
        }
        return result
    }

    /*override suspend fun getSpecialDayPlansFromDB(
        year: Int,
        month: Int,
        dayOfWeek: Int,
        day: Int
    ): List<DayPlans>? {
        var result: List<DayPlans>? = null
        try {
            result = dayPlansEntityMapper.toDomainList(
                dayPlansDao.getSpecialDayPlans(year, month, dayOfWeek, day)
            )
            Log.d("Room getSpecialDayPlansFromDB", "Success")

        } catch (e: Exception) {
            Log.d("Room getSpecialDayPlansFromDB", "Exception $e")
        }
        return result
    }*/

    override suspend fun updateDayPlansInDB(dayPlans: DayPlans) {
        try {
            dayPlansDao.updateDrinkCategories(dayPlans)
            Log.d("Room updateDayPlansInDB", "Success")

        } catch (e: Exception) {
            Log.d("Room updateDayPlansInDB", "Exception $e")
        }
    }

    override suspend fun deleteDayPlansFromDB(dayPlans: DayPlans) {
        try {
            dayPlansDao.delete(dayPlans)
            Log.d("Room deleteDayPlansFromDB", "Success")

        } catch (e: Exception) {
            Log.d("Room deleteDayPlansFromDB", "Exception $e")
        }
    }

}