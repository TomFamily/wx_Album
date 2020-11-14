package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView

class YkImageVeiw : androidx.appcompat.widget.AppCompatImageView {
    @SuppressLint("CustomViewStyleable", "Recycle")
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet){
        var attrArray = context.obtainStyledAttributes(attributeSet,R.styleable.YkImageVeiw)


    }

}