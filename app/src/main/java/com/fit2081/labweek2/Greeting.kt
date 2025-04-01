package com.fit2081.labweek2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fit2081.labweek2.ui.theme.LabWeek2Theme

class Greeting : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LabWeek2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        GreetingScreen(innerPadding)
                    }

                }
            }
        }
    }
}

//in order to use viewmodel data and methods, needs to have instance of it

@Composable
fun GreetingScreen(innerPaddingValues: PaddingValues, viewModel: GreetingViewModel = viewModel()){

    //these variables keep track of any changes via viewmodel and updates it
    val userName = viewModel.userName
    val greetingMessage = viewModel.greetingMessage
    val isLoading = viewModel.isLoading


    TextField(value = userName,
        onValueChange = {viewModel.updateUsername(it)},//custom method called via the viewmethod instance
        label = { Text("Enter your name") },
        modifier = Modifier.fillMaxWidth()
    )

    Button(onClick = {viewModel.generateGreeting()}//,
       // modifier = Modifier.align(Alignment.CenterHorizontally)

    ) {
        Text("Generate Greeting")
    }

    if (isLoading) {

        CircularProgressIndicator()
    } else {

        Text(
            text = greetingMessage,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
    }
}