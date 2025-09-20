package com.example.r_components.error_components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * A Composable function that displays a centered icon and text, typically used to indicate an empty state.
 *
 * @param text The text to be displayed below the icon.
 * @param modifier The modifier to be applied to the root Box composable. Defaults to Modifier.
 * @param icon The ImageVector to be displayed as the icon. Defaults to Icons.Default.Info.
 */
@Composable
fun EmptyUi(
    text: String,
    modifier: Modifier = Modifier,
    icon: ImageVector = Icons.Default.Info,
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(18.dp)
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically),
        ) {
            Icon(
                imageVector = icon,
                contentDescription = text + icon.name,
                modifier = Modifier.size(70.dp),
            )

            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

/**
 * MifosEmptyUiPreview is a Composable function that displays a preview of the EmptyUi Composable.
 * It is annotated with @Preview, which allows it to be rendered in Android Studio's preview window.
 * This function is used for development and testing purposes to visualize the appearance of the EmptyUi Composable.
 *
 * @see EmptyUi
 */
@Preview
@Composable
private fun MifosEmptyUiPreview() {
    EmptyUi(
        text = "No data available",
    )
}