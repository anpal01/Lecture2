package com.fit2081.labweek2.data

import android.content.Context
import kotlinx.coroutines.flow.Flow

class PatientsRepository{ //abstracts how data is retreived from ui
    //if need to change data sources, edit it here
    //can define rules for which data source to use (e.g. if smth is n/a, check where)
    //handles data operations by utilising the DAO

    //holds patientdao instance
    var patientDao: PatientDao

    //constructor for initialising patientdao
    constructor(context: Context) {
        patientDao = HospitalDatabase.getDatabase(context).patientDao()
    }

    //populating the function declarations from patientdao
    suspend fun insert(patient: Patient){
        patientDao.insert(patient)
    }

    suspend fun delete(patient: Patient) {
        patientDao.delete(patient)
    }

    suspend fun update(patient: Patient){
        patientDao.update(patient)
    }

    suspend fun deleteAllPatients() {
        patientDao.deleteAllPatients()
    }

    //retrieves all patient data from db
    fun getAllPatients(): Flow<List<Patient>> = patientDao.getAllPatients()

}