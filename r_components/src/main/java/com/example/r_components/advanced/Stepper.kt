package com.example.r_components.advanced

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Represents a single step in the [Stepper] component.
 *
 * @param name The title of the step (e.g., "Details", "Terms").
 * @param content A composable block representing the content to display when this step is active.
 */
data class Step(
    val name: String,
    val content: @Composable () -> Unit,
)

/**
 * A customizable stepper component for guiding users through a multi-step process.
 *
 * Features:
 * - Displays steps in a horizontal row with step numbers and names.
 * - Highlights the currently active step.
 * - Allows navigation back to completed steps (but not forward).
 * - Supports custom colors for selected/unselected states.
 *
 * @param steps A list of [Step] items representing each step and its content.
 * @param currentIndex The index of the currently active step.
 * @param modifier Modifier to be applied to the Stepper layout.
 * @param stepperColor The color used for the stepper background and active step indicator.
 * Defaults to [MaterialTheme.colorScheme.primary].
 * @param unSelectedStepperColor The color for inactive steps. Defaults to [MaterialTheme.colorScheme.secondary].
 * @param onStepChange Callback triggered when the user clicks a completed step to go back.
 */
@Composable
fun Stepper(
    steps: List<Step>,
    currentIndex: Int,
    modifier: Modifier = Modifier,
    stepperColor: Color? = null,
    unSelectedStepperColor: Color? = null,
    onStepChange: (Int) -> Unit,
) {
    val listState = rememberLazyListState()

    // Automatically scrolls to the current step when it changes
    LaunchedEffect(currentIndex) {
        listState.animateScrollToItem(currentIndex)
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LazyRow(
            state = listState,
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(stepperColor ?: MaterialTheme.colorScheme.primary)
                .padding(vertical = 24.dp)
                .padding(start = 4.dp)
                .fillMaxWidth(),
        ) {
            steps.forEachIndexed { index, step ->
                item {
                    Row(
                        verticalAlignment = Alignment.Top,
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.width(56.dp),
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .clip(CircleShape)
                                    .background(
                                        when {
                                            index == currentIndex -> Color.White
                                            else -> unSelectedStepperColor ?: MaterialTheme.colorScheme.secondary
                                        },
                                    )
                                    .clickable(enabled = index < currentIndex) {
                                        if (index < currentIndex) onStepChange(index)
                                    },
                                contentAlignment = Alignment.Center,
                            ) {
                                Text(
                                    text = (index + 1).toString(),
                                    color = when {
                                        index == currentIndex -> stepperColor ?: MaterialTheme.colorScheme.primary
                                        else -> Color.White
                                    },
                                )
                            }

                            Spacer(modifier = Modifier.height(4.dp))
                            BasicText(
                                text = step.name,
                                autoSize = TextAutoSize.StepBased(
                                    minFontSize = 2.sp,
                                    maxFontSize = 11.sp,
                                ),
                                style = TextStyle.Default.copy(
                                    color = Color.White
                                )
                            )
                        }
                        if (index != steps.lastIndex) {
                            Box(
                                modifier = Modifier
                                    .padding(vertical = 16.dp)
                                    .width(4.dp)
                                    .height(1.dp)
                                    .background(stepperColor ?: MaterialTheme.colorScheme.primary),
                            )
                        } else {
                            Spacer(Modifier.width(4.dp))
                        }
                    }
                }
            }
        }

        Spacer(Modifier.height(36.dp))

        // Display content of the currently selected step
        steps[currentIndex].content()
    }
}

@Preview(showBackground = true)
@Composable
private fun StepperDemo() {
    val steps = listOf(
        Step("Details") { Text("Step 1: Details Content") },
        Step("Terms") { Text("Step 2: Terms Content") },
        Step("Charges") { Text("Step 3: Charges Content") },
        Step("Schedule") { Text("Step 4: Schedule Content") },
        Step("Preview") { Text("Step 5: Preview Content") },
    )

    Stepper(
        steps = steps,
        currentIndex = 2,
        onStepChange = { },
        modifier = Modifier.fillMaxWidth(),
    )
}