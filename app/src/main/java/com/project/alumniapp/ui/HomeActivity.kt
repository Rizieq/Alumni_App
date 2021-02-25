package com.project.alumniapp.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.orhanobut.hawk.Hawk
import com.project.alumniapp.R
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        Log.d("RESULT_TOKEN", Hawk.get("token"))
        textView.text = Hawk.get("token")

    }
}