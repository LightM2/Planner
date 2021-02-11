package com.planner.ui.addNewPlan

import android.util.Log
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
class AddNewPlanViewModel
@Inject
constructor(
    private val dayPlansRepository: PlansRepository,
    private val state: SavedStateHandle,
) : ViewModel() {

    private val TAG = "AddNewPlanViewModel"

    var year: Int = 0
    var month: Int = 0
    var dayOfWeek: Int = 0
    var day: Int = 0

    val newPlan = mutableStateOf("")

    fun onTextChanged(text: String) {
        newPlan.value = text
    }

    fun setNewPlans() {
        viewModelScope.launch {
            val listOfDayPlans = dayPlansRepository.getSpecialDayPlansFromDB(year, month, day)
            Log.d(TAG, "year - $year, Month - $month, day - $day")
            var dayPlans = DayPlans(
                year = year,
                month = month,
                dayOfWeek = dayOfWeek,
                day = day,
                plans = mutableListOf(Plan(newPlan.value, false))
            )
            if (listOfDayPlans == null) {
                dayPlansRepository.setNewDayPlansInDB(dayPlans)
            }else{
                dayPlans = listOfDayPlans[0]
                dayPlans.plans?.add(Plan(newPlan.value, false))
                dayPlansRepository.updateDayPlansInDB(dayPlans)
            }
            newPlan.value = ""
        }

    }


}