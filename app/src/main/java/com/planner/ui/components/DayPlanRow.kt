package com.planner.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.planner.domain.model.Plan

@Composable
@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
fun DayPlanRow(
    onClick: () -> Unit,
    onLongClick: (() -> Unit)?,
    plan: Plan,
    onCheckDayPlan: () -> Unit
) {
    val isCheck = remember { mutableStateOf(plan.done) }
    Row(
        modifier = Modifier
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick
            )
            .fillMaxWidth()
    ) {
        Checkbox(
            checked = isCheck.value,
            onCheckedChange = {
                isCheck.value = it
                plan.done = it
                onCheckDayPlan()
            },
            modifier = Modifier
                .padding(end = 12.dp)
                .align(Alignment.CenterVertically)
        )

        Text(
            text = "${plan.newPlan}",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(end = 8.dp)
        )

    }
}