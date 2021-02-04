package com.planner.room

import androidx.room.*
import com.planner.room.model.DayPlansEntity

@Dao
interface DayPlansDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dayPlansEntity: DayPlansEntity): Long

    @Query("SELECT * FROM dayPlansTN")
    suspend fun get(): List<DayPlansEntity>

    /*@Query("SELECT * FROM dayPlansEntityTN WHERE year = year AND month = month AND dayOfWeek = dayOfWeek AND day = day")
    suspend fun getSpecialDayPlans(year: Int, month: Int, dayOfWeek: Int, day: Int): List<DayPlansEntity>*/

    @Update
    suspend fun updateDrinkCategories(dayPlansEntity: DayPlansEntity)

    @Delete
    suspend fun delete(dayPlansEntity: DayPlansEntity)


}