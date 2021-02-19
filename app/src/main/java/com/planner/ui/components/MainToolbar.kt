package com.planner.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Today
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MainToolbar(
    getPreviousWeek: () -> Unit,
    getThisWeek: () -> Unit,
    getNextWeek: () -> Unit,
){
    Surface(
        modifier = Modifier.fillMaxWidth(),
        elevation = 8.dp
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            IconButton(
                onClick = {
                    getPreviousWeek()
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

            IconButton(
                onClick = {
                    getThisWeek()
                },
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .wrapContentWidth(Alignment.CenterHorizontally)
                    .align(Alignment.CenterVertically)
            ) {
                Icon(Icons.Filled.Today, contentDescription = "Today Icon")
            }

            IconButton(
                onClick = {
                    getNextWeek()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.End)
                    .align(Alignment.CenterVertically)
            ) {
                Icon(
                    Icons.Filled.ArrowForward,
                    contentDescription = "Arrow Forward Icon"
                )
            }


        }

    }
}