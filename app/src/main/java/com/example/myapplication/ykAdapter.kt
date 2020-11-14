package com.example.myapplication

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class ykAdapter : RecyclerView.Adapter<ykAdapter.ykVeiwHolder>() {

    var callBack:((String)-> Unit)? = null

    class ykVeiwHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var imageView = itemView.findViewById<ImageView>(R.id.ykImageView)
        var circleImage = itemView.findViewById<ImageView>(R.id.circleImage)
    }
    var list = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ykVeiwHolder {
        var inflater = LayoutInflater.from(parent.context)
        var ykimageveiw = inflater.inflate(R.layout.item_layout,parent,false)

        return ykVeiwHolder(ykimageveiw)
    }

    override fun getItemCount(): Int {
//        return list.size
        return 100
    }

    override fun onBindViewHolder(holder: ykVeiwHolder, position: Int) {
        var a = list[position]
        var file = File(a)
        if (file.exists()){
//            Log.v("文件不存在","文件存在")
            var uri = Uri.fromFile(file)
//            holder.itemView.setImageURI(uri)
            holder.imageView.setImageURI(uri)
        }else{
//            Log.v("文件不存在","文件不存在")
        }

//        holder.imageView.setOnClickListener(){
//            Log.v("被点击了：", position.toString())
//
//            callBack?.let {
//                it(a)
//            }
//        }

        holder.circleImage.setOnClickListener(){
            if (holder.circleImage.isSelected){
                holder.circleImage.isSelected = false
                holder.circleImage.setBackgroundResource(R.drawable.circle)
            }else{
                holder.circleImage.isSelected = true
                holder.circleImage.setBackgroundResource(R.drawable.circle2)
            }
            callBack?.let {
                it(a)
            }
        }

    }


}