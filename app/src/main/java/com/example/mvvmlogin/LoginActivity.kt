package com.example.mvvmlogin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmlogin.databinding.ActivityLoginBinding
import com.example.mvvmlogin.viewmodel.LoginViewModel
import com.example.mvvmlogin.viewmodel.LoginViewModelFactory
import com.google.android.material.dialog.MaterialDialogs

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel

    private val progressBar:ProgressBar = ProgressBar(this)

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
        val binding = DataBindingUtil
            .setContentView<ActivityLoginBinding>(this,R.layout.activity_login)

        loginViewModel = ViewModelProvider(this,LoginViewModelFactory())[LoginViewModel::class.java]
        binding.lifecycleOwner = this
        binding.viewModel = loginViewModel


    }
}