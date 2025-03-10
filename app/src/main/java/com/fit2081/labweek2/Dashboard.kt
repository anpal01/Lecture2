package com.fit2081.labweek2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fit2081.labweek2.ui.theme.LabWeek2Theme

class Dashboard : ComponentActivity() {

    //this gets called when this activity starts

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            //gives all composables this theme
            LabWeek2Theme {

                //gives basic layout structure for apps
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column (
                        modifier = Modifier.fillMaxSize().padding(innerPadding),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {
                        Text(
                            text =  "Dashboard",
                            style = TextStyle(fontSize = 40.sp)

                        )

                    }
                    MyBottomBar()
                }
            }
        }
    }
}

@Composable
fun MyBottomBar() {
    Scaffold(
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.height(60.dp),
                content = {
                    IconButton(onClick = { /* TODO: Add action */ }) {
                        Icon(Icons.Filled.Check, contentDescription = "Check icon")
                    }
                    IconButton(onClick = { /* TODO: Navigate to another screen */ }) {
                        Icon(Icons.Filled.Home, contentDescription = "Go Home")
                    }
                    Button(onClick = { /* TODO: Add button action */ }) {
                        Text("Click Me")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /* TODO: Add FAB action */ }) {
                Icon(Icons.Filled.Add, contentDescription = "Add something")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Hello, Bottom Bar!", fontSize = 24.sp)
        }
    }
}

