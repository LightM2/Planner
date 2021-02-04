package com.planner.room.model

import androidx.room.*

@Entity(tableName = "dayPlansTN")
data class DayPlansEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "year")
    val year: Int = 0,
    @ColumnInfo(name = "month")
    val month: Int = 0, //January = 0
    @ColumnInfo(name = "dayOfWeek")
    val dayOfWeek: Int = 0,  //Sunday = 1
    @ColumnInfo(name = "day")
    val day: Int = 0,
    @ColumnInfo(name = "plans")
    var plans: String? = null
)