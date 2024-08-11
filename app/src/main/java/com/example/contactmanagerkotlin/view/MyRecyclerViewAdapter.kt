package com.example.contactmanagerkotlin.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.contactmanagerkotlin.R
import com.example.contactmanagerkotlin.databinding.CardItemBinding
import com.example.contactmanagerkotlin.room.Contacts

class MyRecyclerViewAdapter(
    private val contactList: List<Contacts>,
    private val clickListener: (Contacts) -> Unit
) :
    RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>() {


    class MyViewHolder(var binding: CardItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(contact: Contacts, clickListener: (Contacts) -> Unit) {
            binding.nameTextView.text = contact.contact_name
            binding.emailTextView.text = contact.contact_email
            binding.listItemLayout.setOnClickListener {
                clickListener(contact)

            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: CardItemBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.card_item,
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return contactList.size

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(contactList[position], clickListener)

    }
}