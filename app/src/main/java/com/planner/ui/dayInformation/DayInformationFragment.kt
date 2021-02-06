package com.planner.ui.dayInformation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.AddTask
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
import com.planner.ui.theme.PlannerTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

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

        val year = arguments?.getInt("year")
        val month = arguments?.getInt("month")
        val monthName = arguments?.getString("monthName")
        val day = arguments?.getInt("day")



        return ComposeView(requireContext()).apply {
            setContent {
                PlannerTheme {
                    Column(
                        modifier = Modifier.padding(
                            start = 12.dp,
                            top = 6.dp,
                            end = 6.dp,
                            bottom = 6.dp
                        )
                    ) {
                        Row {
                            Text(
                                text = "$monthName",
                                style = MaterialTheme.typography.h4,
                                modifier = Modifier
                                    .align(Alignment.CenterVertically)
                                    .padding(end = 8.dp)
                            )

                            Text(
                                modifier = Modifier
                                    .align(Alignment.CenterVertically),
                                text = "$day",
                                style = MaterialTheme.typography.h4
                            )

                            IconButton(
                                onClick = {
                                    findNavController().navigate(
                                        R.id.action_dayInformationFragment_to_addNewPlanFragment,
                                    )
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(end = 8.dp)
                                    .wrapContentWidth(Alignment.End)
                                    .align(Alignment.CenterVertically),

                                ) {
                                Icon(
                                    imageVector = Icons.Filled.AddCircleOutline,
                                    contentDescription = "Add Task Icon"
                                )
                            }

                        }

                    }


                }

            }
        }
    }

}