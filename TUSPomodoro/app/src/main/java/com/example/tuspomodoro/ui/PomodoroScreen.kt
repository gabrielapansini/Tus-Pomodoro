package com.example.tuspomodoro.ui

import android.os.CountDownTimer
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
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
fun CustomBoxWithText(navController: NavController, userId: String?) {

    var isTimerRunning by remember { mutableStateOf(false) }
    var initialDuration = remember { 25 * 60 * 1000L }
    var timeRemaining by remember { mutableStateOf(initialDuration) }


    var countDownTimer: CountDownTimer? by remember { mutableStateOf(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Transparent)
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

        // Box and Text elements to be placed above the logo
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
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
                    .width(294.dp)
                    .height(78.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = 40.dp,
                            topEnd = 40.dp,
                            bottomStart = 40.dp,
                            bottomEnd = 40.dp
                        )
                    ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = CustomColor,
                    contentColor = Color.White
                ),
            ) {
                // Button content
                Text(
                    text = if (isTimerRunning) "Pause" else "Start",
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
                    color = Color.White,
                    fontWeight = FontWeight.Black,
                    fontStyle = FontStyle.Normal,
                )

            }

            // Spacer for vertical spacing
            Spacer(modifier = Modifier.height(8.dp))

            // Circle
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f)
            ) {
                val circleRadius =
                    (size.minDimension - 2 * 5.5f) / 2
                drawCircle(
                    color = Color.Transparent,
                    radius = circleRadius,
                    center = center
                )
                drawCircle(
                    color = Color.White,
                    radius = circleRadius,
                    center = center,
                    style = Stroke(width = 5.5f, miter = 4f, join = StrokeJoin.Round)
                )
            }


            // Spacer after Circle
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "User ID: $userId")

            // Start Button
            Button(
                onClick = {

                },
                modifier = Modifier
                    .size(78.dp)
                    .clip(RoundedCornerShape(50))
                    .offset(y = (-12).dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = CustomColor,
                    contentColor = Color.White
                ),
            ) {
                Icon(
                    imageVector = if (isTimerRunning) Icons.Default.PlayArrow else Icons.Default.PlayArrow,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )

                // Footer menu
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    FooterIcon(imageVector = Icons.Default.Home, color = CustomColor)
                    FooterIcon(imageVector = Icons.Default.DateRange, color = CustomColor)
                    FooterIcon(imageVector = Icons.Default.Check, color = CustomColor)
                    FooterIcon(imageVector = Icons.Default.Favorite, color = CustomColor)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FooterIcon(imageVector: ImageVector, color: Color) {
    Surface(
        color = Color.Transparent,
        onClick = {
            // Handle icon click
        },
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
fun PreviewCustomBoxWithText() {
    MaterialTheme {
        Surface {
            val navController = rememberNavController()
            CustomBoxWithText(navController,"data")
        }
    }
}
