import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tuspomodoro.ui.theme.CustomColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoList() {
    val tasks = remember { mutableStateOf(mutableListOf("Task 1", "Task 2", "Task 3")) }
    var newTask by remember { mutableStateOf("") }

    fun addTask() {
        if (newTask.isNotBlank()) {
            tasks.value.add(newTask)
            newTask = ""
        }
    }

    fun deleteTask(task: String) {
        tasks.value.remove(task)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
            .padding(16.dp)
    ) {
        // Spacer for vertical spacing
        Spacer(modifier = Modifier.height(64.dp))

        TUSPomodoroButton()

        // Huge space between button and content
        Spacer(modifier = Modifier.height(128.dp))

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
                label = { Text("New Task",  color = Color.White) },
                singleLine = true,
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = { addTask() },
                modifier = Modifier.size(width = 100.dp, height = 40.dp),
                colors = ButtonDefaults.run { buttonColors(Color.Blue) }
            ) {
                Text(
                    text = "Add Task",
                    color = Color.White,
                    style = MaterialTheme.typography.bodyMedium
                )



                // Footer menu
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(139.dp)
                        .clip(
                            RoundedCornerShape(
                                topStart = 54.22222137451172.dp,
                                topEnd = 54.22222137451172.dp,
                                bottomStart = 54.22222137451172.dp,
                                bottomEnd = 54.22222137451172.dp
                            )
                        )
                        .background(
                            Color(
                                red = 0.6431372761726379f,
                                green = 0.5803921818733215f,
                                blue = 0.3803921639919281f,
                                alpha = 1f
                            )
                        )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        FooterIcon(imageVector = Icons.Default.Home)
                        FooterIcon(imageVector = Icons.Default.DateRange)
                        FooterIcon(imageVector = Icons.Default.Check)
                        FooterIcon(imageVector = Icons.Default.Favorite)
                    }
                }
            }
        }
    }
}
            @OptIn(ExperimentalMaterial3Api::class)
            @Composable
            fun FooterIcon(imageVector: ImageVector) {
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
                        contentDescription = null, // You can provide a content description if needed
                        tint = Color.White,
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
                .padding(top = 18.dp) // Adjust the top padding to move the text down
                .alpha(1f),
            color = Color.White, // Text color
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
            ToDoList()
        }
    }
}
