package com.example.contactmanagerkotlin.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "contacts_table")
data class Contacts(
    @PrimaryKey(autoGenerate = true)
    val contact_id : Int,
    var contact_name : String,
    var contact_email : String
)
