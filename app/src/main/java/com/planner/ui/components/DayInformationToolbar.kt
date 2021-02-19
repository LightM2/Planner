package com.planner.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DayInformationToolbar(
    monthName: String,
    day: Int,
    navigateToPreviousFragment: () -> Unit,
    navigateToNextFragment: () -> Unit,

    ) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        elevation = 8.dp
    ) {
        Row {
            IconButton(
                onClick = {
                    navigateToPreviousFragment()
                },
                modifier = Modifier
                    .wrapContentWidth(Alignment.Start)
                    .align(Alignment.CenterVertically)
            ) {
                Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = "Arrow Back Icon"
                )
            }

            Text(
                text = monthName,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(end = 8.dp),
                style = MaterialTheme.typography.h4,
            )

            Text(
                text = "$day",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(end = 8.dp),
                style = MaterialTheme.typography.h4
            )

            IconButton(
                onClick = {
                    navigateToNextFragment()
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