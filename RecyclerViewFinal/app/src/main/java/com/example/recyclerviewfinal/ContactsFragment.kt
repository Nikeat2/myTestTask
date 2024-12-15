package com.example.recyclerviewfinal

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ContactsFragment : Fragment() {

    private lateinit var fragmentDetails: ContactDetailsFragment
    private lateinit var recyclerView: RecyclerView
    lateinit var adapter: ContactAdapter
    lateinit var contactList: MutableList<Contact>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_contacts, container, false)
        val searchEditText: EditText = view.findViewById(R.id.searchEditText)
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.filter(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerView)
        initView()

        adapter = ContactAdapter(contactList)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        adapter.setOnItemClickListener(object : ContactAdapter.OnItemClickListener {
            override fun onClick(position: Int) {
                val contact = contactList[position]
                val contactName = contactList[position].contactName
                val contactSurname = contactList[position].contactSurname
                val contactPhone = contactList[position].contactPhone
                val contactPhoto = contactList[position].contactPhoto

                sendInfo(
                    contact = contact
                )

                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, fragmentDetails)
                    .addToBackStack("gang").commit()

                setFragmentResultListener(CONTACT_NAME_ARG) { requestKey, bundle ->

                    val resultName = bundle.getString(ContactDetailsFragment.CONTACT_NAME_ARG)
                    val resultSurname = bundle.getString(ContactDetailsFragment.CONTACT_SURNAME_ARG)
                    val resultPhone = bundle.getString(ContactDetailsFragment.CONTACT_PHONE_ARG)

                    updateContactName(position, resultName.toString())
                    updateContactSurname(position, resultSurname.toString())
                    updateContactPhone(position, resultPhone.toString())
                }
            }

            override fun onLongClick(position: Int) {
                showDialogToDelete(position)
            }
        })
    }

    private fun showDialogToDelete(position: Int) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Delete this contact")
            .setMessage("Are you sure?")
            .setPositiveButton("Yes") { dialog, _ ->
                contactList.removeAt(position)
                adapter.notifyItemChanged(position)
                adapter.notifyItemRangeChanged(position, contactList.size)
            }
        builder.create().show()
    }

    private fun sendInfo(
        contact: Contact
    ) {
        fragmentDetails = ContactDetailsFragment.newInstance(
            contact = contact
        )
    }


    private fun initView() {

        contactList = mutableListOf(

            Contact(
                "John",
                "Doe",
                "(123) 456-7890",
                "https://randomuser.me/api/portraits/men/1.jpg"
            ),
            Contact(
                "Jane",
                "Smith",
                "(234) 567-8901",
                "https://randomuser.me/api/portraits/women/1.jpg"
            ),
            Contact(
                "Alice",
                "Johnson",
                "(345) 678-9012",
                "https://randomuser.me/api/portraits/women/2.jpg"
            ),
            Contact(
                "Bob",
                "Williams",
                "(456) 789-0123",
                "https://randomuser.me/api/portraits/men/2.jpg"
            ),
            Contact(
                "Charlie",
                "Brown",
                "(567) 890-1234",
                "https://randomuser.me/api/portraits/men/3.jpg"
            ),
            Contact(
                "David",
                "Jones",
                "(678) 901-2345",
                "https://randomuser.me/api/portraits/men/4.jpg"
            ),
            Contact(
                "Eva",
                "Garcia",
                "(789) 012-3456",
                "https://randomuser.me/api/portraits/women/3.jpg"
            ),
            Contact(
                "Fiona",
                "Martinez",
                "(890) 123-4567",
                "https://randomuser.me/api/portraits/women/4.jpg"
            ),
            Contact(
                "George",
                "Anderson",
                "(901) 234-5678",
                "https://randomuser.me/api/portraits/men/5.jpg"
            ),
            Contact(
                "Hannah",
                "Thomas",
                "(012) 345-6789",
                "https://randomuser.me/api/portraits/women/5.jpg"
            ),
            Contact(
                "Ivan",
                "Taylor",
                "(111) 456-7890",
                "https://randomuser.me/api/portraits/men/6.jpg"
            ),
            Contact(
                "Julia",
                "Moore",
                "(222) 567-8901",
                "https://randomuser.me/api/portraits/women/6.jpg"
            ),
            Contact(
                "Kevin",
                "Jackson",
                "(333) 678-9012",
                "https://randomuser.me/api/portraits/men/7.jpg"
            ),
            Contact(
                "Lily",
                "Martin",
                "(444) 789-0123",
                "https://randomuser.me/api/portraits/women/7.jpg"
            ),
            Contact(
                "Michael",
                "Lee",
                "(555) 890-1234",
                "https://randomuser.me/api/portraits/men/8.jpg"
            ),
            Contact(
                "Nina",
                "Clark",
                "(666) 901-2345",
                "https://randomuser.me/api/portraits/women/8.jpg"
            ),
            Contact(
                "Oscar",
                "Hernandez",
                "(777) 012-3456",
                "https://randomuser.me/api/portraits/men/9.jpg"
            ),
            Contact(
                "Paula",
                "Robinson",
                "(888) 123-4567",
                "https://randomuser.me/api/portraits/women/9.jpg"
            ),
            Contact(
                "Quinn",
                "Lewis",
                "(999) 234-5678",
                "https://randomuser.me/api/portraits/men/10.jpg"
            ),
            Contact(
                "Ray",
                "Walker",
                "(000) 345-6789",
                "https://randomuser.me/api/portraits/men/11.jpg"
            ),
            Contact(
                "Sarah",
                "Hall",
                "(101) 456-7890",
                "https://randomuser.me/api/portraits/women/10.jpg"
            ),
            Contact(
                "Tom",
                "Allen",
                "(202) 567-8901",
                "https://randomuser.me/api/portraits/men/12.jpg"
            ),
            Contact(
                "Uma",
                "Young",
                "(303) 678-9012",
                "https://randomuser.me/api/portraits/women/11.jpg"
            ),
            Contact(
                "Vince",
                "King",
                "(404) 789-0123",
                "https://randomuser.me/api/portraits/men/13.jpg"
            ),
            Contact(
                "Wendy",
                "Wright",
                "(505) 890-1234",
                "https://randomuser.me/api/portraits/women/12.jpg"
            ),
            Contact(
                "Xander",
                "Scott",
                "(606) 901-2345",
                "https://randomuser.me/api/portraits/men/14.jpg"
            ),
            Contact(
                "Yara",
                "Green",
                "(707) 012-3456",
                "https://randomuser.me/api/portraits/women/13.jpg"
            ),
            Contact(
                "Zach",
                "Adams",
                "(808) 123-4567",
                "https://randomuser.me/api/portraits/men/15.jpg"
            ),
            Contact(
                "Anna",
                "Bell",
                "(909) 234-5678",
                "https://randomuser.me/api/portraits/women/14.jpg"
            ),
            Contact(
                "Brian",
                "Carter",
                "(010) 345-6789",
                "https://randomuser.me/api/portraits/men/16.jpg"
            ),
            Contact(
                "Cathy",
                "Kim",
                "(111) 456-7890",
                "https://randomuser.me/api/portraits/women/15.jpg"
            ),
            Contact(
                "Derek",
                "Davis",
                "(222) 567-8901",
                "https://randomuser.me/api/portraits/men/17.jpg"
            ),
            Contact(
                "Ella",
                "Garcia",
                "(333) 678-9012",
                "https://randomuser.me/api/portraits/women/16.jpg"
            ),
            Contact(
                "Frank",
                "Green",
                "(444) 789-0123",
                "https://randomuser.me/api/portraits/men/18.jpg"
            ),
            Contact(
                "Grace",
                "Roberts",
                "(555) 890-1234",
                "https://randomuser.me/api/portraits/women/17.jpg"
            ),
            Contact(
                "Harry",
                "Taylor",
                "(666) 901-2345",
                "https://randomuser.me/api/portraits/men/19.jpg"
            ),
            Contact(
                "Ivy",
                "Martinez",
                "(777) 012-3456",
                "https://randomuser.me/api/portraits/women/18.jpg"
            ),
            Contact(
                "Jack",
                "Hernandez",
                "(888) 123-4567",
                "https://randomuser.me/api/portraits/men/20.jpg"
            ),
            Contact(
                "Kathy",
                "Lee",
                "(999) 234-5678",
                "https://randomuser.me/api/portraits/women/19.jpg"
            ),
            Contact(
                "Leo",
                "King",
                "(010) 345-6789",
                "https://randomuser.me/api/portraits/men/21.jpg"
            ),
            Contact(
                "Maya",
                "Scott",
                "(121) 456-7890",
                "https://randomuser.me/api/portraits/women/20.jpg"
            ),
            Contact(
                "Nate",
                "Hall",
                "(232) 567-8901",
                "https://randomuser.me/api/portraits/men/22.jpg"
            ),
            Contact(
                "Olivia",
                "Young",
                "(343) 678-9012",
                "https://randomuser.me/api/portraits/women/21.jpg"
            ),
            Contact(
                "Paul",
                "Jackson",
                "(454) 789-0123",
                "https://randomuser.me/api/portraits/men/23.jpg"
            ),
            Contact(
                "Rita",
                "Adams",
                "(565) 890-1234",
                "https://randomuser.me/api/portraits/women/22.jpg"
            ),
            Contact(
                "Sam",
                "Moore",
                "(676) 901-2345",
                "https://randomuser.me/api/portraits/men/24.jpg"
            ),
            Contact(
                "Tina",
                "Clark",
                "(787) 012-3456",
                "https://randomuser.me/api/portraits/women/23.jpg"
            ),
            Contact(
                "Ursula",
                "Carter",
                "(898) 123-4567",
                "https://randomuser.me/api/portraits/women/24.jpg"
            ),
            Contact(
                "Vera",
                "Martinez",
                "(909) 234-5678",
                "https://randomuser.me/api/portraits/women/25.jpg"
            ),
            Contact(
                "Walter",
                "Baker",
                "(101) 345-6789",
                "https://randomuser.me/api/portraits/men/25.jpg"
            ),
            Contact(
                "Xena",
                "Lopez",
                "(212) 456-7890",
                "https://randomuser.me/api/portraits/women/26.jpg"
            ),
            Contact(
                "Yvonne",
                "Jones",
                "(323) 567-8901",
                "https://randomuser.me/api/portraits/women/27.jpg"
            ),
            Contact(
                "Zane",
                "Hernandez",
                "(434) 678-9012",
                "https://randomuser.me/api/portraits/men/26.jpg"
            ),
            Contact(
                "Ava",
                "Perez",
                "(545) 789-0123",
                "https://randomuser.me/api/portraits/women/28.jpg"
            ),
            Contact(
                "Bryan",
                "Hall",
                "(656) 890-1234",
                "https://randomuser.me/api/portraits/men/27.jpg"
            ),
            Contact(
                "Clara",
                "Garcia",
                "(767) 901-2345",
                "https://randomuser.me/api/portraits/women/29.jpg"
            ),
            Contact(
                "Daniel",
                "Rodriguez",
                "(878) 012-3456",
                "https://randomuser.me/api/portraits/men/28.jpg"
            ),
            Contact(
                "Emily",
                "Flores",
                "(989) 123-4567",
                "https://randomuser.me/api/portraits/women/30.jpg"
            ),
            Contact(
                "Felix",
                "Gonzalez",
                "(090) 234-5678",
                "https://randomuser.me/api/portraits/men/29.jpg"
            ),
            Contact(
                "Gina",
                "Lee",
                "(101) 345-6789",
                "https://randomuser.me/api/portraits/women/31.jpg"
            ),
            Contact(
                "Henry",
                "Martinez",
                "(212) 456-7890",
                "https://randomuser.me/api/portraits/men/30.jpg"
            ),
            Contact(
                "Iris",
                "Murphy",
                "(323) 567-8901",
                "https://randomuser.me/api/portraits/women/32.jpg"
            ),
            Contact(
                "Jack",
                "Jones",
                "(434) 678-9012",
                "https://randomuser.me/api/portraits/men/31.jpg"
            ),
            Contact(
                "Kara",
                "Butler",
                "(545) 789-0123",
                "https://randomuser.me/api/portraits/women/33.jpg"
            ),
            Contact(
                "Leo",
                "Bell",
                "(656) 890-1234",
                "https://randomuser.me/api/portraits/men/32.jpg"
            ),
            Contact(
                "Molly",
                "Davis",
                "(767) 901-2345",
                "https://randomuser.me/api/portraits/women/34.jpg"
            ),
            Contact(
                "Nico",
                "Clark",
                "(878) 012-3456",
                "https://randomuser.me/api/portraits/men/33.jpg"
            ),
            Contact(
                "Opal",
                "Wilson",
                "(989) 123-4567",
                "https://randomuser.me/api/portraits/women/35.jpg"
            ),
            Contact(
                "Pete",
                "Gilbert",
                "(090) 234-5678",
                "https://randomuser.me/api/portraits/men/34.jpg"
            ),
            Contact(
                "Quinn",
                "Edwards",
                "(101) 345-6789",
                "https://randomuser.me/api/portraits/women/36.jpg"
            ),
            Contact(
                "Ralph",
                "Hernandez",
                "(212) 456-7890",
                "https://randomuser.me/api/portraits/men/35.jpg"
            ),
            Contact(
                "Sophie",
                "Kim",
                "(323) 567-8901",
                "https://randomuser.me/api/portraits/women/37.jpg"
            ),
            Contact(
                "Tom",
                "Palmer",
                "(434) 678-9012",
                "https://randomuser.me/api/portraits/men/36.jpg"
            ),
            Contact(
                "Ulysses",
                "Foster",
                "(545) 789-0123",
                "https://randomuser.me/api/portraits/men/37.jpg"
            ),
            Contact(
                "Violet",
                "Mason",
                "(656) 890-1234",
                "https://randomuser.me/api/portraits/women/38.jpg"
            ),
            Contact(
                "Walter",
                "Rivers",
                "(767) 901-2345",
                "https://randomuser.me/api/portraits/men/38.jpg"
            ),
            Contact(
                "Xena",
                "Ellis",
                "(878) 012-3456",
                "https://randomuser.me/api/portraits/women/39.jpg"
            ),
            Contact(
                "Yardley",
                "Hughes",
                "(989) 123-4567",
                "https://randomuser.me/api/portraits/men/39.jpg"
            ),
            Contact(
                "Zara",
                "Woods",
                "(090) 234-5678",
                "https://randomuser.me/api/portraits/women/40.jpg"
            )
        )
    }

    fun updateContactName(position: Int, newName: String) {
        if (position in contactList.indices) {
            contactList[position].contactName = newName
            adapter.updateContactName(position, newName)
        }
    }

    fun updateContactSurname(position: Int, newSurname: String) {
        if (position in contactList.indices) {
            contactList[position].contactSurname = newSurname
            adapter.updateContactSurname(position, newSurname)
        }
    }

    fun updateContactPhone(position: Int, newPhone: String) {
        if (position in contactList.indices) {
            contactList[position].contactPhone = newPhone
            adapter.updateContactPhone(position, newPhone)
        }
    }

    companion object {
        private const val CONTACT_PHOTO_ARG = "contact_photo_arg"
        private const val CONTACT_SURNAME_ARG = "contact_surname_arg"
        private const val CONTACT_NAME_ARG = "contact_name_arg"
        private const val CONTACT_PHONE_ARG = "contact_phone_arg"
    }

}