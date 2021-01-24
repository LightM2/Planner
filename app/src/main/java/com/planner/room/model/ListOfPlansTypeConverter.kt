package com.planner.room.model

import androidx.room.TypeConverter

class ListOfPlansTypeConverter {
    companion object {

        @TypeConverter
        @JvmStatic
        fun planToString(listOfPlans: MutableList<Plan>?): String? {
            var resultString: String? = null
            if (listOfPlans != null){
                resultString = listOfPlans.map { "${it.newPlan} - ${it.done}" }.joinToString { "; " }
            }
            return resultString
        }


        @TypeConverter
        @JvmStatic
        fun stringToPlan(stringOfPlans: String?): MutableList<Plan>? {
            var resultMutableList: MutableList<Plan>? = null
            if (stringOfPlans != null){
                val listOfPars = stringOfPlans.split("; ")
                resultMutableList = listOfPars.map { it ->
                    val result = it.split(" - ")
                    Plan(result[0], result[1].toBoolean())
                }.toMutableList()
            }

            return resultMutableList
        }
    }
}