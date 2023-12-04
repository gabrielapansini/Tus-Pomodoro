package com.example.tuspomodoro.timetable

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

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

        Spacer(modifier = Modifier.height(16.dp))

        // Dummy timetable data
        val timetableEntries = timetableEntries()


// Dummy timetable display
        Timetable(entries = timetableEntries)


    }
}

fun timetableEntries(): List<TimetableEntry> {
    return listOf(
        TimetableEntry("Software Dev", "Mon", "10AM-12AM"),
        TimetableEntry("Software Dev", "Tues", "11AM-12AM"),
        TimetableEntry("OOP", "Wed", "12AM-14AM"),
        TimetableEntry("Mobile Dev", "Fri", "10AM-12AM"),
    )}
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
        colors = ButtonDefaults.buttonColors(
            containerColor = CustomColor,
            contentColor = Color.White
        ),
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
                cursorColor = Color.White,
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White.copy(alpha = 0.12f) 
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
fun Timetable(entries: List<TimetableEntry>) {
    // Group timetable entries by day and time
    val groupedEntries = entries.groupBy { it.day to it.time }

    val days = entries.map { it.day }.distinct().sorted()
    val times = entries.map { it.time }.distinct().sorted()

    // Table header
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.width(80.dp))

        // Weekday headers
        for (day in days) {
            Text(
                text = day,
                color = Color.White,
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )
        }
    }

    // Table content
    for (time in times) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Time column
            Text(
                text = time,
                color = Color.White,
                modifier = Modifier
                    .padding(8.dp, 0.dp, 8.dp, 0.dp)
                    .wrapContentSize(Alignment.Center)
            )

            // Timetable entries for each day
            for (day in days) {
                val entry = groupedEntries[day to time]?.getOrNull(0)
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp)
                        .fillMaxWidth()
                        .heightIn(min = 40.dp)
                        .background(Color.Gray, RoundedCornerShape(4.dp))
                ) {
                    Text(
                        text = entry?.course ?: "",
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}


data class TimetableEntry(
    val course: String,
    val day: String,
    val time: String
)

@Preview
@Composable
fun PreviewTimetableScreen() {
    MaterialTheme {
        Surface {
            TimetableScreen()
        }
    }
}
