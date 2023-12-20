package com.example.tuspomodoro.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tuspomodoro.ui.theme.CustomColor
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


@Composable
fun TimetableScreen(navController: NavController, ViewModel: TimeTableViewModel = viewModel()) {
    // Black background
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
    ) {

        TUSPomodoroButton()

        Spacer(modifier = Modifier.height(16.dp))

        // Search
        TimetableSelection(ViewModel)

        // Display
        Timetable(ViewModel)

        Spacer(modifier = Modifier.height(26.dp))

        // Spacer to push the FooterMenu down
        Spacer(modifier = Modifier.weight(1f))

        // Footer menu
        FooterMenu(navController)
    }
}

@Composable
fun FooterMenu(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimetableSelection(ViewModel: TimeTableViewModel = viewModel()) {


    var department by remember{mutableStateOf("")}
    var group by remember{mutableStateOf("")}
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {

        //Department
        OutlinedTextField(
            value = department,
            onValueChange = {department=it},
            isError = false,
            keyboardOptions = KeyboardOptions.Default,
            label =  { Text("Department",color = Color.White,) },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.White,
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
        Spacer(modifier = Modifier.height(15.dp))

        //Group
        OutlinedTextField(
            value = group,
            onValueChange = {group=it},
            isError = false,
            keyboardOptions = KeyboardOptions.Default,
            label =  { Text("Group",color = Color.White,) },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.White,
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
        Spacer(modifier = Modifier.height(22.dp))
        val coroutineScope = rememberCoroutineScope()
        val weeklySchedule = remember { mutableStateOf<Map<String, DaySchedule>?>(null) }
        Button(
            onClick = {
                //Validation - Can be edited to do another thing
                if (department.isNullOrEmpty() || group.isNullOrEmpty()) {
                    return@Button
                }
                coroutineScope.launch {
                    weeklySchedule.value = ViewModel.getWeeklySchedule(department, group).first()
                    // Do something with weeklySchedule
                }
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
        Spacer(modifier = Modifier.height(24.dp))





        //Time Table

        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp)
            ) {
                Text(text = "Dept - IT",color = Color.White, modifier = Modifier.weight(1f), fontSize = 15.sp)
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "Group - ISD",color = Color.White,modifier = Modifier.weight(1f), fontSize = 15.sp)
            }
            Spacer(modifier = Modifier.height(24.dp))
            //Assuming 5 classes is the maximum classes we can have as students
            Row {
                Surface(modifier = Modifier
                    .weight(1f)
                    .size(50.dp), color = Color.Transparent) {

                }
                Surface(modifier = Modifier
                    .weight(1f)
                    .size(50.dp)) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(text = "8:00 - 10:30", fontSize = 14.sp)
                    }

                }
                Surface(modifier = Modifier
                    .weight(1f)
                    .size(50.dp)) {

                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(text = "11:00 - 13:30", fontSize = 14.sp)
                    }
                }
                Surface(modifier = Modifier
                    .weight(1f)
                    .size(50.dp)) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(text = "14:00 - 16:00", fontSize = 14.sp)
                    }

                }
                Surface(modifier = Modifier
                    .weight(1f)
                    .size(50.dp)) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(text = "16:30 - 19:00", fontSize = 14.sp)
                    }

                }
                Surface(modifier = Modifier
                    .weight(1f)
                    .size(50.dp)) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(text = "19:30 - 21:30", fontSize = 14.sp)
                    }
                }
            }


            weeklySchedule.value?.let { schedule ->
                for ((day, daySchedule) in schedule) {

                    val classes = listOf(daySchedule.FirstClass, daySchedule.SecondClass, daySchedule.ThirdClass, daySchedule.FourthClass, daySchedule.FifthClass)


                    //Time Table data
                    Row {
                        //Day
                        Surface(modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .size(95.dp),) {

                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {

                                Text(text = day , fontSize = 10.sp)
                            }

                        }
                        for (classDetails in classes) {
                            // Display classDetails data

                            //First Class
                            Surface(modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .size(95.dp)) {
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Row {
                                        Text(text = " ${classDetails.Subject} ", fontSize = 10.sp,lineHeight = 8.sp, color = Color.Red)
                                    }
                                    Spacer(modifier = Modifier.height(1.dp))
                                    Row {
                                        Text(text = " ${classDetails.LectureHall} ", fontSize = 10.sp,lineHeight = 8.sp)
                                    }
                                    Row {
                                        Text(text = " ${classDetails.LectureType} ", fontSize = 10.sp,lineHeight = 8.sp, color = Color.Green)
                                    }
                                    Spacer(modifier = Modifier.height(1.dp))
                                    Row {
                                        Text(text = " ${classDetails.Lecturer} ", fontSize = 10.sp,lineHeight = 8.sp)
                                    }
                                    Spacer(modifier = Modifier.height(1.dp))

                                }

                            }
                        }


                    }
                }
            }


        }

        Spacer(modifier = Modifier.height(26.dp))

    }
}


@Composable
fun Timetable(viewModel: TimeTableViewModel = viewModel()) {




}


@Preview
@Composable
fun PreviewTimetableScreen() {
    MaterialTheme {
        Surface {
            val navController = rememberNavController()
            TimetableScreen(navController)
        }
    }
}
