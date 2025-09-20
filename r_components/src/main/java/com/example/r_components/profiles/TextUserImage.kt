package com.example.r_components.profiles

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.min

/**
 * A composable that displays a text inside a circular shape,
 * with the font size automatically adjusted to fit the box size.
 *
 * @param text The string to display inside the circle.
 * @param modifier Modifier to adjust the size, padding, or layout of the circle.
 * @param bgColor Optional background color. If not provided, uses [MaterialTheme.colorScheme.primary].
 */
@Composable
fun TextUserImage(
    text: String,
    modifier: Modifier = Modifier,
    bgColor: Color? = null,
) {
    var boxSize by remember { mutableStateOf(Size.Zero) }
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(bgColor ?: MaterialTheme.colorScheme.primary)
            .onGloballyPositioned { coordinates ->
                boxSize = Size(
                    coordinates.size.width.toFloat(),
                    coordinates.size.height.toFloat(),
                )
            },
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            fontSize = with(LocalDensity.current) {
                (min(boxSize.width, boxSize.height) / 2).toSp()
            },
        )
    }
}

/**
 * Preview for [TextUserImage].
 *
 * ## How to use:
 * ```
 * TextUserImage(
 *     text = "RK",
 *     modifier = Modifier.size(100.dp),
 *     bgColor = Color.Red
 * )
 * ```
 * Pass a size modifier to control the circle size and optionally a background color.
 */
@Preview(showBackground = true)
@Composable
fun TextUserImagePreview() {
    TextUserImage(
        text = "RK",
        modifier = Modifier.size(50.dp),
        bgColor = Color.Blue
    )
}
