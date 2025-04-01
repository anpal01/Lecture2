package com.fit2081.labweek2

import android.icu.util.Calendar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GreetingViewModel : ViewModel() {

    var userName by mutableStateOf("")
        private set


    var greetingMessage by mutableStateOf("")
        private set

    var isLoading by mutableStateOf(false)
        private set


    fun updateUsername(name: String) {
        userName = name

    }

    fun generateGreeting() {

        viewModelScope.launch {
            isLoading = true
            delay(1000)

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