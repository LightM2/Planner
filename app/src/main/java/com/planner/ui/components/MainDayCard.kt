package com.planner.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MainDayCard(
    month: String,
    dayOfWeek: String,
    day: Int,
    numberOfDonePlans: Int,
    numberOfPlans: Int,
    isToday: Boolean,
    onClick: () -> Unit
) {
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(
                bottom = 6.dp,
                top = 6.dp,
                start = 6.dp,
                end = 6.dp
            )
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = 8.dp,
    ) {
        Row {
            Column(
                modifier = if (isToday) {
                    Modifier.background(Color.DarkGray)
                } else Modifier.background(Color.LightGray)
            ) {
                Text(
                    text = dayOfWeek,
                    modifier = Modifier
                        .fillMaxWidth(0.25f)
                        .wrapContentWidth(Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.subtitle2
                )
                Text(
                    text = day.toString(),
                    modifier = Modifier
                        .fillMaxWidth(0.25f)
                        .wrapContentWidth(Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.h4
                )
                Text(
                    text = month,
                    modifier = Modifier
                        .fillMaxWidth(0.25f)
                        .wrapContentWidth(Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.subtitle1
                )

            }
            if (numberOfPlans != 0){
                Text(
                    text = "$numberOfDonePlans/$numberOfPlans",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 6.dp)
                        .wrapContentWidth(Alignment.End)
                        .align(Alignment.CenterVertically),
                    style = MaterialTheme.typography.h4
                )
            }
        }
    }
}