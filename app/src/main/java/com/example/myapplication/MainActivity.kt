package com.example.myapplication

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import kotlinx.android.synthetic.main.activity_album_activity.*
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream


class MainActivity : AppCompatActivity() {

    private var CAMARA_CODE = 123
    private var IMAGE_CODE = 1234


    var list = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //        跳转到相机
        camara.setOnClickListener() {
            val intent = Intent()
            intent.action = MediaStore.ACTION_IMAGE_CAPTURE //拍照界面的隐式意图
            startActivityForResult(intent, CAMARA_CODE)
        }

        //        跳转到相册
        album.setOnClickListener {
//            Intent().also {
//                it.action = Intent.ACTION_PICK
//                it.type = "image/*"
//                startActivityForResult(it, IMAGE_CODE)
//            }

//            跳转到下一个界面，在其onCreate方法中获取相册的所有图片
            Intent().also {
                it.setClass(this,Album_activity::class.java)
                startActivity(it)
            }
        }


        //        获取头像图片（下一次进入程序时，去获取图片）
        File(filesDir, "header.jpg").also {
            if (it.exists()) {
                BitmapFactory.decodeFile(it.path).also { yk ->
                    ykImage.setImageBitmap(yk)
                }
            }
        }



//        动态授权(读取联系人）
        var yk2 = mutableListOf<String>(Manifest.permission.READ_CONTACTS)
//        判断项目的版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            检查项目是否有该权限（读取联系人的权限）
            if (ContextCompat.checkSelfPermission(this,Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                // 检查权限状态
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)) {
                    //  用户彻底拒绝授予权限，一般会提示用户进入设置权限界面进行设置
                } else {
                    //  用户未彻底拒绝授予权限，提示用户授予相应的去权限
                    ActivityCompat.requestPermissions(this, yk2.toTypedArray(), 1)
//                    要做的事
                }
            } else {
//                有权限，要做的事
            }
        }

//        对相册的读写操作
        var yk4 = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            该两个地方
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // 检查权限状态
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
                ) {
                    //  用户彻底拒绝授予权限，一般会提示用户进入设置权限界面
                } else {
                    Log.v("yk", "无权")
                    //  用户未彻底拒绝授予权限
                    ActivityCompat.requestPermissions(this, yk4, 1)
                }
            } else {
            }
        }
//        获取图片数据
        var yk3 = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            该两个地方
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // 检查权限状态
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        this,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    )
                ) {
                    //  用户彻底拒绝授予权限，一般会提示用户进入设置权限界面
//                    getMsgs()
                } else {
                    Log.v("yk", "无权")
                    //  用户未彻底拒绝授予权限
                    ActivityCompat.requestPermissions(this, yk3, 1)
//                    getMsgs()
                }
            } else {
//                getMsgs()
            }
        }

        var main = Album_activity().also {
            it.listA = list
        }


    }


    private fun getMsgs() {

////        获取联系人信息
//        Log.v("yk","被调用了")
//        val resolver: ContentResolver = contentResolver
//        val uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
//        var cursor = resolver.query(uri, null, null, null, null)
//        if (cursor != null) {
//            while (cursor.moveToNext()) {
//                //获取联系人姓名,手机号码
//                val cName: String =
//                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
//                val cNum: String =
//                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
//                Log.v("人名：",cName)
//                Log.v("电话号码：",cNum)
//                println("======================")
//            }
//        }
//        cursor?.close()


//        获取图片信息
        val mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        var mCursor = contentResolver.query(mImageUri, null, null, null, null)
        var i = 0
        if (mCursor != null) {
            while (mCursor.moveToNext()) {
                // 获取图片的路径
                val path =
                    mCursor.getString(mCursor.getColumnIndex(MediaStore.Images.Media.DATA))
                val size =
                    mCursor.getInt(mCursor.getColumnIndex(MediaStore.Images.Media.SIZE)) / 1024
                val displayName =
                    mCursor.getString(mCursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME))
                Log.v("图片大小：", size.toString())
                Log.v("图片名：", displayName)
                Log.v("图片路径：", path)
                if(i == 1){
                    var file = File(path)
                    if (file.exists()){
                        Log.v("文件不存在","文件存在")
                        var uri = Uri.fromFile(file)
                        ykImage.setImageURI(uri)
                    }else{
                        Log.v("文件不存在","文件不存在")
                    }
                }
                i ++
                list.add(path)
            }
            mCursor.close()
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            IMAGE_CODE -> {
                if (resultCode != Activity.RESULT_CANCELED) {
                    val uri: Uri? = data?.data
                    Log.v("yk", "你好啊： $data")
                    Log.v("yk", data?.data.toString())
                    uri?.let {
                        contentResolver.openInputStream(uri).use {
                            BitmapFactory.decodeStream(it).apply {
//                                显示图片
                                ykImage.setImageBitmap(this)
//                                缓存图片： 创建保存图片的文件
                                var file = File(filesDir, "header.jpg")
                                FileOutputStream(file).also { yk2 ->
//                                    将图片保存到对应的路径中
                                    compress(Bitmap.CompressFormat.JPEG, 50, yk2)
                                }
                            }
                        }
                    }
                }
            }
            CAMARA_CODE -> {
                if (resultCode != Activity.RESULT_CANCELED) {
                    Log.v("yk", "获取到图片了")
                    val bundle = data!!.extras //bundle 可以存放键值对

                    val bm = bundle!!["data"] as Bitmap?
                    if (bm != null) {
                        ykImage.setImageBitmap(bm)
                    }
                }
            }
        }
    }

    //  一般重写这两个方法就行了
    @SuppressLint("ResourceType")
    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        var inflater = MenuInflater(this)
        inflater.inflate(R.menu.yk_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.option_normal_1 -> camara.setTextColor(Color.RED)
            R.id.option_normal_2 -> camara.setTextColor(Color.GREEN)
            R.id.option_normal_3 -> camara.setTextColor(Color.BLUE)
            R.id.option_normal_4 -> camara.setTextColor(Color.YELLOW)
        }
        return super.onOptionsItemSelected(item)
    }
}
