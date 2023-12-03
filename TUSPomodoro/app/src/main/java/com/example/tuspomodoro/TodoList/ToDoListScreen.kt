// Import necessary dependencies
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalSoftwareKeyboardController.current
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// Main function to create the To-Do List screen
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun ToDoListScreen() {
    var tasks by remember { mutableStateOf(mutableListOf("Task 1", "Task 2", "Task 3")) }
    var newTask by remember { mutableStateOf("") }

    // Function to add a new task
    fun addTask() {
        if (newTask.isNotBlank()) {
            tasks.add(newTask)
            newTask = ""
        }
    }

    // Function to delete a task
    fun deleteTask(task: String) {
        tasks.remove(task)
    }

    // Composable for the To-Do List screen
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(16.dp)
    ) {
        // To-Do List Header
        Text(
            text = "To-Do List",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // To-Do List
        LazyColumn {
            items(tasks.size) { index ->
                ToDoListItem(
                    task = tasks[index],
                    onDelete = { deleteTask(tasks[index]) }
                )
            }
        }

        // Add Task Section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Input field for new task
            OutlinedTextField(
                value = newTask,
                onValueChange = { newTask = it },
                label = { Text("New Task") },
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        addTask()
                        hideKeyboard(current)
                    }
                ),
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            )

            // Add Task Button
            IconButton(
                onClick = {
                    addTask()
                    current?.hide()
                },
                modifier = Modifier
                    .size(48.dp)
                    .background(color = MaterialTheme.colorScheme.primary)
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add Task",
                    tint = Color.White
                )
            }
        }
    }
}

// Composable for each item in the To-Do List
@Composable
fun ToDoListItem(task: String, onDelete: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Check Icon
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .size(24.dp)
                .clickable { /* Handle task completion */ }
        )

        // Task Text
        Text(
            text = task,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
        )

        // Delete Icon
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .size(24.dp)
                .clickable(onClick = onDelete)
        )
    }
}


// Function to hide the keyboard
@OptIn(ExperimentalComposeUiApi::class)
fun hideKeyboard(keyboardController: SoftwareKeyboardController?) {
    keyboardController?.hide()
}
// Preview the To-Do List screen
@Preview
@Composable
fun PreviewToDoListScreen() {
    MaterialTheme {
        ToDoListScreen()
    }
}
