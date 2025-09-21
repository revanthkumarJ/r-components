package com.example.r_components.text_fields

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults.colors
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation

/**
 * A Composable function that displays an outlined text field with customizable options.
 *
 * @param value The current value of the text field.
 * @param onValueChange A callback function that is invoked when the value of the text field changes.
 * @param label The label to display for the text field.
 * @param modifier A [Modifier] to apply to the text field.
 * @param shape The shape of the text field's outline.
 * @param colors The colors to use for the text field.
 * @param textStyle The text style to apply to the text field.
 * @param interactionSource The [MutableInteractionSource] for this text field.
 * @param config A [RevTextFieldConfig] object that configures the behavior and appearance of the text field.
 * @param onClickClearIcon A callback function that is invoked when the clear icon is clicked.
 *
 * @see OutlinedTextField
 * @see RevTextFieldConfig
 */
@Composable
fun RevOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    shape: Shape = OutlinedTextFieldDefaults.shape,
    colors: TextFieldColors = colors(
        focusedBorderColor = MaterialTheme.colorScheme.secondaryContainer,
        unfocusedBorderColor = MaterialTheme.colorScheme.secondaryContainer,
        errorBorderColor = MaterialTheme.colorScheme.error,
    ),
    textStyle: TextStyle = LocalTextStyle.current,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    config: RevTextFieldConfig = RevTextFieldConfig(),
    onClickClearIcon: () -> Unit = { onValueChange("") },
) {
    val isFocused by interactionSource.collectIsFocusedAsState()
    val showIcon by rememberUpdatedState(value.isNotEmpty())

    OutlinedTextField(
        shape = shape,
        colors = colors,
        value = value,
        label = { Text(text = label) },
        onValueChange = onValueChange,
        textStyle = textStyle,
        modifier = modifier.fillMaxWidth(),
        enabled = config.enabled,
        readOnly = config.readOnly,
        visualTransformation = config.visualTransformation,
        keyboardOptions = config.keyboardOptions,
        keyboardActions = config.keyboardActions,
        interactionSource = interactionSource,
        singleLine = config.singleLine,
        maxLines = config.maxLines,
        minLines = config.minLines,
        leadingIcon = config.leadingIcon,
        isError = config.isError,
        trailingIcon = @Composable {
            AnimatedContent(
                targetState = config.showClearIcon && isFocused && showIcon,
            ) {
                if (it) {
                    ClearIconButton(
                        showClearIcon = true,
                        clearIcon = config.clearIcon,
                        onClickClearIcon = onClickClearIcon,
                    )
                } else {
                    config.trailingIcon?.invoke()
                }
            }
        },
        supportingText = config.errorText?.let {
            {
                Text(
                    modifier = Modifier.testTag("errorTag"),
                    text = it,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.error,
                )
            }
        },
    )
}

/**
 * A Material Design text field.
 *
 * This component is a variation of [OutlinedTextField] that provides a clear icon when the text field is focused and has content.
 * It also includes built-in error handling and styling.
 *
 * @param value The input text to be shown in the text field.
 * @param onValueChange The callback that is triggered when the input service updates the text. An
 * updated text comes as a parameter of the callback.
 * @param label The label to be displayed inside the text field container.
 * @param modifier The [Modifier] to be applied to this text field.
 * @param textStyle The style to be applied to the input text. Defaults to [LocalTextStyle.current].
 * @param interactionSource The [MutableInteractionSource] representing the stream of [Interaction]s
 * for this TextField. You can create and pass in your own remembered [MutableInteractionSource] if
 * you want to observe [Interaction]s and customize the appearance / behavior of this TextField in
 * different [Interaction]s.
 * @param config The configuration for the text field. See [RevTextFieldConfig] for more details.
 * @param onClickClearIcon The callback that is triggered when the clear icon is clicked. Defaults to clearing the text field.
 */
@Composable
fun RevTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = LocalTextStyle.current,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    config: RevTextFieldConfig = RevTextFieldConfig(),
    onClickClearIcon: () -> Unit = { onValueChange("") },
) {
    val isFocused by interactionSource.collectIsFocusedAsState()
    val showIcon by rememberUpdatedState(value.isNotEmpty())

    OutlinedTextField(
        value = value,
        label = { Text(text = label) },
        onValueChange = onValueChange,
        textStyle = textStyle,
        modifier = modifier.fillMaxWidth(),
        enabled = config.enabled,
        readOnly = config.readOnly,
        visualTransformation = config.visualTransformation,
        keyboardOptions = config.keyboardOptions,
        keyboardActions = config.keyboardActions,
        interactionSource = interactionSource,
        singleLine = config.singleLine,
        maxLines = config.maxLines,
        minLines = config.minLines,
        leadingIcon = config.leadingIcon,
        isError = config.isError,
        trailingIcon = @Composable {
            AnimatedContent(
                targetState = config.showClearIcon && isFocused && showIcon,
            ) {
                if (it) {
                    ClearIconButton(
                        showClearIcon = true,
                        clearIcon = config.clearIcon,
                        onClickClearIcon = onClickClearIcon,
                    )
                } else {
                    config.trailingIcon?.invoke()
                }
            }
        },
        supportingText = config.errorText.let {
            {
                Text(
                    modifier = Modifier.testTag("errorTag"),
                    text = it ?: "",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.error,
                )
            }
        },
    )
}

/**
 * A composable function that displays a clear icon button with an animation.
 * The button is only visible when [showClearIcon] is true.
 *
 * @param showClearIcon A boolean indicating whether to show the clear icon.
 * @param clearIcon The image vector for the clear icon.
 * @param onClickClearIcon A lambda function to be executed when the clear icon is clicked.
 * @param modifier A [Modifier] for this composable.
 */
@Composable
private fun ClearIconButton(
    showClearIcon: Boolean,
    clearIcon: ImageVector,
    onClickClearIcon: () -> Unit,
    modifier: Modifier = Modifier,
) {
    AnimatedVisibility(
        visible = showClearIcon,
        modifier = modifier,
    ) {
        IconButton(
            onClick = onClickClearIcon,
            modifier = Modifier.semantics {
                contentDescription = "clearIcon"
            },
        ) {
            Icon(
                imageVector = clearIcon,
                contentDescription = "trailingIcon",
                tint = MaterialTheme.colorScheme.onSurface,
            )
        }
    }
}

/**
 * Configuration data class for [RevOutlinedTextField] and [RevTextField].
 *
 * @param enabled Controls the enabled state of the text field. When `false`, the text field will
 * be disabled and not interactable.
 * @param showClearIcon Whether to display a clear icon when the text field has content and is focused.
 * @param readOnly Controls the editable state of the text field. When `true`, the text
 * field can not be modified, however, a user can focus it and copy text from it. Read-only text
 * fields are usually used to display pre-filled forms that user can not edit.
 * @param clearIcon The [ImageVector] to be used for the clear icon. Defaults to [Icons.Default.Close].
 * @param isError Indicates if the text field's current value is in error. If set to true, the
 * label, bottom indicator and trailing icon by default will be displayed in error color.
 * @param errorText The supporting text to be displayed when [isError] is true.
 * @param visualTransformation Transforms the visual representation of the input value.
 * For example, you can use [androidx.compose.ui.text.input.PasswordVisualTransformation] to create a password
 * text field. By default no visual transformation is applied.
 * @param keyboardActions Software keyboard actions that define the keyboard action trigger such as
 * [KeyboardActions.Default].
 * @param singleLine When set to true, this text field becomes a single horizontally scrolling
 * text field instead of wrapping onto multiple lines. The keyboard will be informed to not show
 * a return key as the [ImeAction]. Note that [maxLines] parameter will be ignored if
 * [singleLine] is set to true.
 * @param maxLines The maximum number of lines to be displayed in the text field.
 * @param minLines The minimum number of lines to be displayed in the text field.
 * @param keyboardOptions Software keyboard options that contains configuration such as
 * [KeyboardOptions.Default].
 * @param trailingIcon The optional trailing icon to be displayed at the end of the text field
 * container.
 * @param leadingIcon The optional leading icon to be displayed at the beginning of the text field
 * container.
 */
data class RevTextFieldConfig(
    val enabled: Boolean = true,
    val showClearIcon: Boolean = true,
    val readOnly: Boolean = false,
    val clearIcon: ImageVector = Icons.Default.Close,
    val isError: Boolean = false,
    val errorText: String? = null,
    val visualTransformation: VisualTransformation = VisualTransformation.None,
    val keyboardActions: KeyboardActions = KeyboardActions.Default,
    val singleLine: Boolean = true,
    val maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    val minLines: Int = 1,
    val keyboardOptions: KeyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
    val trailingIcon: @Composable (() -> Unit)? = null,
    val leadingIcon: @Composable (() -> Unit)? = null,
)