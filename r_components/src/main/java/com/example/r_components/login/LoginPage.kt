package com.example.r_components.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.r_components.button.RevButton
import com.example.r_components.text_fields.RevOutlinedTextField
import com.example.r_components.text_fields.RevPasswordField
import com.example.r_components.text_fields.RevTextFieldConfig

@Composable
fun LoginPage(
    logo: @Composable () -> Unit,
    userName: String,
    password:String,
    onUserNameChanged:(String)->Unit,
    onPasswordChanged:(String)->Unit,
    userNameError:String?=null,
    passwordError:String?=null,
    isPasswordVisible: Boolean=false,
    onPasswordVisibilityChange: (Boolean) -> Unit,
    onLoginClick:()->Unit,
) {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        logo()

        Spacer(modifier = Modifier.height(24.dp))

        RevOutlinedTextField(
            value = userName,
            onValueChange = {
                onUserNameChanged(it)
            },
            label = "User Name",
            config = RevTextFieldConfig(
                isError = userNameError!=null,
                errorText = userNameError.takeIf { userNameError!=null }
                    ?.let { userNameError },
                trailingIcon = if (userNameError!=null) {
                    {
                        Icon(
                            imageVector = Icons.Default.Error,
                            contentDescription = "Error",
                        )
                    }
                } else {
                    null
                },
            ),
            modifier = Modifier.fillMaxWidth(),
        )

        RevPasswordField(
            label = "Password",
            value = password,
            onValueChange = {
                onPasswordChanged(it)
            },
            modifier = Modifier.fillMaxWidth(),
            showPassword = isPasswordVisible,
            showPasswordChange = {
                onPasswordVisibilityChange(it)
            },
            isError = passwordError!=null,
            hint = passwordError.takeIf { passwordError!=null }?.let { passwordError },
        )

        RevButton(
            onClick = onLoginClick,
            enabled = userName.isNotEmpty() && password.isNotEmpty(),
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Login")
        }
    }
}