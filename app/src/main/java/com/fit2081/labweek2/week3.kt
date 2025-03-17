package com.fit2081.labweek2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.fit2081.labweek2.ui.theme.LabWeek2Theme

class week3 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val mContent = LocalContext.current

            val mDate = remember { mutableStateOf("") }
            val mTime = remember { mutableStateOf("") }

            val mTextFieldValue = remember { mutableStateOf("") }
            val mCheckBoxState = remember { mutableStateOf("") }

            //variables that retrieve timepicker dialogs
            //var mTimePickerDialog = TimePickerFun(mTime)
            //var DatePickerDialog = DatePickerFun(mDate)


            LabWeek2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
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