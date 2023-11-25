package com.example.tuspomodoro.Login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tuspomodoro.R
import com.example.tuspomodoro.ui.theme.TUSPomodoroTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen() {
    // Column that holds the entire login screen
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Box and Text elements to be placed above the logo


        Box(
            modifier = Modifier
                .width(294.dp)
                .height(78.dp)
                .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp, bottomStart = 40.dp, bottomEnd = 40.dp))
                .background(
                    Color(
                        red = 0.6431372761726379f,
                        green = 0.5803921818733215f,
                        blue = 0.3803921639919281f,
                        alpha = 1f
                    )
                )
        ) {
            Text(
                text = "TUS POMODORO",
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
                textDecoration = TextDecoration.None,
                letterSpacing = 0.sp,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(top = 8.dp) // Adjust the top padding to move the text down
                    .alpha(1f),
                color = Color(red = 1f, green = 1f, blue = 1f, alpha = 1f),
                fontWeight = FontWeight.Black,
                fontStyle = FontStyle.Normal,
            )
        }

        Spacer(modifier = Modifier.height(16.dp)) // Spacer for vertical spacing

        // Logo
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp) // Increase the size of the logo
                .background(MaterialTheme.colorScheme.background) // Set the background color to the theme's background color
                .padding(16.dp)
                .clip(MaterialTheme.shapes.medium)
        )

        Spacer(modifier = Modifier.height(16.dp)) // spacer for vertical spacing

        // Username field
        val usernameState = remember { mutableStateOf("") }
        OutlinedTextField(
            value = usernameState.value,
            onValueChange = { usernameState.value = it },
            label = { Text("Username") },
            leadingIcon = {
                Icon(Icons.Default.Person, contentDescription = null)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp)) // spacer for vertical spacing

        // Password field
        val passwordState = remember { mutableStateOf("") }
        OutlinedTextField(
            value = passwordState.value,
            onValueChange = { passwordState.value = it },
            label = { Text("Password") },
            leadingIcon = {
                Icon(Icons.Default.Lock, contentDescription = null)
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp)) // spacer for vertical spacing

        // Login button
        Button(
            onClick = {
                // Handle login logic here // TO DO
            },
            colors = ButtonDefaults.buttonColors( Color(red = 1f, green = 1f, blue = 1f, alpha = 1f)) // set the background color of the button to #8e5c1c color brand
        ) {
            Text("Login")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    TUSPomodoroTheme {
        LoginScreen()
    }
}
