package com.funcrib.expressouitestingpractice.ui.gallery

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.funcrib.expressouitestingpractice.R
import kotlinx.android.synthetic.main.activity_gallery.*

const val GALLERY_REQUEST_CODE = 1234

class GalleryActivity : AppCompatActivity() {
    private val TAG: String = "AppDebug"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)
        button_open_gallery.setOnClickListener {
            pickFromGallery()
        }
    }

    @SuppressLint("QueryPermissionsNeeded")
    private fun pickFromGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, GALLERY_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            Log.d(TAG, "RESULT OK")
            when (requestCode) {

                GALLERY_REQUEST_CODE -> {
                    Log.d(TAG, "GALLERY_REQUEST_CODE Detected.")
                    data?.data?.let { uri: Uri ->
                        Log.d(TAG, "URI: $uri")
                        Glide.with(this)
                            .load(uri)
                            .into(image)
                    }
                }
            }
        }
    }
}