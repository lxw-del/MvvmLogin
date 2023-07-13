package com.example.mvvmlogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmlogin.databinding.ActivityLoginBinding
import com.example.mvvmlogin.model.LoginState
import com.example.mvvmlogin.viewmodel.LoginViewModel
import com.example.mvvmlogin.viewmodel.LoginViewModelFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel

    private lateinit var progressBar:ProgressBar

    private val dialog:AlertDialog by lazy {
        AlertDialog.Builder(this)
        .setTitle("登录中")
        .setView(progressBar)
        .setCancelable(false)
        .create()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //DataBinding 数据层和UI层的绑定
        val binding = DataBindingUtil.setContentView<ActivityLoginBinding>(this,R.layout.activity_login)

        progressBar = ProgressBar(this)

        loginViewModel = ViewModelProvider(this,LoginViewModelFactory())[LoginViewModel::class.java]
        binding.lifecycleOwner = this
        binding.viewModel = loginViewModel

        loginViewModel.loginStateLiveData.observe(this) {
            when(it.loginState){
                LoginState.STA_ING -> {
                    showProgressDialog()
                }
                LoginState.STA_FAIL,LoginState.STA_SUCCESS -> {
                    dismissProgressDialog()
                }
            }
            loginViewModel.loginIsValid()
        }

        loginViewModel.loginResultLiveData.observe(this){
            if (it.success != null){
                showToast("user:${it.success.displayName}",this)
                goMain()
            }
            if (it.error != null){
                showToast(it.error,this)
            }
        }
    }

    private fun showProgressDialog(){
        dialog.show()
    }

    private fun dismissProgressDialog(){
        dialog.dismiss()
    }

    private fun goMain(){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}