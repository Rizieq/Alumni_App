 package com.project.alumniapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.project.alumniapp.model.ResponseLogin
import com.project.alumniapp.ui.HomeActivity
import com.project.alumniapp.ui.login.LoginContract
import com.project.alumniapp.ui.login.LoginPresenter
import kotlinx.android.synthetic.main.activity_main.*


 class MainActivity : AppCompatActivity(),LoginContract.View {


     private var loginPresenter = LoginPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        signButton.setOnClickListener {
            loginPresenter.doLogin(fieldEmail.text.toString(),fieldPassword.text.toString())

        }
    }

     override fun showError(msg: String) {
         Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
     }

     override fun showLoginSuccess(user: ResponseLogin) {
         startActivity(Intent(this, HomeActivity::class.java))
         Toast.makeText(this,user.message,Toast.LENGTH_SHORT).show()
     }
 }