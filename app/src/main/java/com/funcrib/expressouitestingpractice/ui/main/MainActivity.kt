package com.funcrib.expressouitestingpractice.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.input.input
import com.funcrib.expressouitestingpractice.R
import com.funcrib.expressouitestingpractice.ui.camera.CameraActivity
import com.funcrib.expressouitestingpractice.ui.gallery.GalleryActivity
import com.funcrib.expressouitestingpractice.ui.login.LoginActivity
import com.funcrib.expressouitestingpractice.ui.movie.MovieActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_register_activity.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        button_fragments.setOnClickListener {
            val intent = Intent(this, MovieActivity::class.java)
            startActivity(intent)
        }

        button_gallery.setOnClickListener {
            val intent = Intent(this, GalleryActivity::class.java)
            startActivity(intent)
        }

        button_camera.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            startActivity(intent)
        }

        button_launch_dialog.setOnClickListener {
            showDialog()
        }
    }
    private fun showDialog() {
        MaterialDialog(this).show {
            title(R.string.text_enter_name)
            positiveButton(R.string.text_ok)
            input (
                waitForPositiveButton = true,
                allowEmpty = false,
                    ) { materialDialog, name ->
                setNameToTextView(name.toString())
                showToast(buildToastMessage(name.toString()))
            }
        }
    }
    private fun setNameToTextView(name: String){
        text_name.text = name
    }
    private fun showToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object{
        fun buildToastMessage(name: String): String{
            return "Your name is $name."
        }
    }
}