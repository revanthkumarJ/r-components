package com.example.r_components.text_fields

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * A Composable function that displays a password input field.
 * This version of [RevPasswordField] allows for external control of the password visibility state.
 *
 * @param label The label to be displayed for the text field.
 * @param value The current value of the text field.
 * @param showPassword A boolean indicating whether the password should be visible.
 * @param showPasswordChange A callback function invoked when the visibility of the password changes.
 * It receives a boolean parameter indicating the new visibility state.
 * @param onValueChange A callback function invoked when the value of the text field changes.
 * It receives the new string value as a parameter.
 * @param modifier The modifier to be applied to the text field.
 * @param shape The shape of the text field's outline.
 * @param colors The colors to be used for the text field in different states.
 * @param isError A boolean indicating whether the text field is in an error state.
 * @param readOnly A boolean indicating whether the text field is read-only.
 * @param singleLine A boolean indicating whether the text field should be a single line.
 * @param hint An optional hint text to be displayed when the text field is empty.
 * @param showPasswordTestTag An optional test tag for the show/hide password icon, useful for UI testing.
 * @param autoFocus A boolean indicating whether the text field should automatically request focus when composed.
 * @param keyboardType The type of keyboard to be displayed. Defaults to [KeyboardType.Password].
 * @param imeAction The IME action to be displayed on the keyboard.
 * @param keyboardActions The actions to be performed when an IME action is triggered.
 */
@Composable
fun RevPasswordField(
    label: String,
    value: String,
    showPassword: Boolean,
    showPasswordChange: (Boolean) -> Unit,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(12.dp),
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = MaterialTheme.colorScheme.secondaryContainer,
        unfocusedBorderColor = MaterialTheme.colorScheme.secondaryContainer,
        errorBorderColor = MaterialTheme.colorScheme.error,
    ),
    isError: Boolean = false,
    readOnly: Boolean = false,
    singleLine: Boolean = true,
    hint: String? = null,
    showPasswordTestTag: String? = null,
    autoFocus: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Password,
    imeAction: ImeAction = ImeAction.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    val focusRequester = remember { FocusRequester() }
    RevOutlinedTextField(
        modifier = modifier
            .focusRequester(focusRequester),
        shape = shape,
        colors = colors,
        label = label,
        value = value,
        onValueChange = onValueChange,
        config = RevTextFieldConfig(
            visualTransformation = when {
                !showPassword -> PasswordVisualTransformation()
                readOnly -> nonLetterColorVisualTransformation()
                else -> VisualTransformation.None
            },
            singleLine = singleLine,
            readOnly = readOnly,
            isError = isError,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction,
            ),
            showClearIcon = false,
            keyboardActions = keyboardActions,
            errorText = hint,
            trailingIcon = {
                val color = if (isError) {
                    MaterialTheme.colorScheme.error
                } else {
                    MaterialTheme
                        .colorScheme.onSurface
                }
                IconButton(
                    onClick = { showPasswordChange.invoke(!showPassword) },
                ) {
                    val imageVector = if (showPassword) {
                        Icons.Default.VisibilityOff
                    } else {
                        Icons.Default.Visibility
                    }

                    Icon(
                        modifier = Modifier.semantics { showPasswordTestTag?.let { testTag = it } },
                        imageVector = imageVector,
                        contentDescription = "togglePassword",
                        tint = color,
                    )
                }
            },
        ),
    )
    if (autoFocus) {
        LaunchedEffect(Unit) { focusRequester.requestFocus() }
    }
}

/**
 * A Composable function that displays a password field with a toggle to show/hide the password.
 * This version of [RevPasswordField] manages its own `showPassword` state.
 *
 * @param label The label to be displayed above the text field.
 * @param value The current text value of the password field.
 * @param onValueChange A callback function invoked when the text value changes.
 * @param modifier The modifier to be applied to the text field.
 * @param shape The shape of the text field's outline.
 * @param colors The colors to be used for the text field in different states.
 * @param isError A boolean indicating whether the text field is in an error state.
 * @param readOnly A boolean indicating whether the text field is read-only.
 * @param singleLine A boolean indicating whether the text field should be a single line.
 * @param hint An optional hint text to be displayed when the text field is empty.
 * @param initialShowPassword A boolean indicating whether the password should be initially visible.
 * @param showPasswordTestTag An optional test tag for the show/hide password icon, useful for UI testing.
 * @param autoFocus A boolean indicating whether the text field should automatically request focus when it appears.
 * @param keyboardType The type of keyboard to be displayed when the text field is focused.
 * @param imeAction The IME action to be displayed on the keyboard.
 * @param keyboardActions Actions to be performed when specific IME actions are triggered.
 */
@Composable
fun RevPasswordField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = OutlinedTextFieldDefaults.shape,
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(),
    isError: Boolean = false,
    readOnly: Boolean = false,
    singleLine: Boolean = true,
    hint: String? = null,
    initialShowPassword: Boolean = false,
    showPasswordTestTag: String? = null,
    autoFocus: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Password,
    imeAction: ImeAction = ImeAction.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    var showPassword by rememberSaveable { mutableStateOf(initialShowPassword) }
    RevPasswordField(
        modifier = modifier,
        label = label,
        shape = shape,
        colors = colors,
        isError = isError,
        value = value,
        showPassword = showPassword,
        showPasswordChange = { showPassword = !showPassword },
        onValueChange = onValueChange,
        readOnly = readOnly,
        singleLine = singleLine,
        hint = hint,
        showPasswordTestTag = showPasswordTestTag,
        autoFocus = autoFocus,
        keyboardType = keyboardType,
        imeAction = imeAction,
        keyboardActions = keyboardActions,
    )
}

/**
 * A [VisualTransformation] that colors digits and special characters differently.
 * Digits are colored with the primary color from the MaterialTheme.
 * Special characters (non-letters and non-digits) are colored with the error color from the MaterialTheme.
 *
 * @return A [VisualTransformation] that applies the described coloring.
 */
@Composable
fun nonLetterColorVisualTransformation(): VisualTransformation {
    val digitColor = MaterialTheme.colorScheme.primary
    val specialCharacterColor = MaterialTheme.colorScheme.error
    return remember(digitColor, specialCharacterColor) {
        NonLetterColorVisualTransformation(
            digitColor = digitColor,
            specialCharacterColor = specialCharacterColor,
        )
    }
}

/**
 * A [VisualTransformation] that colors digits and special characters in a text input.
 * Letters remain the default text color.
 *
 * This is useful for password fields where the password is shown, to visually distinguish
 * different types of characters.
 *
 * @param digitColor The color to use for digit characters.
 * @param specialCharacterColor The color to use for special characters (non-letters and non-digits).
 */
private class NonLetterColorVisualTransformation(
    private val digitColor: Color,
    private val specialCharacterColor: Color,
) : VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText =
        TransformedText(
            buildTransformedAnnotatedString(text.toString()),
            OffsetMapping.Identity,
        )

    private fun buildTransformedAnnotatedString(text: String): AnnotatedString {
        val builder = AnnotatedString.Builder()
        text.toCharArray().forEach { char ->
            when {
                char.isDigit() -> builder.withStyle(SpanStyle(color = digitColor)) { append(char) }

                !char.isLetter() -> {
                    builder.withStyle(SpanStyle(color = specialCharacterColor)) { append(char) }
                }

                else -> builder.append(char)
            }
        }
        return builder.toAnnotatedString()
    }
}

@Preview(showBackground = true)
@Composable
private fun RevPasswordField_preview_withInput_hidePassword() {
    RevPasswordField(
        label = "Label",
        value = "Password",
        onValueChange = {},
        initialShowPassword = false,
        hint = "Hint",
    )
}

@Preview(showBackground = true)
@Composable
private fun RevPasswordField_preview_withInput_showPassword() {
    RevPasswordField(
        label = "Label",
        value = "Password",
        onValueChange = {},
        initialShowPassword = true,
        hint = "Hint",
    )
}

@Preview(showBackground = true)
@Composable
private fun RevPasswordField_preview_withoutInput_hidePassword() {
    RevPasswordField(
        label = "Label",
        value = "",
        onValueChange = {},
        initialShowPassword = false,
        hint = "Hint",
    )
}

@Preview(showBackground = true)
@Composable
private fun RevPasswordField_preview_withoutInput_showPassword() {
    RevPasswordField(
        label = "Label",
        value = "",
        onValueChange = {},
        initialShowPassword = true,
        hint = "Hint",
    )
}