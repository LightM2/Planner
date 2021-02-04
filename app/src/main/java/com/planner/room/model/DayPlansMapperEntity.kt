package com.planner.room.model

import com.planner.domain.DomainMapper
import com.planner.domain.model.DayPlans
import com.planner.domain.model.Plan

class DayPlansMapperEntity : DomainMapper<DayPlansEntity, DayPlans> {
    override fun mapToDomainModel(model: DayPlansEntity): DayPlans {
        return DayPlans(
            id = model.id,
            year = model.year,
            month = model.month,
            dayOfWeek = model.dayOfWeek,
            day = model.day,
            plans = stringToPlan(model.plans)
        )
    }

    override fun mapFromDomainModel(domainModel: DayPlans): DayPlansEntity {
        return DayPlansEntity(
            id = domainModel.id,
            year = domainModel.year,
            month = domainModel.month,
            dayOfWeek = domainModel.dayOfWeek,
            day = domainModel.day,
            plans = planToString(domainModel.plans)
        )
    }

    fun toDomainList(initial: List<DayPlansEntity>): List<DayPlans> {
        return initial.map { mapToDomainModel(it) }
    }

    fun fromDomainList(initial: List<DayPlans>): List<DayPlansEntity> {
        return initial.map { mapFromDomainModel(it) }
    }

    private fun planToString(listOfPlans: MutableList<Plan>?): String? {
        var resultString: String? = null
        if (listOfPlans != null){
            resultString = listOfPlans.map { "${it.newPlan} - ${it.done}" }.joinToString { "; " }
        }
        return resultString
    }

    private fun stringToPlan(stringOfPlans: String?): MutableList<Plan>? {
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