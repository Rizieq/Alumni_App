package com.project.alumniapp.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.orhanobut.hawk.Hawk
import com.project.alumniapp.R
import com.project.alumniapp.data.PreferencesHelper
import com.project.alumniapp.model.Data
import com.project.alumniapp.model.ResponseUser
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity(),ProfileContract.View {

    private val preferencesHelper: PreferencesHelper? = null
    private val profilePresenter = ProfilePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)



        Log.d("RESULT_TOKEN","DATA_TEST")
//        val token: String = Hawk.get("token")
        profilePresenter.getProfile(" eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczpcL1wvdWppa29tLnBob2VuaXhjZW5kZWtpYS5jb21cL2FwaVwvbG9naW4iLCJpYXQiOjE2MTQyNjUzMDAsImV4cCI6MTYxNDI2ODkwMCwibmJmIjoxNjE0MjY1MzAwLCJqdGkiOiJQejR3M1Jpdko2aDJoRmdWIiwic3ViIjoxLCJwcnYiOiI4N2UwYWYxZWY5ZmQxNTgxMmZkZWM5NzE1M2ExNGUwYjA0NzU0NmFhIn0.EAztfDumyOaJ8lc6cfaUepXLRt89Xr6o6yWDS9ym2iA")



//        txtTest.text = Hawk.get("token")
    }

    override fun showError(msg: String) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
    }

    override fun showLoginSuccess(data: ResponseUser) {
        fieldName.setText(data.data?.name)
    }
}