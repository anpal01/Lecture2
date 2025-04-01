package com.fit2081.labweek2.data

import android.content.Context
import kotlinx.coroutines.flow.Flow

class PatientsRepository{

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

    fun getAllPatients(): Flow<List<Patient>> = patientDao.getAllPatients()

}