package com.example.tuspomodoro.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tuspomodoro.R
import com.example.tuspomodoro.ui.theme.CustomColor
import com.example.tuspomodoro.ui.theme.TUSPomodoroTheme

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController:NavController, viewModel: AuthRegViewModel = viewModel()) {
    var emailState by remember{mutableStateOf("")}
    var passwordState by remember{mutableStateOf("")}
    val errorMessage by viewModel.errorMessage.observeAsState()

    val userId = viewModel.userId.observeAsState()

    // Column that holds the entire login screen
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (errorMessage != null) {
            AlertDialog(
                onDismissRequest = { viewModel.errorMessage.value = null },
                title = { Text("Error") },
                text = { Text(text = errorMessage!!) },
                confirmButton = {
                    TextButton(onClick = { viewModel.errorMessage.value = null }) {
                        Text(text = "OK")
                    }
                }
            )
        }
        // Box and Text elements above the logo
        Button(
            onClick = {
                // TO DO 
            },
            modifier = Modifier
                .width(294.dp)
                .height(78.dp)
                .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp, bottomStart = 40.dp, bottomEnd = 40.dp)),
            colors = ButtonDefaults.buttonColors(containerColor = CustomColor, contentColor = Color.White),

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
                    .padding(top = 18.dp)
                    .alpha(1f),
                color = Color.White, // Text color
                fontWeight = FontWeight.Black,
                fontStyle = FontStyle.Normal,
            )
        }

        // Spacer for vertical spacing
        Spacer(modifier = Modifier.height(16.dp))

        // Logo ref: I got the image from the TUS branding
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .background(MaterialTheme.colorScheme.background) 
                .padding(16.dp)
                .clip(MaterialTheme.shapes.medium)
        )

        // Spacer for vertical spacing
        Spacer(modifier = Modifier.height(16.dp))

        // Email Field
        OutlinedTextField(
            value = emailState,
            onValueChange = { emailState = it },
            label = { Text("Email") },
            leadingIcon = {
                Icon(Icons.Default.Person, contentDescription = null)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        // Spacer for vertical spacing
        Spacer(modifier = Modifier.height(16.dp))

        // Password field
        OutlinedTextField(
            value = passwordState,
            onValueChange = { passwordState = it },
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

        // Spacer for vertical spacing
        Spacer(modifier = Modifier.height(16.dp))

        // Login button 
        Button(
            onClick = { viewModel.loginUser(emailState.lowercase(), passwordState)

                // Navigate to the Pomodoro screen if the userId is not null
                if (userId.value != null) {
                    navController.navigate("pomodoro/${userId.value}")
                } },
            modifier = Modifier.padding(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = CustomColor, contentColor = Color.White),
        ) {
            Text(text = "Login")
        }

        // Login Page Button
        Button(
            onClick = { navController.navigate(Screen.SignUpScreen.route) },
            modifier = Modifier.padding(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent, contentColor = Color.Black),
        ) {
            Text(
                text = "Don't have an account, Register",
                modifier = Modifier
                    .padding(4.dp)
            )
        }
    }



}



@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    TUSPomodoroTheme {
        val navController = rememberNavController()
        LoginScreen(navController)
    }
}