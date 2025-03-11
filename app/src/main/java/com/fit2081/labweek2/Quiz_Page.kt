package com.fit2081.labweek2

import android.content.Intent
import android.os.Bundle
import android.speech.ModelDownloadListener
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fit2081.labweek2.ui.theme.LabWeek2Theme

class Quiz_Page : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
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
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.fit2081_logo_yellow),
                            contentDescription = "Quiz Image",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .padding(16.dp)

                        )

                        //since values can be changed continuously throughout process, theyre mutable
                        var checked1 by remember { mutableStateOf(false) }
                        var checked2 by remember { mutableStateOf(false) }
                        var checked3 by remember { mutableStateOf(false) }

                        //all questions are laid out in a column format
                        Column (modifier = Modifier.fillMaxWidth().padding(16.dp), horizontalAlignment = Alignment.Start) {


                            //individual questions and their checkboxes are all rows
                            Row (verticalAlignment = Alignment.CenterVertically) {
                                Checkbox(checked = checked1, onCheckedChange = {checked1 = it})
                                Text("Q1: The Earth's flat, Peetah.")
                            }

                            Row (verticalAlignment = Alignment.CenterVertically) {
                                Checkbox(checked = checked2, onCheckedChange = {checked2 = it})
                                Text("Q2: Canberra's the capital of Australia, Peetah.")
                            }

                            Row (verticalAlignment = Alignment.CenterVertically) {
                                Checkbox(checked = checked3, onCheckedChange = {checked3 = it})
                                Text("Q3: The speed of sound is 1,236 km/h, Peetah.")
                            }
                        }

                        //dictate the action taken when user is done and they click a confirm button
                        Button(onClick = {

                            var result = !checked1 && checked2 && checked3; //this checks the quiz answers, result is true IF all of these conditions are met
                            val intent =
                                Intent(this@Quiz_Page, QuizResult::class.java).apply { //make intent from quizpage to quizresult
                                    putExtra("isPassed", result)//pass on the extra data e.g. result
                                }
                            startActivity(intent) //start the new activity via the intent
                        }) {
                            Text("I'm Done!")
                        }
                    }
                }
            }
        }
    }
}

