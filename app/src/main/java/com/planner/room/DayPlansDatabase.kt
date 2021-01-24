package com.planner.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.planner.room.model.DayPlans
import com.planner.room.model.ListOfPlansTypeConverter

@Database(entities = [DayPlans::class], version = 1)
@TypeConverters(ListOfPlansTypeConverter::class)
abstract class DayPlansDatabase: RoomDatabase() {
    abstract fun dayPlansDao(): DayPlansDao

    companion object{
        val DATABASE_NAME: String = "day_plans_database"
    }

}