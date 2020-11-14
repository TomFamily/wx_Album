package com.example.myapplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_preview_.*
import java.io.File

class preview_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview_)

        var a = intent.getStringExtra("selectedImageList").also {
            Log.v("selectedImageList:", it)

            var file  = File(it)
            var uri = Uri.fromFile(file)
            pImageVeiw.setImageURI(uri)
        }
    }
}
