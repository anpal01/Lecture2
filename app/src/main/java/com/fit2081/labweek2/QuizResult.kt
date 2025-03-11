package com.fit2081.labweek2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fit2081.labweek2.ui.theme.LabWeek2Theme

class QuizResult : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            //retrieves the passed on value from quiz_page
            val isPassed = intent.getBooleanExtra("isPassed", false)

            LabWeek2Theme {
                Scaffold(
                    topBar = { TopBar() }, // Include Top Bar
                    bottomBar = { MyBottomBar() }, // Include Bottom Bar
                    floatingActionButton = {
                        FloatingActionButton(onClick = { /* TODO: Add FAB action */ }) {
                            Icon(Icons.Filled.Add, contentDescription = "Add something")
                        }
                    }
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        if (isPassed) {
                            Image(
                                painter = painterResource(R.drawable.green_tick),
                                contentDescription = "Passed",
                                modifier = Modifier.size(100.dp)
                            )
                        } else {
                            Image(
                                painter = painterResource(R.drawable.red_corss),
                                contentDescription = "Failed",
                                modifier = Modifier.size(100.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

