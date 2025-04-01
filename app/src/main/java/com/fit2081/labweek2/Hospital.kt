package com.fit2081.labweek2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.fit2081.labweek2.data.Patient
import com.fit2081.labweek2.data.PatientViewModel
import com.fit2081.labweek2.ui.theme.LabWeek2Theme

class Hospital : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val viewModel: PatientViewModel = ViewModelProvider(
            this, PatientViewModel.PatientViewModelFactory(this@Hospital)
        )[PatientViewModel::class.java]
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LabWeek2Theme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
                var patientName by  remember { mutableStateOf("") }
                var patientAge by  remember { mutableStateOf("") }
                var patientAddress by  remember { mutableStateOf("") }


                TextField(value = patientName,
                    onValueChange = {patientName = it},
                    label = { Text("Enter your name") },
                    modifier = Modifier.fillMaxWidth()
                )

                TextField(value = patientAge,
                    onValueChange = {patientAge = it},
                    label = { Text("Enter your age") },
                    modifier = Modifier.fillMaxWidth()
                )

                TextField(value = patientAddress,
                    onValueChange = {patientAddress = it},
                    label = { Text("Enter your address") },
                    modifier = Modifier.fillMaxWidth()
                )

                val numberOfPatients by viewModel.allPatients.collectAsState(initial = emptyList())

                Button(onClick = { viewModel.insert(
                    Patient(
                        name = patientName,
                        age = patientAge.toInt(),
                        address = patientAddress
                    )
                )}

                ) {
                    Text("Add Patient")
                }

                Button(onClick = { viewModel.deleteAllPatients()}
                ) {
                    Text("Delete All Patients")
                }

            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LabWeek2Theme {
        Greeting("Android")
    }
}