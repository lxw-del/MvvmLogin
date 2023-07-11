package com.example.mvvmlogin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmlogin.LoginSource

@Suppress("UNCHECKED_CAST")
class LoginViewModelFactory:ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)){
            return LoginViewModel(loginRepository = LoginRepository(
                dataSource = LoginSource()
            )
            )as T
        }else{
            throw java.lang.IllegalArgumentException("Unknown ViewModel class")
        }
    }
}