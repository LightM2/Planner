package com.planner.room.model

import androidx.room.*

@Entity(tableName = "dayPlansTN")
data class DayPlans (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "year")
    val year: Int? = null,
    @ColumnInfo(name = "month")
    val month: Int? = null,
    @ColumnInfo(name = "dayOfWeek")
    val dayOfWeek: Int? = null,
    @ColumnInfo(name = "day")
    val day: Int? = null,

    @TypeConverters(ListOfPlansTypeConverter::class)
    var plans: MutableList<Plan>? = null
)