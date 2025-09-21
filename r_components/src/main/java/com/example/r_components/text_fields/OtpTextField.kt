package com.example.r_components.text_fields

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * A Composable function that displays a text field for entering an OTP (One-Time Password).
 *
 * @param onOtpTextCorrectlyEntered A callback function that is invoked when the OTP is entered correctly.
 * @param modifier A [Modifier] to be applied to the OTP text field.
 * @param realOtp The actual OTP that the user needs to enter. Defaults to an empty string.
 * @param otpCount The number of digits in the OTP. Defaults to 4.
 */
@Composable
fun RevOtpTextField(
    onOtpTextCorrectlyEntered: () -> Unit,
    modifier: Modifier = Modifier,
    realOtp: String = "",
    otpCount: Int = 4,
) {
    var otpText by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        BasicTextField(
            modifier = Modifier,
            value = TextFieldValue(otpText, selection = TextRange(otpText.length)),
            onValueChange = {
                otpText = it.text
                isError = false
                if (otpText.length == otpCount) {
                    if (otpText != realOtp) {
                        isError = true
                    } else {
                        onOtpTextCorrectlyEntered.invoke()
                    }
                }
            },
            keyboardActions = KeyboardActions(
                onDone = {
                    if (otpText != realOtp) {
                        isError = true
                    } else {
                        onOtpTextCorrectlyEntered.invoke()
                    }
                    println("OTP: $otpText and $isError")
                },
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done,
            ),
            decorationBox = {
                Row(horizontalArrangement = Arrangement.Center) {
                    repeat(otpCount) { index ->
                        CharView(
                            index = index,
                            text = otpText,
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            },
        )
        if (isError) {
            Text(
                text = "Invalid OTP",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Red,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 8.dp),
            )
        }
    }
}

/**
 * Composable function to display a single character of the OTP.
 * It shows an underscore "_" if the character is not yet entered or if it's the current input position.
 * The currently focused character (where the next input will go) is highlighted.
 *
 * @param index The position of this character view in the OTP sequence.
 * @param text The current OTP text entered by the user.
 * @param modifier Optional [Modifier] for styling and layout.
 */
@Composable
private fun CharView(
    index: Int,
    text: String,
    modifier: Modifier = Modifier,
) {
    val isFocused = text.length == index
    val char = when {
        index == text.length -> "_"
        index > text.length -> "_"
        else -> text[index].toString()
    }
    Text(
        modifier = modifier
            .width(40.dp)
            .wrapContentHeight(align = Alignment.CenterVertically),
        text = char,
        style = MaterialTheme.typography.headlineSmall,
        color = if (isFocused) {
            Color.DarkGray
        } else {
            Color.LightGray
        },
        textAlign = TextAlign.Center,
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewOtpTextField() {
    RevOtpTextField(
        onOtpTextCorrectlyEntered = {},
        realOtp = "1234",
        otpCount = 4,
    )
}