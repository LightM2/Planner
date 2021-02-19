package com.planner.ui.dayInformation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.planner.R
import com.planner.ui.BaseApplication
import com.planner.ui.components.DayInformationToolbar
import com.planner.ui.components.DayPlanRow
import com.planner.ui.theme.PlannerTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@ExperimentalMaterialApi
@AndroidEntryPoint
class DayInformationFragment : Fragment() {
    @Inject
    lateinit var application: BaseApplication

    private val viewModel: DayInformationViewModel by viewModels()

    private val TAG: String = "DayInformationFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val monthName = arguments?.getString("monthName")

        arguments?.getInt("year")?.let { viewModel.year = it }
        arguments?.getInt("month")?.let { viewModel.month = it }
        arguments?.getInt("dayOfWeek")?.let { viewModel.dayOfWeek = it }
        arguments?.getInt("day")?.let { viewModel.day = it }


        return ComposeView(requireContext()).apply {
            setContent {
                PlannerTheme() {
                    Column {

                        DayInformationToolbar(
                            monthName = monthName.toString(),
                            day = viewModel.day,
                            navigateToPreviousFragment = { findNavController().popBackStack() },
                            navigateToNextFragment = {
                                val bundle = bundleOf(
                                    Pair("year", viewModel.year),
                                    Pair("month", viewModel.month),
                                    Pair("dayOfWeek", viewModel.dayOfWeek),
                                    Pair("day", viewModel.day)
                                )

                                findNavController().navigate(
                                    R.id.action_dayInformationFragment_to_addNewPlanFragment,
                                    bundle
                                )
                            })



                        viewModel.getDayPlansList()



                        if (viewModel.dayPlansLastIsNotEmpty.value) {
                            LazyColumn {
                                itemsIndexed(
                                    items = viewModel.planList.value
                                ) { i, plan ->
                                    DayPlanRow(
                                        onClick = {
                                            Log.d(TAG, "onClick")
                                            val bundle = bundleOf(
                                                Pair("id", viewModel.dayPlans.id),
                                                Pair("planIndex", i),
                                            )
                                            findNavController().navigate(
                                                R.id.action_dayInformationFragment_to_editingFragment,
                                                bundle
                                            )
                                        },
                                        onLongClick = {
                                            Log.d(TAG, "long click")
                                        },
                                        plan = plan,
                                        onCheckDayPlan = { viewModel.onCheckDayPlan(plan, i) }
                                    )

                                }
                            }
                        }

                    }


                }


            }
        }
    }
}
