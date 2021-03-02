package com.funcrib.expressouitestingpractice.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.funcrib.expressouitestingpractice.R
import com.funcrib.expressouitestingpractice.ui.login.ValidateRegistration.validateEmail
import com.funcrib.expressouitestingpractice.ui.login.ValidateRegistration.validateNumber
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        spinner.onItemSelectedListener = this
        button_login.setOnClickListener {
            val name: String = editTextPersonName.text.toString()
            val email: String = editTextTextEmailAddress.text.toString()
            val phoneNumber: String = editTextPhone.text.toString()
            val sex: String = spinner.selectedItem.toString()

            if (name.isEmpty() || email.isEmpty() || phoneNumber.isEmpty() || sex.isEmpty()) {
                Toast.makeText(this, "All Fields are Required!!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }else if (!validateEmail(email)){
                Toast.makeText(this, "Invalid Email", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }else if (!validateNumber(phoneNumber)) {
                Toast.makeText(this, "Enter Nigerian Number", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }else {
                val intent = Intent(this, ProfileActivity::class.java).apply {
                    putExtra("name", name)
                    putExtra("email", email)
                    putExtra("phoneNumber", phoneNumber)
                    putExtra("sex", sex)
                }

                startActivity(intent)
            }

        }


//      Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.Sex,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        parent?.getItemAtPosition(position)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

}