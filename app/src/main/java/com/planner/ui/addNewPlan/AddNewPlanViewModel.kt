package com.planner.ui.addNewPlan

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.planner.repository.PlansRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddNewPlanViewModel
@Inject
constructor(
    private val recipeRepository: PlansRepository,
    private val state: SavedStateHandle,
) : ViewModel() {

    private val TAG = "AddNewPlanViewModel"

    val newPlan = mutableStateOf("")

    fun onTextChanged(text: String){
        newPlan.value = text
    }


}