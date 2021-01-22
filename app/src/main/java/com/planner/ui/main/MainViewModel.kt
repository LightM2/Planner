package com.planner.ui.main

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.planner.repository.PlansRepository

class MainViewModel
@ViewModelInject
constructor(
    private val recipeRepository: PlansRepository,
    @Assisted private val state: SavedStateHandle,
): ViewModel(){


}