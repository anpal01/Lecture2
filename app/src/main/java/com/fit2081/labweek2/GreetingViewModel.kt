package com.fit2081.labweek2

import android.icu.util.Calendar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


//viewmodels hold data that the ui needs to display, any changes the ui goes through wont affect the data as the processing for it is independant
class GreetingViewModel : ViewModel() {

    //details what variables will be displayed in the ui as well as variable processing

    var userName by mutableStateOf("")
        private set //no external mods, security effort, only editable via viewmodel


    var greetingMessage by mutableStateOf("")
        private set

    var isLoading by mutableStateOf(false)
        private set


    fun updateUsername(name: String) {
        userName = name

    }

    fun generateGreeting() {

        viewModelScope.launch { //viewmodelscope = coroutine (asynch process), lasts for as long as viewmodel is there
            //used to run the delay without blocking ui view
            isLoading = true
            delay(1000) //simulates delay

            if (userName.isNotBlank()) {
                val currentTime = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)

                greetingMessage = when (currentTime) {
                    in 0..11 -> "Good Morning, $userName"
                    in 12..17 -> "Good Afternoon, $userName"
                    else -> "Good Evening, $userName"
                }
            } else {
                greetingMessage = "Please enter your name"
            }
            isLoading = false
        }

    }

}