 package com.project.alumniapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.orhanobut.hawk.Hawk
import com.project.alumniapp.data.PreferencesHelper
import com.project.alumniapp.model.ResponseLogin
import com.project.alumniapp.ui.HomeActivity
import com.project.alumniapp.ui.listAlumni.ListAlumniActivity
import com.project.alumniapp.ui.login.LoginContract
import com.project.alumniapp.ui.login.LoginPresenter
import com.project.alumniapp.ui.profile.ProfileActivity
import kotlinx.android.synthetic.main.activity_main.*



 class MainActivity : AppCompatActivity(),LoginContract.View {

     private var loginPresenter = LoginPresenter(this)
     private val preferencesHelper: PreferencesHelper? = null

     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_main)

         Hawk.init(this).build()



         signButton.setOnClickListener {
             loginPresenter.doLogin(fieldEmail.text.toString(),fieldPassword.text.toString())
         }
     }

     override fun showError(msg: String) {
         Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
     }

     override fun showLoginSuccess(msg: ResponseLogin?) {
         msg?.token?.let { preferencesHelper?.saveToken(it) }
         Hawk.put("token", msg?.token)
         startActivity(Intent(this, ListAlumniActivity::class.java))
         finish()
     }



     override fun moveToProfile() {
         TODO("Not yet implemented")
     }
 }