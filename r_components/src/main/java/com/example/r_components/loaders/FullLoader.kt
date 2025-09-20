package com.example.r_components.loaders

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.tooling.preview.Preview

/**
 * A full screen loader composable.
 *
 * Displays a [CircularProgressIndicator] centered vertically and horizontally.
 *
 * @param modifier Optional [Modifier] for styling and layout adjustments.
 */
@Composable
fun FullProgressIndicator(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CircularProgressIndicator()
    }
}

/**
 * A full screen overlay loader with semi-transparent background.
 *
 * Useful when you want to indicate a loading state while dimming underlying content.
 *
 * @param modifier Optional [Modifier] for styling and layout adjustments.
 */
@Composable
fun FullProgressIndicatorOverlay(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background.copy(alpha = 0.7f)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CircularProgressIndicator()
    }
}

/**
 * A modal dialog style loader.
 *
 * Displays a [CircularProgressIndicator] inside a [Dialog] with a [Surface] container.
 *
 * @param modifier Optional [Modifier] for styling and layout adjustments.
 * @param show Controls visibility of the loader. Default is true.
 */
@Composable
fun LoadingDialog(
    modifier: Modifier = Modifier,
    show: Boolean = true
) {
    if (show) {
        Dialog(onDismissRequest = {}) {
            Surface(
                shape = MaterialTheme.shapes.medium,
                tonalElevation = 4.dp,
                shadowElevation = 4.dp,
                modifier = modifier
            ) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

/**
 * A small full-width horizontal loader.
 *
 * Can be used as a thin progress indicator at the top of a screen.
 *
 * @param modifier Optional [Modifier] for styling and layout adjustments.
 */
@Composable
fun FullWidthLoader(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.Center),
            strokeWidth = 2.dp
        )
    }
}

/**
 * Previews for composables
 */
@Preview(showBackground = true)
@Composable
private fun FullProgressIndicatorPreview() {
    FullProgressIndicator()
}

@Preview(showBackground = true)
@Composable
private fun FullProgressIndicatorOverlayPreview() {
    FullProgressIndicatorOverlay()
}

@Preview(showBackground = true)
@Composable
private fun LoadingDialogPreview() {
    LoadingDialog()
}

@Preview(showBackground = true)
@Composable
private fun FullWidthLoaderPreview() {
    FullWidthLoader()
}
