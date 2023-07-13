package com.example.mvvmlogin

import android.content.Context
import android.widget.Toast

fun showToast(str:String,context:Context){
    Toast.makeText(context,str,Toast.LENGTH_SHORT).show()
}