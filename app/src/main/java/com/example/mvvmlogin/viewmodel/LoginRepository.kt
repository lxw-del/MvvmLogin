package com.example.mvvmlogin.viewmodel

import com.example.mvvmlogin.LoginSource
import com.example.mvvmlogin.model.LoginModel
import com.example.mvvmlogin.model.LoginResult

/**
 * Model层仓库
 * 在这里为View层提供接口
 */

class LoginRepository(private val dataSource: LoginSource) {

    //提供一个变量，随着数据层更改，View层也会更改
    val user:LoginModel by lazy {
        LoginModel("","")
    }

    suspend fun login(username:String,password:String):com.example.mvvmlogin.Result<LoginResult>{
        return dataSource.login(username,password)
    }



}