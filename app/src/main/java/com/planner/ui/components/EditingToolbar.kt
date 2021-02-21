package com.planner.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EditingToolbar(
    navigateToPreviousFragment: () -> Unit,
    deletePlan: () -> Unit,
    saveUpdatedPlan: () -> Unit,
    showToast: (text: String) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        elevation = 8.dp
    ) {
        Row() {
            IconButton(
                onClick = { navigateToPreviousFragment() },
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            ) {
                Icon(Icons.Filled.Close, contentDescription = "Close Icon")
            }

            IconButton(
                onClick = {
                    deletePlan()
                    showToast("Deleted")
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
                    saveUpdatedPlan()
                    showToast("Saved")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.End)
                    .align(Alignment.CenterVertically)
            ) {

                Icon(Icons.Filled.Save, contentDescription = "Save Icon")
            }

        }
    }
}