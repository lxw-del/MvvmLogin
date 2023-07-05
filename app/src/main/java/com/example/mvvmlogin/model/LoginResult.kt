package com.example.mvvmlogin.model

/**
 * 登录成功返回 UserInfo 信息，失败则返回 失败信息
 */
data class LoginResult(val success:UserInfo? = null,val error:String? = null)
