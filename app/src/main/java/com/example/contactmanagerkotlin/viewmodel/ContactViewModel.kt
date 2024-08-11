package com.example.contactmanagerkotlin.viewmodel

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactmanagerkotlin.repo.ContactRepository
import com.example.contactmanagerkotlin.room.Contacts
import kotlinx.coroutines.launch

class ContactViewModel(private val repository: ContactRepository) : ViewModel(), Observable {
    val users = repository.contact
    private var isUpdateOrDelete = false
    private lateinit var contactToDeleteOrUpdate: Contacts

    @Bindable
    val inputName = MutableLiveData<String?>()

    @Bindable
    val inputEmail = MutableLiveData<String?>()

    @Bindable
    val saveOrUpdateButtonText = MutableLiveData<String?>()

    @Bindable
    val clearOrDeleteButtonText = MutableLiveData<String>()

    init {
        saveOrUpdateButtonText.value = "Save"
        clearOrDeleteButtonText.value = "Clear All"
    }

    private fun insert(contact: Contacts) = viewModelScope.launch {
        repository.insert(contact)
    }

    private fun delete(contact: Contacts) = viewModelScope.launch {
        repository.delete(contact)
        inputName.value = null
        inputEmail.value = null
        isUpdateOrDelete = false
        saveOrUpdateButtonText.value = "Save"
        clearOrDeleteButtonText.value = "Clear All"
    }

    private fun update(contact: Contacts) = viewModelScope.launch {
        repository.update(contact)
        inputName.value = null
        inputEmail.value = null
        isUpdateOrDelete = false
        saveOrUpdateButtonText.value = "Save"
        clearOrDeleteButtonText.value = "Clear All"
    }

    private fun clearAll() = viewModelScope.launch {
        repository.deleteAll()
    }

    fun saveOrUpdate() {
        if (isUpdateOrDelete) {
            contactToDeleteOrUpdate.contact_name = inputName.value!!
            contactToDeleteOrUpdate.contact_email = inputName.value!!
            update(contactToDeleteOrUpdate)

        } else {
            val name = inputName.value!!
            val email = inputEmail.value!!
            insert(Contacts(0, name, email))
            inputEmail.value = null
            inputName.value = null
        }
    }

    fun clearAllOrDelete() {
        if (isUpdateOrDelete) {
            delete(contactToDeleteOrUpdate)

        } else {
            clearAll()
        }
    }

    fun initUpdateOrDelete(contact: Contacts) {
        inputName.value = contact.contact_name
        inputEmail.value = contact.contact_email
        isUpdateOrDelete = true
        contactToDeleteOrUpdate = contact
        saveOrUpdateButtonText.value = "Update"
        clearOrDeleteButtonText.value = "Delete"
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

}
