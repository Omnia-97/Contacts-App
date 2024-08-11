package com.example.contactmanagerkotlin.room

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

interface ContactDAO {
    @Insert
    suspend fun insertContact(contact: Contacts) : Long

    @Update
    suspend fun updateContact(contact: Contacts)

    @Delete
    suspend fun deleteContact(contact: Contacts)

    @Query("DELETE FROM contacts_table")
    suspend fun deleteAllContacts()

    @Query("SELECT * FROM contacts_table")
    fun getAllContactsInDB() : LiveData<List<Contacts>>
}