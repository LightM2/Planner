package com.planner.room

import androidx.room.*
import com.planner.room.model.DayPlansEntity

@Dao
interface DayPlansDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dayPlansEntity: DayPlansEntity): Long

    @Query("SELECT * FROM dayPlansTN")
    suspend fun get(): List<DayPlansEntity>

    @Query("SELECT * FROM dayPlansTN WHERE year = :yearSecond AND month = :monthSecond AND day = :daySecond")
    suspend fun getSpecialDayPlans(
        yearSecond: Int,
        monthSecond: Int,
        daySecond: Int
    ): List<DayPlansEntity>

    @Update
    suspend fun updateDayPlans(dayPlansEntity: DayPlansEntity)

    @Delete
    suspend fun delete(dayPlansEntity: DayPlansEntity)


}