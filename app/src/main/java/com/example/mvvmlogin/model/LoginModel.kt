package com.example.mvvmlogin.model

import android.text.TextUtils

/**
 * 用户的账号信息
 * 判断输入的信息是否合规a
 */

data class LoginModel(var userId:String,var password:String){

    fun isValid():Boolean{
        return !TextUtils.isEmpty(userId) && !TextUtils.isEmpty(password) && password.length >= 6
    }
}
