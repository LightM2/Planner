package com.planner.ui.editing

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
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.planner.R
import com.planner.ui.BaseApplication
import com.planner.ui.components.DayPlanRow
import com.planner.ui.dayInformation.DayInformationViewModel
import com.planner.ui.theme.PlannerTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EditingFragment: Fragment() {
    @Inject
    lateinit var application: BaseApplication

    private val viewModel: EditingViewModel by viewModels()

    private val TAG: String = "EditingFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        arguments?.getInt("id")?.let { viewModel.id = it }
        arguments?.getInt("planIndex")?.let { viewModel.planIndex = it }


        viewModel.getEditingPlan()

        return ComposeView(requireContext()).apply {
            setContent {
                PlannerTheme {
                    Column{
                        Row() {
                            IconButton(
                                onClick = { findNavController().popBackStack() },
                                modifier = Modifier
                                    .align(Alignment.CenterVertically)
                            ) {
                                Icon(Icons.Filled.Close, contentDescription = "Close Icon")
                            }

                            IconButton(
                                onClick = {
                                    viewModel.deletePlan()
                                },
                                modifier = Modifier
                                    .fillMaxWidth(0.85f)
                                    .wrapContentWidth(Alignment.End)
                                    .align(Alignment.CenterVertically)
                            ) {

                                Icon(Icons.Filled.Delete, contentDescription = "Delete Icon")
                            }
                            IconButton(
                                onClick = {
                                    viewModel.saveUpdatedPlan()
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentWidth(Alignment.End)
                                    .align(Alignment.CenterVertically)
                            ) {

                                Icon(Icons.Filled.Save, contentDescription = "Save Icon")
                            }

                        }

                        TextField(
                            value = viewModel.planString.value,
                            onValueChange = { viewModel.onTextChanged(it) },
                            modifier = Modifier.fillMaxWidth(),
                            textStyle = TextStyle(color = MaterialTheme.colors.onSurface),
                            label = {
                                Text(text = "Plan")
                            },
                            backgroundColor = MaterialTheme.colors.surface
                        )


                        Button(
                            onClick = { viewModel.changePlanState() },
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentWidth(Alignment.End)
                                .padding(top = 12.dp, end = 6.dp)
                        ) {
                            viewModel.setButtonText()
                            Text(
                                text = viewModel.buttonText.value,
                                style = MaterialTheme.typography.subtitle2
                            )

                        }
                    }

                }

            }
        }
    }

}