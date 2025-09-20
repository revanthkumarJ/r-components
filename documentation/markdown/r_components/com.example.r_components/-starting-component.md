//[r_components](../../index.md)/[com.example.r_components](index.md)/[StartingComponent](-starting-component.md)

# StartingComponent

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [StartingComponent](-starting-component.md)(text: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html))

[StartingComponent](-starting-component.md) is a simple composable that displays a greeting message.

This component takes a [text](-starting-component.md) parameter and shows &quot;Hello [text](-starting-component.md)!&quot; using the standard [Text](https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary.html) composable from Jetpack Compose.

Example usage:

```kotlin
@Composable
fun PreviewStartingComponent() {
    StartingComponent("Revanth")
}
```

#### Parameters

androidJvm

| | |
|---|---|
| text | The name or text to include in the greeting message. |