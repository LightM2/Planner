package com.planner.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.planner.room.model.DayPlansEntity

@Database(entities = [DayPlansEntity::class], version = 1)
abstract class DayPlansDatabase: RoomDatabase() {
    abstract fun dayPlansDao(): DayPlansDao

    companion object{
        val DATABASE_NAME: String = "day_plans_database"
    }

}