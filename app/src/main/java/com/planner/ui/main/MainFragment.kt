package com.planner.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.planner.R
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

    private val TAG: String = "MainFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {



        return ComposeView(requireContext()).apply{
            setContent {
                PlannerTheme {

                    LazyRow(){
                        itemsIndexed(
                            items = viewModel.weeksList.value
                        ){i, item ->
                            viewModel.onChangeWeeksScrollPosition(i)
                            Log.d(TAG, "WeeksScrollPosition $i")
                            if ((i + 1) >= viewModel.caunter){
                                viewModel.nextWeek()
                            }
                            LazyColumn(modifier = Modifier.width(365.dp)){
                                itemsIndexed(
                                    items = item
                                ){index, dayInformation ->
                                    MainDayCard(
                                        month = dayInformation.getMonthName(),
                                        dayOfWeek = dayInformation.getDayOfWeekName(),
                                        day = dayInformation.day,
                                        numberOfDonePlans = 0,
                                        numberOfPlans = 7,
                                        onClick = {
                                            findNavController().navigate(R.id.action_mainFragment_to_dayInformationFragment)
                                        })

                                }

                            }
                        }
                    }

                }

            }
        }
    }

}
