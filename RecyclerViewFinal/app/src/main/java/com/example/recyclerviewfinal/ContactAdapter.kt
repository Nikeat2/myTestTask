package com.example.recyclerviewfinal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

private lateinit var mListener: ContactAdapter.OnItemClickListener

class ContactAdapter(private var contactList: MutableList<Contact>):
    RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

        private val contactListFull: List<Contact> = contactList.toList()






    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val contactSurnameTv: TextView = view.findViewById(R.id.contactSurname)
        val contactNameTv: TextView = view.findViewById(R.id.contactName)
        val contactPhotoIv: ImageView = view.findViewById(R.id.contactPhoto)
        val contactNumberTv: TextView = view.findViewById(R.id.contactNumber)

        init {
            itemView.setOnClickListener {
                mListener.onClick(adapterPosition)
            }
        }

        init {
            itemView.setOnLongClickListener {
                mListener.onLongClick(adapterPosition)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = contactList[position]
        holder.contactNameTv.text = contact.contactName
        holder.contactSurnameTv.text = contact.contactSurname
        holder.contactNumberTv.text = contact.contactPhone
        Glide.with(holder.itemView.context).load(contact.contactPhoto).into(holder.contactPhotoIv)
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    fun updateContactName (position: Int, newName: String) {
        contactList[position].contactName = newName
        notifyItemChanged(position)
    }

    fun updateContactSurname(position: Int, newSurname: String){
        contactList[position].contactSurname = newSurname
        notifyItemChanged(position)
    }

    fun updateContactPhone(position: Int, newPhone: String) {
        contactList[position].contactPhone = newPhone
        notifyItemChanged(position)
    }

    fun filter(text: String) {
        contactList.clear()
        if (text.isEmpty()) {
            contactList.addAll(contactListFull)
        } else {
            val filterPattern = text.lowercase().trim()
            for (contact in contactListFull) {
                if (contact.contactName.lowercase().contains(filterPattern) ||
                    contact.contactSurname.lowercase().contains(filterPattern)){
                    contactList.add(contact)
                }
            }
        }
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onClick(position: Int)
        fun onLongClick(position: Int)
    }

}