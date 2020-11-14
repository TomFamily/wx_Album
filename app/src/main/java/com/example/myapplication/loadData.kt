package com.example.myapplication

import android.content.ContentResolver
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class loadData private constructor() : AppCompatActivity() {

    companion object{
        private var intance:loadData? = null
        fun getInstance():loadData{
            if(intance == null){
                intance = loadData()
            }
            return intance!!
        }
    }
}