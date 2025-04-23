package com.fit2081.labweek2.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Patient::class], version = 1, exportSchema = false)
//let it know its a room db
abstract class HospitalDatabase: RoomDatabase() { //makes sure that only one instance exists at a time

    //returns instance of patient dao for patient entities, provide depending on entity number
    //pretty much provides access to do db operations on the relevant entity

    abstract fun patientDao(): PatientDao

    companion object {

        //holds db instance, and volatile, visible to all threads?
        //used to access the db within the class
        @Volatile
        private var Instance: HospitalDatabase? = null

        //returns db instance and if null, creates one
        //used to access the db outside of the class and create one if empty
        fun getDatabase(context: Context): HospitalDatabase {

            //synchronised = one thread can access at a time
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, HospitalDatabase::class.java, "hospital_database")
                    .build().also { Instance = it }
            }
        }

    }

}
