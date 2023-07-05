package com.example.mvvmlogin

import com.example.mvvmlogin.model.LoginResult
import com.example.mvvmlogin.model.UserInfo
import kotlinx.coroutines.delay

/**
 * 模拟登录
 * 第一次是失败，第二次成功
 */
class LoginSource {
    private var count = 0

    suspend fun login(username:String,password:String): com.example.mvvmlogin.Result<LoginResult> {
     return try {
         count++
         delay(100)
         if (count%2==0){
             val userInfo = UserInfo(username,"111",java.util.UUID.randomUUID().toString())

             val result = LoginResult(userInfo,"")
             Result.success(result)
         }else{
             val result = LoginResult(null,"登录失败")
             Result.failure(result)
         }
     }catch (e:Throwable){
         val result = LoginResult(null,"登录失败")
         Result.failure(result)
     }
    }
}


