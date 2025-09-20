package com.example.r_components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

/**
 * [StartingComponent] is a simple composable that displays a greeting message.
 *
 * This component takes a [text] parameter and shows "Hello [text]!" using the
 * standard [Text] composable from Jetpack Compose.
 *
 * Example usage:
 * ```
 * @Composable
 * fun PreviewStartingComponent() {
 *     StartingComponent("Revanth")
 * }
 * ```
 *
 * @param text The name or text to include in the greeting message.
 */
@Composable
fun StartingComponent(text: String) {
    Text("Hello $text!")
}
