package com.planner.ui.editing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.planner.ui.BaseApplication
import com.planner.ui.components.EditingToolbar
import com.planner.ui.theme.PlannerTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
class EditingFragment : Fragment() {
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
                    Column {

                        if (viewModel.isPlanDeleted.value) {
                            findNavController().popBackStack()
                        }

                        EditingToolbar(
                            navigateToPreviousFragment = { findNavController().popBackStack() },
                            deletePlan = viewModel::deletePlan,
                            saveUpdatedPlan = viewModel::saveUpdatedPlan,
                            showToast = { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() }
                        )

                        TextField(
                            value = viewModel.planString.value,
                            onValueChange = { viewModel.onTextChanged(it) },
                            modifier = Modifier.fillMaxWidth(),
                            textStyle = TextStyle(color = MaterialTheme.colors.onSurface),
                            label = {
                                Text(text = "Plan")
                            },
                            backgroundColor = MaterialTheme.colors.surface,
                        )


                        Button(
                            onClick = {
                                viewModel.changePlanState()
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentWidth(Alignment.End)
                                .padding(top = 12.dp, end = 6.dp),
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