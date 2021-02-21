package com.planner.ui.addNewPlan

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Save
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.planner.ui.BaseApplication
import com.planner.ui.components.AddNewPlanToolbar
import com.planner.ui.theme.PlannerTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
@ExperimentalMaterialApi
class AddNewPlanFragment : Fragment() {

    @Inject
    lateinit var application: BaseApplication

    private val viewModel: AddNewPlanViewModel by viewModels()

    private val TAG: String = "AddNewPlanFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        arguments?.getInt("year")?.let { viewModel.year = it }
        arguments?.getInt("month")?.let { viewModel.month = it }
        arguments?.getInt("dayOfWeek")?.let { viewModel.dayOfWeek = it }
        arguments?.getInt("day")?.let { viewModel.day = it }



        return ComposeView(requireContext()).apply {
            setContent {
                PlannerTheme {
                    Column{

                        AddNewPlanToolbar(
                            navigateToPreviousFragment = { findNavController().popBackStack() },
                            setNewPlans = viewModel::setNewPlans,
                            showToast = { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() }
                        )

                        TextField(
                            value = viewModel.newPlan.value,
                            onValueChange = { viewModel.onTextChanged(it) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    start = 12.dp,
                                    top = 6.dp,
                                    end = 6.dp,
                                    bottom = 6.dp
                                ),
                            textStyle = TextStyle(color = MaterialTheme.colors.onSurface),
                            label = {
                                Text(text = "New Plan")
                            },
                            backgroundColor = MaterialTheme.colors.surface
                        )

                    }

                }
            }
        }
    }
}