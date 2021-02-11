package com.planner.ui.dayInformation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.planner.repository.PlansRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DayInformationViewModel
@Inject
constructor(
    private val dayPlansRepository: PlansRepository,
    private val state: SavedStateHandle,
) : ViewModel(){
    private val TAG: String = "DayInformationViewModel"


}