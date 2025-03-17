package com.fit2081.labweek2

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.DatePicker
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
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.fit2081.labweek2.ui.theme.LabWeek2Theme
import java.io.BufferedReader
import java.io.InputStreamReader

class Week3 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val mContext = LocalContext.current

            val mDate = remember { mutableStateOf("") }
            val mTime = remember { mutableStateOf("") }

            val mTextFieldValue = remember { mutableStateOf("") }
            val mCheckBoxState = remember { mutableStateOf(false) }

            //variables that retrieve timepicker dialogs
            var mTimePickerDialog = TimePickerFunction(mTime)
            var mDatePickerDialog = DatePickerFunction(mDate)

            var sliderValue by remember { mutableStateOf(10f) }


            LabWeek2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        //button to open date picker
                        Button(onClick = { mDatePickerDialog.show() }) {
                            Text(text = "Select the Date")
                        }
                        Text(text = "Selected Date: $(mDate.value)")

                        Spacer(Modifier.height(40.dp))


                        //button for time picker

                        Button(onClick = { mTimePickerDialog.show() }) {
                            Text(text = "Select the Time")
                        }
                        Text(text = "Selected Time: ${mTime.value}")

                        //textfield
                        TextField(
                            value = mTextFieldValue.value,
                            onValueChange = { mTextFieldValue.value = it },
                            label = {
                                Text("Enter Text")
                            }
                        )
                        Spacer(Modifier.height(40.dp))

                        //checklbox
                        Checkbox(
                            checked = mCheckBoxState.value, //retrieves the value of the variable
                            onCheckedChange = { mCheckBoxState.value = it }
                        )

                        Spacer(Modifier.height(20.dp))

                        //progress bar

                        Text(text = "Slider Value: ${sliderValue.toInt()}")

                        Slider(
                            value = sliderValue,
                            onValueChange = { sliderValue = it },
                            valueRange = 0f..100f
                        )

                        Spacer(Modifier.height(40.dp))

                        Text("Progress Bar")

                        Spacer(Modifier.height(20.dp))

                        LinearProgressIndicator(
                            progress = { sliderValue / 100f },
                            modifier = Modifier.padding(10.dp)
                        )


                        //retrieve instance of shared prefs to save/restore data
                        //val sharedPref = mContext.getSharedPreferences("doc", Context.MODE_PRIVATE).edit() //file name, only calling app can access file, opens for updates


                        //saves all of the info input
                        Button(onClick = {
                            val sharedPref =
                                mContext.getSharedPreferences("doc", Context.MODE_PRIVATE).edit()

                            sharedPref.putString("date", mDate.value)
                            sharedPref.putString("time", mTime.value)
                            sharedPref.putString("text", mTextFieldValue.value)
                            sharedPref.putBoolean("checkbox", mCheckBoxState.value)
                            sharedPref.putInt("slider", sliderValue.toInt())
                            sharedPref.apply()
                        }) {
                            Text(text = "Save Data")
                        }

                        //loads saved data, provides default values if none
                        Button(onClick = {

                            val sharedPref =
                                mContext.getSharedPreferences("doc", Context.MODE_PRIVATE)

                            val loadDate = sharedPref.getString("date", "17/03/2025")
                            val loadTime = sharedPref.getString("time", "9:00")
                            val loadText = sharedPref.getString("text", "")
                            val loadCheckBox = sharedPref.getBoolean("checkbox", false)
                            val loadSlider = sharedPref.getInt("slider", 0).toFloat()

                            //replaces the current values with the loaded ones
                            mDate.value = loadDate.toString()
                            mTime.value = loadTime.toString()
                            mTextFieldValue.value = loadText.toString()
                            mCheckBoxState.value = loadCheckBox
                            sliderValue = loadSlider
                        }) {
                            Text(text = "Load Saved Values")
                        }

                       // CSVProcesorScreen(this@Week3, modifier = Modifier.padding(innerPadding))
                    }
                }
            }
        }
    }
}


@Composable
fun DatePickerFunction(mDate: MutableState<String>): DatePickerDialog { //the parameters and the return value after colon

    //get current context
    val mContext = LocalContext.current

    //variables to hold date info
    val mYear: Int
    val mMonth: Int
    val mDay: Int //why the colon? rather than =?

    //get calendar instance
    val mCalendar = Calendar.getInstance()

    //take date from this calendar
    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    //return and create datepickerdialog

    return DatePickerDialog(
        //we're returning a context for todays date
        //this is called whenever the dates being set
        //provides the current date

        mContext,
        {
            _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            mDate.value = "$mDayOfMonth/${mMonth + 1}/$mYear"
        }, mYear, mMonth, mDay
    )
}


@Composable
fun TimePickerFunction(mTime: MutableState<String>): TimePickerDialog { //the parameters and the return value after colon

    //get current context
    val mContext = LocalContext.current

    //get calendar instance
    val mCalendar = Calendar.getInstance()

    //get current hour and minute
    val mHour = mCalendar.get(Calendar.HOUR_OF_DAY)
    val mMinute = mCalendar.get(Calendar.MINUTE)
    //val mSecond = mCalendar.get(Calendar.SECOND), can add this if want to include minutes

    //set to current time for calendar
    mCalendar.time = Calendar.getInstance().time

    //return and create timepickerdialog

    return TimePickerDialog(
        //we're returning a context for todays time
        //this is called whenever the dates being set
        //provides the current date

        mContext,
        {
                _, mHour: Int, mMinute: Int -> //this is where the code listens for changes
            mTime.value = "$mHour:$mMinute" //listened values are than put into the initial hour and minute
        }, mHour, mMinute, false //use 24hr time or not
    )
}


////counts row number
//fun countRowsByLocation(context: Context, fileName: String, location: String): Int {
//
//    var count = 0
//    var assets = context.assets //get asset manager
//
//    //try to open csv and read each line
//    try {
//        val inputStream = assets.open(fileName) //open file
//
//        //create buffer for reading
//        val reader = BufferedReader(InputStreamReader(inputStream))
//        reader.useLines { lines ->
//            lines.drop(1).forEach { line -> //skip header
//                val values = line.split(",") //split each line into values
//
//                //check if row has enough column
//                //also if 7th column matches the location
//                if(values.size > 6 && values[7].trim() == location.trim()) {
//                    count++ //increment
//                }
//            }
//        }
//    } catch (e: Exception) {
//        //error message or handling of errors in reading files
//    }
//    //return count
//    return count
//}


//@Composable
//fun CSVProcesorScreen(context: Context, modifier: Modifier){
//
//    //var to hold location input
//    var location by remember { mutableStateOf("") }
//
//    //holds count of rows with matching locations
//    var count by remember { mutableStateOf(0) }
//
//    //hold text to display result
//    var resultText by remember { mutableStateOf("Result: 0") }
//
//    val context = LocalContext.current
//
//    //text field for entering the location
//    TextField(
//        value = location,
//        onValueChange = {location = it},
//        label = { Text("Enter Location") },
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(bottom = 16.dp)
//    )
//
//    Spacer(modifier = Modifier.height(16.dp))
//
//    //button to kickstart csv processing
//    Button(onClick = {
//
//
//        count = countRowsByLocation(context, "data.csv", location)
//
//        //update text result
//        resultText = "Result: $count"
//    },
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(bottom = 16.dp)
//        ) {
//        Text("Process CSV")
//    }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//    Text(text = resultText)
//}
