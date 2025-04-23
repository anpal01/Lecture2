package com.fit2081.labweek2.data

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class PatientViewModel(context: Context) : ViewModel() { //handles data retrieval and prep, while ui displays it
    //prevents data loss/unnecessary queries

    //create repo instance to talk to db
    private val patientRepo = PatientsRepository(context)

    //provides all patients that are currently stored
    val allPatients: Flow<List<Patient>> = patientRepo.getAllPatients()

    //viewmodel version of the db methods, gives it to viewmodel
    fun insert(patient: Patient) = viewModelScope.launch { //updates the viewmodel afterwards
        patientRepo.insert(patient) //inserts the new patient
    }

    fun delete(patient: Patient) = viewModelScope.launch {
        patientRepo.delete(patient)
    }
    fun update(patient: Patient) = viewModelScope.launch {
        patientRepo.update(patient)
    }
    fun deleteAllPatients() = viewModelScope.launch {
        patientRepo.deleteAllPatients()
    }

    //how to create a new patient view model (like a factory)
    //factories = pattern to create objects without exposing creation logic
    //instead of PatientViewModel(_all of the parameters_), can just invoke factory
    class PatientViewModelFactory(context: Context) : ViewModelProvider.Factory {
        private val context = context.applicationContext //uses app context instead of activity context. why? it lives longer/prevents memory leaks

        override fun <T : ViewModel> //T = generic type that extends viewmodel
                create(modelClass: Class<T>): T = //cakker passes class obj for type of vm wanted
            PatientViewModel(context) as T //creating the vm and casting it to t type, we already know t is a vm so its ok

    }


}
