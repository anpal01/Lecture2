package com.fit2081.labweek2

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.provider.CalendarContract.Calendars
import android.widget.DatePicker
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
            val mCheckBoxState = remember { mutableStateOf(false) }

            //variables that retrieve timepicker dialogs
            var mTimePickerDialog = TimePickerFun(mTime)
            var mDatePickerDialog = DatePickerFun(mDate)

            var sliderValue by remember { mutableStateOf(10f) }


            LabWeek2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column (
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        //button to open date picker
                        Button(onClick = {mDatePickerDialog.show()}) {
                            Text(text = "Select the Date")
                        }
                        Text(text = "Selected Date: $(mDate.value)")

                        Spacer(Modifier.height(40.dp))


                        //button for time picker

                        Button(onClick = {mTimePickerDialog.show()}) {
                            Text(text = "Select the Time")
                        }
                        Text(text = "Selected Time: ${mTime.value}")

                        //textfield
                        TextField(
                            value = mTextFieldValue.value,
                            onValueChange = {mTextFieldValue.value = it},
                            label = { Text("Enter Text")
                            }
                        )
                        Spacer(Modifier.height(40.dp))

                        //checklbox
                        Checkbox(
                            checked = mCheckBoxState.value, //retrieves the value of the variable
                            onCheckedChange = {mCheckBoxState.value = it}
                            )
                        
                        Spacer(Modifier.height(20.dp))

                        //progress bar

                        Text(text = "Slider Value: ${sliderValue.toInt()}")

                        Slider(
                            value = sliderValue,
                            onValueChange = {sliderValue = it},
                            valueRange = 0f..100f
                        )

                        Spacer(Modifier.height(40.dp))

                        Text("Progress Bar")

                        Spacer(Modifier.height(20.dp))
                        
                        LinearProgressIndicator(
                            progress = {sliderValue / 100f},
                            modifier = Modifier.padding(10.dp)
                        )
                    }
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

@Composable
fun DatePickerFun(mDate: MutableState<String>): DatePickerDialog { //the parameters and the return value after colon

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
fun TimePickerFun(mTime: MutableState<String>): TimePickerDialog { //the parameters and the return value after colon

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