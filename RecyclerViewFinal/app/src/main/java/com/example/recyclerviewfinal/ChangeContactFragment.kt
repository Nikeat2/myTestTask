package com.example.recyclerviewfinal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult



class ChangeContactFragment : Fragment() {

    private var changeContactName: String? = null
    private var changeContactSurname: String? = null
    private var changeContactPhoto: String? = null
    private var changeContactPhone: String? = null
    private lateinit var changeNameEt: EditText
    private lateinit var saveTheChangesBtn: Button
    private lateinit var changeSurnameEt: EditText
    private lateinit var changePhoneEt: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            changeContactName = it.getString(CONTACT_NAME_ARG)
            changeContactSurname = it.getString(CONTACT_SURNAME_ARG)
            changeContactPhoto = it.getString(CONTACT_PHOTO_ARG)
            changeContactPhone = it.getString(CONTACT_PHONE_ARG)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_change_contact, container, false)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        saveTheChangesBtn.setOnClickListener {
            sendInfoBack()
            parentFragmentManager.beginTransaction().remove(this).commit()
            parentFragmentManager.popBackStack()
        }
    }




    private fun initView(view: View) {
        changeNameEt = view.findViewById(R.id.changeNameEt)
        changeSurnameEt = view.findViewById(R.id.changeSurnameEt)
        changePhoneEt = view.findViewById(R.id.changePhoneEt)
        saveTheChangesBtn = view.findViewById(R.id.saveTheChangesBtn)

        changeNameEt.setText(changeContactName)
        changeSurnameEt.setText(changeContactSurname)
        changePhoneEt.setText(changeContactPhone)
    }

    private fun sendInfoBack() {
        val userInputName: String = changeNameEt.text.toString()
        val userInputSurname: String = changeSurnameEt.text.toString()
        val userInputPhone: String = changePhoneEt.text.toString()

        setFragmentResult(
            CONTACT_NAME_ARG,
            bundleOf(
                CONTACT_NAME_ARG to userInputName,
                CONTACT_SURNAME_ARG to userInputSurname,
                CONTACT_PHONE_ARG to userInputPhone
            )
        )
    }

    companion object {
        private const val CONTACT_PHOTO_ARG = "contact_photo_arg"
        private const val CONTACT_SURNAME_ARG = "contact_surname_arg"
        private const val CONTACT_NAME_ARG = "contact_name_arg"
        private const val CONTACT_PHONE_ARG = "contact_phone_arg"

        @JvmStatic
        fun newInstance(
            contactName: String, contactSurname: String,
            contactPhone: String, contactPhoto: String
        ) =
            ChangeContactFragment().apply {
                arguments = Bundle().apply {
                    putString(CONTACT_PHOTO_ARG, contactPhoto)
                    putString(CONTACT_SURNAME_ARG, contactSurname)
                    putString(CONTACT_NAME_ARG, contactName)
                    putString(CONTACT_PHONE_ARG, contactPhone)
                }
            }
    }
}