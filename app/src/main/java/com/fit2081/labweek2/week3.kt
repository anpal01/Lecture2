package com.fit2081.labweek2

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.provider.CalendarContract.Calendars
import android.widget.DatePicker
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
            var DatePickerDialog = DatePickerFun(mDate)

            var sliderValue by remember { mutableStateOf(10f) }


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