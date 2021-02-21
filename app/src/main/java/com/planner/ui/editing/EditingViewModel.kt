package com.planner.ui.editing

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
class EditingViewModel
@Inject
constructor(
    private val dayPlansRepository: PlansRepository,
    private val state: SavedStateHandle,
) : ViewModel(){
    private val TAG: String = "EditingViewModel"

    var id: Int = 0
    var planIndex: Int = 0
    val isPlanDeleted: MutableState<Boolean> = mutableStateOf(false)

    lateinit var dayPlans: DayPlans
    val plan: MutableState<Plan> = mutableStateOf(Plan(null, false))
    val planString: MutableState<String> = mutableStateOf("")
    val buttonText = mutableStateOf("")

    fun onTextChanged(text: String) {
        planString.value = text
    }


    fun getEditingPlan(){
        viewModelScope.launch {
            dayPlansRepository.getDayPlansUsingIDFromDB(id)?.let {
                dayPlans = it
                plan.value = dayPlans.plans!![planIndex]
                planString.value = plan.value.newPlan.toString()
            }
        }
    }

    fun saveUpdatedPlan(){
        viewModelScope.launch {
            plan.value.newPlan = planString.value
            dayPlansRepository.updateSpecialPlanInSpecialDay(dayPlans, plan.value, planIndex)
        }
    }

    fun changePlanState(){
        viewModelScope.launch {
            plan.value.done = plan.value.done.not()
            saveUpdatedPlan()
            setButtonText()
        }
    }

    fun deletePlan(){
        viewModelScope.launch {
            dayPlansRepository.deleteSpecialPlanInSpecialDay(dayPlans, plan.value)
            isPlanDeleted.value = true
        }
    }

    fun setButtonText(){
        if (plan.value.done){
            buttonText.value = "Uncheck"
        }else buttonText.value = "Check"
    }

}