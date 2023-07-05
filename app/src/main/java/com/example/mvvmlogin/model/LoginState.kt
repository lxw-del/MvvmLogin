package com.example.mvvmlogin.model

/**
 * 登录的5个状态
 */

data class LoginState(var loginState:Int = STA_NOLOGIN){

    companion object{
        const val STA_NOLOGIN = 0
        const val STA_VALID   = 1
        const val STA_ING     = 2
        const val STA_SUCCESS = 3
        const val STA_FAIL    = 4
    }
}
