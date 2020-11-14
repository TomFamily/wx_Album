package com.example.myapplication

import android.content.Intent
import android.graphics.Rect
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Adapter
import android.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_album_activity.*
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.StringReader

class Album_activity : AppCompatActivity() {

    var selectedImageList = ArrayList<String>()
    var listA = ArrayList<String>()
        set(value) {
            field = value
            Log.v("listAa", value.size.toString())
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_activity)



        ykRecycleView.layoutManager = GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false)

        var path: String? = null
        ykRecycleView.adapter = ykAdapter().also {
            it.list = getImages() as ArrayList<String>
            it.callBack = { yk ->
                selectedImageList.add(yk)
                path = yk
            }
        }
        Log.v("listA", listA.size.toString())


        yktextView.setOnClickListener() {
            if (path != null) {
                Intent().also {
                    it.setClass(this, preview_Activity::class.java)
                    it.putExtra("selectedImageList", path)
                    startActivity(it)
                }
            }
        }
//        加边距
        ykRecycleView.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                super.getItemOffsets(outRect, view, parent, state)
                outRect.set(0, 0, 5, 5)
            }
        })

    }

    fun getImages(): List<String> {
        var list = ArrayList<String>()

//        获取图片信息
        val mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        var mCursor = contentResolver.query(mImageUri, null, null, null, null)

        var i = 0
        if (mCursor != null) {
            while (mCursor.moveToNext() && i < 50) {
                // 获取图片的路径
                val path =
                    mCursor.getString(mCursor.getColumnIndex(MediaStore.Images.Media.DATA))
                list.add(path)
                i++
            }

            mCursor.close()
        }
        return list
    }
}
