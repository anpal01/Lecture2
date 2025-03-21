package com.fit2081.labweek2

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fit2081.labweek2.ui.theme.LabWeek2Theme


// login credentials, put these BEFORE THE CLASS DECLARATION
private const val usernameStatic: String = "testUser"
private const val passwordStatic: String = "testPassword"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LabWeek2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    //LoginScreen(modifier = Modifier.padding(innerPadding))

                    Column(modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                        verticalArrangement = Arrangement.Center
                    ) {
                        LoginScreen2()
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    //shows toast message
    val context = LocalContext.current

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            //add column details here, like alignment, fill size, font sizes, etc
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top

        ) {
//            We will insert an Image here
           androidx.compose.foundation.Image(
               painter = painterResource(id = R.drawable.fit2081_logo_yellow),
               contentDescription = "FIT logo",
               modifier = Modifier.height(24.dp) //adjust size of image as needed
           )

            //adds space between logo and username
            Spacer(modifier = Modifier.height(24.dp))

//            userName

            OutlinedTextField( //gives text field an outline
                value = username, //what variable is being changed?/input?
                onValueChange = { username = it }, //what happens when a user changes the input?
                label = { Text(text = "Username") }, //the placeholder text
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

//            Password

            OutlinedTextField( //gives text field an outline
                value = password, //what variable is being changed?/input?
                onValueChange = { password = it }, //what happens when a user changes the input?
                label = { Text(text = "Password") }, //the placeholder text
                visualTransformation = PasswordVisualTransformation(), //makes password the dot thing??
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

//            Button

            Button(

                onClick = {
                    //check credentials
                    if (username == usernameStatic && password == passwordStatic) {

                        //correct, give message
                        Toast.makeText(context, "Login Successful", Toast.LENGTH_LONG).show()

                        //starts the dashboard activity
                        context.startActivity(Intent(context, Dashboard::class.java))
                    } else {
                        Toast.makeText(context, "Incorrect Credentials", Toast.LENGTH_LONG).show()
                    }

                }

            ) {
                Text("Login")
            }

        }
    }
}


fun isValidEmail(email: String):Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

@Composable
fun LoginScreen2() {

    val context = LocalContext.current

    // Email and password state
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

// Error flags for validation, false no error, true there IS error
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }

//email field
    OutlinedTextField(
        value = email,
        onValueChange = {
            email = it

            //checks email is valid, ! bc if email is valid, function returns true but since trying to sense error, there is NO ERROR = false
            emailError = !isValidEmail(it)
        },
        label = { Text("Email") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),

        //iserror takes on emailerror
        //if true (error present), what should the error message/visual look like?
        isError = emailError,
        modifier = Modifier.fillMaxWidth(),
        singleLine = true
    )

    //if there IS an error, turn the textbox red + give it more padding so it stands out
    if (emailError) {
        Text(
            text = "Invalid Email",
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
        )
    }

    //password field
    OutlinedTextField(
        value = password,
        onValueChange = {
            password = it
            //check length of pw, if less theres error, if not, nothing
            passwordError = it.length < 6
        },
        label = { Text("Password") },
        visualTransformation = PasswordVisualTransformation(), //password is not visible as you type
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),

        //same deal, iserror takes on pwerror value and if there IS an error, the message should look like this
        isError = passwordError,
        modifier = Modifier.fillMaxWidth(),
        singleLine = true
    )

    if (passwordError) {
        Text(
            text = "Password must be at least 6 characters",
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
        )
    }


    //button to move to dash if both credentials are correct
    Button(
        onClick = {
            emailError = !isValidEmail(email)
            passwordError = password.length < 6

            if (!emailError && !passwordError) {
                context.startActivity(Intent(context, Dashboard::class.java))
            }
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Login")
    }

}