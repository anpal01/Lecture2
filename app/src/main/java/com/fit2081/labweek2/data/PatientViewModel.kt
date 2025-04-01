package com.fit2081.labweek2.data

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class PatientViewModel(context: Context) : ViewModel() {

    //create repo instance to talk to db
    private val patientRepo = PatientsRepository(context)

    //provides all patients that are currently stored
    val allPatients: Flow<List<Patient>> = patientRepo.getAllPatients()

    //viewmodel version of the db methods, gives it to viewmodel
    fun insert(patient: Patient) = viewModelScope.launch {
        patientRepo.insert(patient)
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

    //sets context for view models
    class PatientViewModelFactory(context: Context) : ViewModelProvider.Factory {
        private val context = context.applicationContext

        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            PatientViewModel(context) as T

    }


}
