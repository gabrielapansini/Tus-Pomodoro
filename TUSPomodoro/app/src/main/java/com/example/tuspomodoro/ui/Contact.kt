import android.content.Intent
import android.net.Uri
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
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
import com.example.tuspomodoro.ui.Pomodoro
import com.example.tuspomodoro.ui.Screen
import com.example.tuspomodoro.ui.theme.CustomColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactUs(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
            .padding(16.dp)
            .wrapContentHeight(Alignment.CenterVertically) // align  vertically to the center
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally, // Align horizontally to the center
    ) {
        Spacer(modifier = Modifier.height(64.dp))

        // TUS Button
        Button(
            onClick = {
                navController.navigate(Screen.PomodoroScreen.route)
            },
            modifier = Modifier
                .width(294.dp)
                .height(78.dp)
                .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp, bottomStart = 40.dp, bottomEnd = 40.dp))
                .align(Alignment.CenterHorizontally), // Align the button to the center horizontally
            colors = ButtonDefaults.buttonColors(containerColor = CustomColor, contentColor = Color.White),
        ) {
            Text(
                text = "TUS POMODORO",
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
                textDecoration = TextDecoration.None,
                letterSpacing = 0.sp,
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
        Spacer(modifier = Modifier.height(26.dp))

        ContactInfo()

        Spacer(modifier = Modifier.height(60.dp))

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
fun FooterIcon(imageVector: ImageVector, color: Color) {
    Surface(
        color = Color.Transparent,
        onClick = {
            // TO DO
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

@Composable
fun ContactInfo() {
    // Display your contact information etc.
    Text(
        text = "Contact Us",
        fontSize = 30.sp,
        color = Color.White,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(bottom = 16.dp)
    )
    Text(
        text = "Email: Reception.Midwest@tus.ie",
        fontSize = 18.sp,
        color = Color.White,
        modifier = Modifier.padding(bottom = 8.dp)
    )
    Text(
        text = "Phone:  +353 61 293000",
        fontSize = 18.sp,
        color = Color.White,
        modifier = Modifier.padding(bottom = 16.dp)
    )

    // Button to trigger dialing the phone number
    val context = LocalContext.current
    Button(
        onClick = {
            dialPhoneNumber(context, "+353 61 293000")
        },
        modifier = Modifier.size(width = 200.dp, height = 40.dp),
        colors = ButtonDefaults.buttonColors(CustomColor)
    ) {
        Text(
            text = "Call Us",
            color = Color.White,
            fontSize = 16.sp
        )
    }
}


//References: https://stackoverflow.com/questions/44169564/how-to-call-phone-number-in-kotlin-android


// Function to dial a phone number
private fun dialPhoneNumber(context: android.content.Context, phoneNumber: String) {
    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse("tel:$phoneNumber")
    }
    context.startActivity(intent)
}
@Preview
@Composable
fun PreviewContactUs() {
    MaterialTheme {
        Surface {
            val navController = rememberNavController()
            ContactUs(navController)
        }
    }
}