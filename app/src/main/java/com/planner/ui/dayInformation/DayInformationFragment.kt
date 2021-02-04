package com.planner.ui.dayInformation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Text
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.planner.ui.BaseApplication
import com.planner.ui.theme.PlannerTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DayInformationFragment: Fragment() {
    @Inject
    lateinit var application: BaseApplication

    private val viewModel: DayInformationViewModel by viewModels()

    private val TAG: String = "DayInformationFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {



        return ComposeView(requireContext()).apply{
            setContent {
                PlannerTheme {
                    Text(text = "DayInformationFragment")

                }

            }
        }
    }

}