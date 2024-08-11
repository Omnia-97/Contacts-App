package com.example.contactmanagerkotlin

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactmanagerkotlin.databinding.ActivityMainBinding
import com.example.contactmanagerkotlin.repo.ContactRepository
import com.example.contactmanagerkotlin.room.ContactDataBase
import com.example.contactmanagerkotlin.room.Contacts
import com.example.contactmanagerkotlin.view.MyRecyclerViewAdapter
import com.example.contactmanagerkotlin.viewmodel.ContactViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var contactViewModel: ContactViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val doa = ContactDataBase.getInstance(applicationContext).contactDAO
        val repository = ContactRepository(doa)
        contactViewModel = ViewModelProvider(
            this,
        ).get(ContactViewModel::class.java)
        binding.contactViewModel = contactViewModel
        binding.lifecycleOwner = this
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        displayUsersList()
    }

    private fun displayUsersList() {
        contactViewModel.users.observe(this, Observer {
            binding.recyclerView.adapter = MyRecyclerViewAdapter(
                it,
                { selectedItem: Contacts -> listItemClicked(selectedItem) }
            )

        })
    }

    private fun listItemClicked(selectedItem : Contacts) {
        Toast.makeText(this , "selected name is ${selectedItem.contact_name}" ,
            Toast.LENGTH_LONG).show()
        contactViewModel.initUpdateOrDelete(selectedItem)

    }
}