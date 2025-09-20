package com.example.r_components.error_components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * A simple composable to display a "No Internet" error screen with an optional retry button.
 *
 * This composable displays an icon, an error message, and optionally a retry button.
 *
 * @param modifier Modifier to apply to the root layout.
 * @param error Optional error message to display. Defaults to "No Internet Connection".
 * @param isRetryEnabled Whether the retry button should be shown. Defaults to true.
 * @param icon The icon to display at the top. Defaults to [Icons.Default.WifiOff].
 * @param retry Lambda that is invoked when the retry button is clicked.
 *
 * ## Example usage:
 * ```kotlin
 * NoInternet(
 *     error = "Unable to connect",
 *     isRetryEnabled = true,
 *     retry = { /* retry action */ }
 * )
 * ```
 */
@Composable
fun NoInternet(
    modifier: Modifier = Modifier,
    error: String? = null,
    isRetryEnabled: Boolean = true,
    icon: ImageVector = Icons.Default.WifiOff,
    retry: () -> Unit = {},
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            modifier = Modifier
                .size(100.dp)
                .padding(bottom = 12.dp),
            imageVector = icon,
            contentDescription = "No Internet Icon",
        )

        Text(
            text = error ?: "No Internet Connection",
            style = TextStyle(fontSize = 20.sp),
        )

        Spacer(modifier = Modifier.height(12.dp))
        if (isRetryEnabled) {
            FilledTonalButton(onClick = retry) {
                Text(text = "Retry")
            }
        }
    }
}

/**
 * Preview for [NoInternet] composable.
 *
 * Demonstrates the default usage without a custom error message.
 */
@Preview(showBackground = true)
@Composable
fun NoInternetPreview(
    modifier: Modifier = Modifier,
) {
    NoInternet(
        modifier = modifier,
        isRetryEnabled = true,
        retry = {},
    )
}
