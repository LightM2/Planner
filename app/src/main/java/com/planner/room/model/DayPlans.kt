package com.planner.room.model

import androidx.room.*

@Entity(tableName = "dayPlansTN")
data class DayPlans (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "year")
    val year: Int? = null,
    @ColumnInfo(name = "month")
    val month: Int? = null, //January = 0
    @ColumnInfo(name = "dayOfWeek")
    val dayOfWeek: Int? = null,  //Sunday = 1
    @ColumnInfo(name = "day")
    val day: Int? = null,

    @TypeConverters(ListOfPlansTypeConverter::class)
    var plans: MutableList<Plan>? = null
)