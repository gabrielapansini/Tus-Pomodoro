package com.example.tuspomodoro.ui

import FooterIcon
import android.os.CountDownTimer
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tuspomodoro.R
import com.example.tuspomodoro.ui.theme.CustomColor

@Composable
fun Pomodoro(navController: NavController, userId: String?) {
    var isTimerRunning by remember { mutableStateOf(false) }
    var initialDuration = remember { 25 * 60 * 1000L }
    var timeRemaining by remember { mutableStateOf(initialDuration) }

    var countDownTimer: CountDownTimer? by remember { mutableStateOf(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Transparent),
        contentAlignment = Alignment.TopCenter // Align content to the top center
    ) {
        // Image with background
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Transparent),
            contentScale = ContentScale.Crop
        )

        // Button
        Button(
            onClick = {
                navController.navigate(Screen.ToDoListScreen.route)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(78.dp)
                .clip(
                    RoundedCornerShape(
                        topStart = 40.dp,
                        topEnd = 40.dp,
                        bottomStart = 40.dp,
                        bottomEnd = 40.dp
                    )
                )
                .padding(top = 18.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = CustomColor,
                contentColor = Color.White
            ),
        ) {
            Text(
                text = "TO DO LIST",
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
                textDecoration = TextDecoration.None,
                letterSpacing = 0.sp,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(top = 8.dp)
                    .alpha(1f),
                color = Color.White,
                fontWeight = FontWeight.Black,
                fontStyle = FontStyle.Normal,
            )
        }

        // Circle background
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Gray.copy(alpha = 0.2f))
        ) {
            val circleRadius = (size.minDimension - 2 * 16.dp.toPx()) / 2
            drawCircle(
                color = Color.Transparent,
                radius = circleRadius,
                center = center
            )
        }

        // Timer circle
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            val circleRadius = (size.minDimension - 2 * 16.dp.toPx()) / 2

            // Draw the transparent circle
            drawCircle(
                color = Color.Transparent,
                radius = circleRadius,
                center = center
            )

            // Draw the static black border
            drawCircle(
                color = Color.White,
                radius = circleRadius,
                center = center,
                style = Stroke(width = 4.dp.toPx())
            )
        }

        // Box and Text elements
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(top = 300.dp), //  padding to move content down
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Start/Pause Button
            Button(
                onClick = {
                    if (!isTimerRunning) {
                        countDownTimer = createTimer(timeRemaining, 1000) { millisUntilFinished ->
                            timeRemaining = millisUntilFinished
                        }
                        countDownTimer?.start()
                    } else {
                        countDownTimer?.cancel()
                    }
                    isTimerRunning = !isTimerRunning
                },
                modifier = Modifier
                    .size(78.dp)
                    .clip(CircleShape),
                colors = ButtonDefaults.buttonColors(
                    containerColor = CustomColor,
                    contentColor = Color.White
                ),
            ) {
                Icon(
                    imageVector = if (isTimerRunning) Icons.Default.PlayArrow else Icons.Default.PlayArrow,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(40.dp)
                )
            }

            // Spacer to push down the Start/Pause Button and Timer
            Spacer(modifier = Modifier.height(16.dp))

            // Timer text
            Text(
                text = formatTime(timeRemaining),
                fontSize = 48.sp,
                color = Color.White,
                modifier = Modifier.alpha(0.8f)
            )

            // Spacer for vertical spacing
            Spacer(modifier = Modifier.height(16.dp))

            // User ID text
            Text(text = "User ID: $userId")

            Spacer(modifier = Modifier.height(40.dp))

            Spacer(modifier = Modifier.weight(1f)) // Spacer to push the footer menu to the bottom

            // Footer menu
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                FooterIcon(imageVector = Icons.Default.Home, color = CustomColor) {
                    navController.navigate(Screen.PomodoroScreen.route)
                }
                FooterIcon(imageVector = Icons.Default.DateRange, color = CustomColor) {
                    navController.navigate(Screen.TimeTableScreen.route)
                }
                FooterIcon(imageVector = Icons.Default.Check, color = CustomColor) {
                    navController.navigate(Screen.ToDoListScreen.route)
                }
                FooterIcon(imageVector = Icons.Default.Phone, color = CustomColor) {
                    navController.navigate(Screen.Contact.route)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FooterIcon(imageVector: ImageVector, color: Color, onClick: () -> Unit) {
    Surface(
        color = Color.Transparent,
        onClick = onClick,
        modifier = Modifier
            .size(40.dp)
            .padding(8.dp)
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = null,
            tint = color,
            modifier = Modifier.size(40.dp)
        )
    }
}

// Function to format time in mm:ss format
private fun formatTime(millis: Long): String {
    val minutes = millis / 1000 / 60
    val seconds = (millis / 1000) % 60
    return "%02d:%02d".format(minutes, seconds)
}

private fun createTimer(
    millisInFuture: Long,
    countDownInterval: Long,
    onTick: (Long) -> Unit
): CountDownTimer {
    return object : CountDownTimer(millisInFuture, countDownInterval) {
        override fun onTick(millisUntilFinished: Long) {
            onTick.invoke(millisUntilFinished)
        }

        override fun onFinish() {

        }
    }
}

@Preview
@Composable
fun PreviewPomodoro() {
    MaterialTheme {
        Surface {
            val navController = rememberNavController()
            Pomodoro(navController, "data")
        }
    }
}
