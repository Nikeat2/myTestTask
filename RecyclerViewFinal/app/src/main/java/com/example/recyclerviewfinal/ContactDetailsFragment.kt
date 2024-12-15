package com.example.recyclerviewfinal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide





class ContactDetailsFragment : Fragment() {

    private lateinit var changefragment: ChangeContactFragment
    private lateinit var detailedContactBtn: Button
    private lateinit var detailedContactName: TextView
    private lateinit var detailedContactSurname: TextView
    private lateinit var detailedContactNumber: TextView
    var contactDetailed: Contact? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            contactDetailed = it.getParcelable(CONTACT_NAME_ARG)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_contact_details, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewForFragment(view)

        detailedContactBtn.setOnClickListener {
            btnClick(
                contactName = contactDetailed?.contactName.toString(),
                contactSurname = contactDetailed?.contactSurname.toString(),
                contactPhone = contactDetailed?.contactPhone.toString(),
                contactPhoto = contactDetailed?.contactPhoto.toString()
            )
        }
    }



    private fun btnClick(
        contactName: String, contactSurname: String,
        contactPhone: String, contactPhoto: String
    ) {
        changefragment = ChangeContactFragment.newInstance(
            contactName = contactName,
            contactPhone = contactPhone,
            contactPhoto = contactPhoto,
            contactSurname = contactSurname
        )
        parentFragmentManager.beginTransaction().add(R.id.fragmentContainerView, changefragment)
            .commit()
        parentFragmentManager.beginTransaction().remove(this).commit()
    }

    private fun initViewForFragment(view: View) {

        detailedContactSurname = view.findViewById(R.id.detailedContactSurname)
        detailedContactName = view.findViewById(R.id.detailedContactName)
        detailedContactNumber = view.findViewById(R.id.detailedContactNumber)
        val detailedContactPhoto = view.findViewById<ImageView>(R.id.detailedContactPhoto)
        detailedContactBtn = view.findViewById(R.id.changeContactBtn)

        detailedContactSurname.text = contactDetailed?.contactSurname
        detailedContactName.text = contactDetailed?.contactName
        detailedContactNumber.text = contactDetailed?.contactPhone
        Glide.with(this).load(contactDetailed?.contactPhoto).into(detailedContactPhoto)

    }

    companion object {
        private const val CONTACT_PHOTO_ARG = "contact_photo_arg"
        const val CONTACT_SURNAME_ARG = "contact_surname_arg"
        const val CONTACT_NAME_ARG = "contact_name_arg"
        const val CONTACT_PHONE_ARG = "contact_phone_arg"

        @JvmStatic
        fun newInstance(
            contact: Contact
        ) =
            ContactDetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(CONTACT_NAME_ARG, contact)
                }
            }
    }

}

