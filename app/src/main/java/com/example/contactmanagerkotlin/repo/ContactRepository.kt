package com.example.contactmanagerkotlin.repo

import com.example.contactmanagerkotlin.room.ContactDAO
import com.example.contactmanagerkotlin.room.Contacts

class ContactRepository(private val contactDAO: ContactDAO) {
    val contact = contactDAO.getAllContactsInDB()
    suspend fun insert(contact: Contacts): Long {
        return contactDAO.insertContact(contact)
    }
    suspend fun update(contact: Contacts)  {
        return contactDAO.updateContact(contact)
    }
    suspend fun delete(contact: Contacts) {
        return contactDAO.deleteContact(contact)
    }
    suspend fun deleteAll() {
        return contactDAO.deleteAllContacts()
    }
}