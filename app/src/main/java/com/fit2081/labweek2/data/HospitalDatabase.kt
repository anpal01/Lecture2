package com.fit2081.labweek2.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Patient::class], version = 1, exportSchema = false)
//let it know its a room db
abstract class HospitalDatabase: RoomDatabase() {

    //returns dao, room does this

    abstract fun patientDao(): PatientDao

    companion object {

        //holds db instance, and volatile, visible to all threads?
        @Volatile
        private var Instance: HospitalDatabase? = null

        //returns db instance and if null, creates one

        fun getDatabase(context: Context): HospitalDatabase {

            //synchronised = one thread can access at a time
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, HospitalDatabase::class.java, "hospital_database")
                    .build().also { Instance = it }
            }
        }

    }

}
