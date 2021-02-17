package com.planner.ui.dayInformation

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.planner.domain.model.DayPlans
import com.planner.domain.model.Plan
import com.planner.repository.PlansRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DayInformationViewModel
@Inject
constructor(
    private val dayPlansRepository: PlansRepository,
    private val state: SavedStateHandle,
) : ViewModel() {
    private val TAG: String = "DayInformationViewModel"

    var dayPlansLastIsNotEmpty = mutableStateOf(false)

    private var dayPlansList: List<DayPlans>? = null
    lateinit var dayPlans: DayPlans
    var planList: MutableState<List<Plan>> = mutableStateOf(listOf())


    var year: Int = 0
    var month: Int = 0
    var dayOfWeek: Int = 0
    var day: Int = 0

    fun getDayPlansList() {
        viewModelScope.launch {
            dayPlansList = dayPlansRepository.getSpecialDayPlansFromDB(
                yearSecond = year,
                monthSecond = month,
                daySecond = day
            )
            if (!dayPlansList.isNullOrEmpty()){
                dayPlans = dayPlansList!![0]
                dayPlansList!![0].plans ?.let {
                    planList.value = it
                    dayPlansLastIsNotEmpty.value = planList.value.isNotEmpty()
                }
            }
        }

    }

    fun onCheckDayPlan(plan: Plan, index: Int){
        viewModelScope.launch {
            val dayPlan: DayPlans = dayPlansList!![0]
            dayPlansRepository.updateSpecialPlanInSpecialDay(dayPlan, plan, index)
            Log.d(TAG, "onCheckDayPlan done")
        }
    }



}