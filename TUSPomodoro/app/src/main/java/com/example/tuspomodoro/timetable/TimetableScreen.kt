package com.example.tuspomodoro.timetable

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tuspomodoro.ui.theme.CustomColor

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface {
                    TimetableScreen()
                }
            }
        }
    }
}

@Composable
fun TimetableScreen() {
    var selectedDepartment by remember { mutableStateOf("") }
    var selectedGroup by remember { mutableStateOf("") }
    var selectedWeek by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
            .padding(16.dp)
    ) {
        TUSPomodoroButton()

        Spacer(modifier = Modifier.height(16.dp))

        TimetableSelection(
            departments = listOf("Department A", "Department B", "Department C"),
            groups = listOf("Group 1", "Group 2", "Group 3"),
            weeks = listOf("Week 1", "Week 2", "Week 3"),
            onDepartmentSelected = { selectedDepartment = it },
            onGroupSelected = { selectedGroup = it },
            onWeekSelected = { selectedWeek = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // Handle View Timetable click
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .clip(RoundedCornerShape(12.dp)),
            colors = ButtonDefaults.buttonColors(containerColor = CustomColor, contentColor = Color.White),
        ) {
            Text(
                text = "View Timetable",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 12.dp),
            )
        }

        // Add timetable content here
    }
}

@Composable
fun TimetableSelection(
    departments: List<String>,
    groups: List<String>,
    weeks: List<String>,
    onDepartmentSelected: (String) -> Unit,
    onGroupSelected: (String) -> Unit,
    onWeekSelected: (String) -> Unit
) {
    Column {
        TimetableDropdown(
            label = "Select Department",
            items = departments,
            onItemSelected = onDepartmentSelected
        )

        Spacer(modifier = Modifier.height(8.dp))

        TimetableDropdown(
            label = "Select Group",
            items = groups,
            onItemSelected = onGroupSelected
        )

        Spacer(modifier = Modifier.height(8.dp))

        TimetableDropdown(
            label = "Select Week",
            items = weeks,
            onItemSelected = onWeekSelected
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimetableDropdown(
    label: String,
    items: List<String>,
    onItemSelected: (String) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf(items.firstOrNull()) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        OutlinedTextField(
            value = selectedItem ?: "",
            onValueChange = {},
            label = { Text(label) },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                cursorColor = Color.White, // Customize cursor color if needed
                focusedBorderColor = Color.White, // Customize focused border color
                unfocusedBorderColor = Color.White.copy(alpha = 0.12f) // Customize unfocused border color
            ),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = null
                )
            }
        )

        

    }
        }

@Composable
fun TUSPomodoroButton() {
    Button(
        onClick = {
            // Handle TUS POMODORO click
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(78.dp)
            .clip(RoundedCornerShape(40.dp)),
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
                .fillMaxSize()
                .padding(top = 18.dp),
            color = Color.White,
            fontWeight = FontWeight.Black,
            fontStyle = FontStyle.Normal,
        )
    }
}

@Preview
@Composable
fun PreviewTimetableScreen() {
    MaterialTheme {
        Surface {
            TimetableScreen()
        }
    }
}
