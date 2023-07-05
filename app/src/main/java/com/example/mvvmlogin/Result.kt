package com.example.mvvmlogin

/**
 * 自定义Result类
 */

sealed class Result<out T:Any>{

    data class Success<out T:Any>(val data:T):com.example.mvvmlogin.Result<T>()
    data class Fail<out T:Any>(val data:T):com.example.mvvmlogin.Result<T>()

    companion object{
        fun<T:Any> success(data:T):com.example.mvvmlogin.Result<T>{
            return Result.Success(data)
        }

        fun<T:Any> failure(data:T):com.example.mvvmlogin.Result<T>{
            return Result.Fail(data)
        }
    }
}