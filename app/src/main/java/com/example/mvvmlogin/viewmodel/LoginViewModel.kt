package com.example.mvvmlogin.viewmodel

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmlogin.model.LoginResult
import com.example.mvvmlogin.model.LoginState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.example.mvvmlogin.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.math.log

class LoginViewModel(private val loginRepository: LoginRepository):ViewModel() {

    private val _loginStateLiveData = MutableLiveData<LoginState>()
    val loginStateLiveData:LiveData<LoginState> = _loginStateLiveData

    private val _loginResultLiveData = MutableLiveData<LoginResult>()
    val loginResultLiveData:LiveData<LoginResult> = _loginResultLiveData

    /**
     * 默认没有登录
     */
    private val loginStateInfo = LoginState(loginState = LoginState.STA_NOLOGIN)

    /**
     * login
     */
    fun login(username:String,password:String){

        viewModelScope.launch {
            val result = loginRepository.login(username,password)
            //延迟一秒模拟登录过程
            delay(1000)

            if (result is Result.Success) {
                _loginStateLiveData.value = LoginState(loginState = LoginState.STA_SUCCESS)
                _loginResultLiveData.value = result.data
            }else if (result is Result.Fail){
                _loginStateLiveData.value = LoginState(loginState = LoginState.STA_FAIL)
                _loginResultLiveData.value = result.data
            }

        }
    }

    fun onUserTextChanged(text:Editable){
        loginRepository.user.userId = text.toString()
        loginIsValid()
    }

    fun onPasswordTextChanged(text: Editable){
        loginRepository.user.password = text.toString()
        loginIsValid()
    }

    /**
     * 判断信息是否合法
     * 使用postValue 因为setValue只能在主线程中执行
     */
    private fun loginIsValid(){
        viewModelScope.launch{
            withContext(Dispatchers.Default){
                if (loginRepository.user.isValid()){
                    loginStateInfo.loginState = LoginState.STA_VALID
                }else{
                    loginStateInfo.loginState = LoginState.STA_NOLOGIN
                }
                _loginStateLiveData.postValue(loginStateInfo)
            }
        }
    }

    /**
     * 点击登录的逻辑
     */
    fun loginOnClick(){
        login(loginRepository.user.userId,loginRepository.user.password)
        _loginStateLiveData.value = LoginState(loginState = LoginState.STA_ING)
    }

}