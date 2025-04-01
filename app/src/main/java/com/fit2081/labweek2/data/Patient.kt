package com.fit2081.labweek2.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "patients") //this entire thing details the table and all the columns for each entry
data class Patient(

    //id for the object, auto made

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val name: String,
    val age: Int,
    val address: String

)
