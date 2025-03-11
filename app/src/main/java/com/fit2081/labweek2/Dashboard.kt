package com.fit2081.labweek2

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class Dashboard : ComponentActivity() {

    //this gets called when this activity starts

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyBottomBar()

        }
    }
}

@Composable
fun MyBottomBar() {
    val context = LocalContext.current //if looking to make links, make sure to involve the local context
    Scaffold(
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.height(60.dp),
                content = {
                    IconButton(onClick = { /* TODO: Add action */ }) {
                        Icon(Icons.Filled.Check, contentDescription = "Check icon") //defines the icon and the text
                    }
                    IconButton(onClick = { /* TODO: Navigate to another screen */ }) {
                        Icon(Icons.Filled.Home, contentDescription = "Go Home")
                    }
                    Button(onClick = { context.startActivity(Intent(context, Quiz_Page::class.java)) }) {
                        Text("Quiz Time!")
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

@Composable
fun DropdownMenu() {

    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.padding(16.dp)) {
        IconButton(onClick = {expanded = true}) {
            Icon(Icons.Default.MoreVert, contentDescription = "More options")
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {expanded = false}
        ) {

            DropdownMenuItem(
                text = { Text("Edit") },
                onClick = {/* handle edit*/},
                leadingIcon = { Icon(Icons.Outlined.Edit, contentDescription = null) }
            )
            DropdownMenuItem(
                text = { Text("Settings") },
                onClick = {/* handle settings*/},
                leadingIcon = { Icon(Icons.Outlined.Settings, contentDescription = null) }
            )
            HorizontalDivider()
            DropdownMenuItem(
                text = { Text("Send Feedback") },
                onClick = {/* handle feedback*/},
                leadingIcon = { Icon(Icons.Outlined.Email, contentDescription = null) },
                trailingIcon =  { Text("F11", textAlign = TextAlign.Center) }
            )
        }
    }
}



