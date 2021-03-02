package com.project.alumniapp.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.project.alumniapp.MainActivity
import com.project.alumniapp.R
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), RegisterContract.View {

    private var registerPresenter = RegisterPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        registerButton.setOnClickListener {
            registerPresenter.doRegister(fieldEmail.text.toString(),
            fieldNoHandphone.text.toString(),
            fieldPassword.text.toString(),
            fieldAddress.text.toString(),
            fieldName.text.toString())
        }
    }

    override fun showError(msg: String) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
    }

    override fun showLoginSuccess(msg: String) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
        startActivity(Intent(this,MainActivity::class.java))
    }
}