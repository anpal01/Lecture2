package com.fit2081.labweek2.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PatientDao {

    //all of the retrieval functions/functions that deal with taking, deleting and updating data
    //HOW DOES IT INTERACT, not actually doing it, repo does that
    //all declared here, no functionality coded

    //suspend is a coroutine function, using suspend at start of methods shows that the functions called from a coroutine/can be done in parallel with functions

    @Insert
    suspend fun insert(patient: Patient)

    @Update
    suspend fun update(patient: Patient)

    @Delete //annotations ensure that right sql query is used, maps
    suspend fun delete(patient: Patient)

    @Query("DELETE FROM patients")//means that the function does the same as this sql query
    suspend fun deleteAllPatients()

    @Query("SELECT * FROM patients ORDER BY id ASC")
    fun getAllPatients(): Flow<List<Patient>> //flow automatically creates a new list when records are changed

}