package com.planner.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.planner.ui.BaseApplication
import com.planner.ui.components.MainDayCard
import com.planner.ui.theme.PlannerTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment: Fragment() {

    @Inject
    lateinit var application: BaseApplication

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.getCurrentDate()
        val weekList = viewModel.currentDayInformation.getThisWeekList()


        return ComposeView(requireContext()).apply{
            setContent {
                PlannerTheme() {
                    LazyColumn(){
                        itemsIndexed(
                            items = weekList
                        ){index, dayInformation ->
                            MainDayCard(
                                month = dayInformation.getMonthName(),
                                dayOfWeek = dayInformation.getDayOfWeekName(),
                                day = dayInformation.day,
                                numberOfDonePlans = 0,
                                numberOfPlans = 7,
                                onClick = {})

                        }

                    }
                }

            }
        }
    }

}
