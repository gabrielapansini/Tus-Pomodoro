import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
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
import com.example.tuspomodoro.ui.Screen
import com.example.tuspomodoro.ui.theme.CustomColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoList(navController: NavController) {
    val tasks = remember { mutableStateOf(mutableListOf("Task 1", "Task 2", "Task 3")) }
    var newTask by remember { mutableStateOf("") }


    //function Add working now
    fun addTask() {
        if (newTask.isNotBlank()) {
            tasks.value = tasks.value.toMutableList().apply {
                add(newTask)
            }
            newTask = ""
        }
    }

    //function delete working now
    fun deleteTask(task: String) {
        try {
            tasks.value = tasks.value.toMutableList().apply {
                remove(task)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(64.dp))

        TUSPomodoroButton()

        Spacer(modifier = Modifier.height(40.dp))

        LazyColumn {
            items(tasks.value.size) { index ->
                ToDoListItem(
                    task = tasks.value[index],
                    onCompletion = {},
                    onDelete = { deleteTask(tasks.value[index]) }
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = newTask,
                onValueChange = { newTask = it },
                label = { Text("New Task", color = Color.White) },
                singleLine = true,
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = { addTask() },
                modifier = Modifier.size(width = 100.dp, height = 40.dp),
                colors = ButtonDefaults.run { buttonColors(containerColor = CustomColor, contentColor = Color.White) }
            ) {
                Text(
                    text = "Add Task",
                    color = Color.White,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f)) // Spacer to push the footer menu to the bottom

        // Footer menu
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



@Composable
fun TUSPomodoroButton() {
    Button(
        onClick = {
            // TO DO
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(78.dp)
            .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp, bottomStart = 40.dp, bottomEnd = 40.dp)),
        colors = ButtonDefaults.buttonColors(containerColor = CustomColor, contentColor = Color.White),
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
                .padding(top = 18.dp)
                .alpha(1f),
            color = Color.White,
            fontWeight = FontWeight.Black,
            fontStyle = FontStyle.Normal,
        )
    }
}

@Composable
fun ToDoListItem(task: String, onCompletion: () -> Unit, onDelete: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {


        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = task,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f),
            color = Color.White

        )

        Spacer(modifier = Modifier.width(8.dp))

        Button(
            onClick = onDelete,
            modifier = Modifier.size(width = 100.dp, height = 40.dp),
            colors = ButtonDefaults.buttonColors(Color.Red)
        ) {
            Text(
                text = "Delete",
                color = Color.White,
                style = MaterialTheme.typography.bodySmall
            )


        }
    }
}

@Preview
@Composable
fun PreviewToDoList() {
    MaterialTheme {
        Surface {
            val navController = rememberNavController()
            ToDoList(navController)
        }
    }
}
