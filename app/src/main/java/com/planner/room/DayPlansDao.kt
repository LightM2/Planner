package com.planner.room

import androidx.room.*
import com.planner.room.model.DayPlans

@Dao
interface DayPlansDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dayPlans: DayPlans): Long

    @Query("SELECT * FROM dayPlansTN")
    suspend fun get(): List<DayPlans>

    /*@Query("SELECT * FROM dayPlansEntityTN WHERE year = year AND month = month AND dayOfWeek = dayOfWeek AND day = day")
    suspend fun getSpecialDayPlans(year: Int, month: Int, dayOfWeek: Int, day: Int): List<DayPlansEntity>*/

    @Update
    suspend fun updateDrinkCategories(dayPlans: DayPlans)

    @Delete
    suspend fun delete(dayPlans: DayPlans)


}