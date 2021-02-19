package com.planner.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Today
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.planner.R
import com.planner.ui.BaseApplication
import com.planner.ui.components.MainDayCard
import com.planner.ui.components.MainToolbar
import com.planner.ui.theme.PlannerTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
@ExperimentalMaterialApi
class MainFragment : Fragment() {

    @Inject
    lateinit var application: BaseApplication

    private val viewModel: MainViewModel by viewModels()

    private val TAG: String = "MainFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return ComposeView(requireContext()).apply {
            setContent {
                PlannerTheme {
                    viewModel.getToday()

                    Column() {
                        MainToolbar(
                            getPreviousWeek = viewModel::getPreviousWeek,
                            getThisWeek = viewModel::getThisWeek,
                            getNextWeek = viewModel::getNextWeek
                        )

                        LazyColumn(modifier = Modifier.fillMaxWidth()) {
                            itemsIndexed(
                                items = viewModel.week.value
                            ) { index, dayPlan ->
                                Log.d(TAG, "numberOfPlans - ${dayPlan.countPlans()}")
                                MainDayCard(
                                    month = dayPlan.getMonthName(),
                                    dayOfWeek = dayPlan.getDayOfWeekName(),
                                    day = dayPlan.day,
                                    numberOfDonePlans = dayPlan.countDonePlans(),
                                    numberOfPlans = dayPlan.countPlans(),
                                    isToday = dayPlan.isSameDay(viewModel.today),
                                    onClick = {
                                        val bundle = bundleOf(
                                            Pair("year", dayPlan.year),
                                            Pair("month", dayPlan.month),
                                            Pair("monthName", dayPlan.getMonthName()),
                                            Pair("dayOfWeek", dayPlan.dayOfWeek),
                                            Pair("day", dayPlan.day)
                                        )
                                        findNavController().navigate(
                                            R.id.action_mainFragment_to_dayInformationFragment,
                                            bundle
                                        )
                                    })

                            }

                        }


                    }


                }

            }
        }
    }



}

