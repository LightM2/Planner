package com.planner.ui.addNewPlan

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Repeat
import androidx.compose.material.icons.filled.Save
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.planner.R
import com.planner.ui.BaseApplication
import com.planner.ui.theme.PlannerTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
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
                    Column(
                        modifier = Modifier.padding(
                            start = 12.dp,
                            top = 6.dp,
                            end = 6.dp,
                            bottom = 6.dp
                        )
                    ) {
                        Row {
                            IconButton(
                                onClick = { findNavController().popBackStack() },
                                modifier = Modifier
                                    .align(Alignment.CenterVertically)
                            ) {
                                Icon(Icons.Filled.Close, contentDescription = "Close Icon")
                            }

                            IconButton(
                                onClick = {
                                    viewModel.setNewPlans()
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
                            value = viewModel.newPlan.value,
                            onValueChange = { viewModel.onTextChanged(it) },
                            modifier = Modifier.fillMaxWidth(),
                            label = {
                                Text(text = "New Plan")
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Done,
                            ),
                            onImeActionPerformed = { action, softKeyboardController ->
                                if (action == ImeAction.Done) {
                                    softKeyboardController?.hideSoftwareKeyboard()
                                }
                            },
                            textStyle = TextStyle(color = MaterialTheme.colors.onSurface),
                            backgroundColor = MaterialTheme.colors.surface

                        )

                        /*IconButton(
                            onClick = { *//*TODO*//* },
                        ) {

                            Icon(Icons.Filled.Repeat, contentDescription = "Repeat Icon")
                        }*/
                    }

                }
            }
        }
    }
}