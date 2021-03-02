package com.funcrib.expressouitestingpractice.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.funcrib.expressouitestingpractice.R
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

//        Get Extras
        val name: String? = intent.getStringExtra("name")
        val email: String? = intent.getStringExtra("email")
        val phoneNumber: String? = intent.getStringExtra("phoneNumber")
        val sex: String? = intent.getStringExtra("sex")

//        set the texView text

        tvName.text = name
        tvEmail.text = email
        tvNumber.text = phoneNumber
        tvSex.text = sex

    }

}