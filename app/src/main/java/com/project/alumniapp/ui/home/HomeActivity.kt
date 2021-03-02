package com.project.alumniapp.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.project.alumniapp.R
import com.project.alumniapp.ui.AddFragment
import com.project.alumniapp.ui.listAlumni.ListAlumniFragment
import com.project.alumniapp.ui.profile.ProfileFragment
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() {


    private val mOnNavigationItemSelected = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when(item.itemId){
            R.id.homePosting -> {
                replaceFragment(HomeFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.addPost -> {
                replaceFragment(AddFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.listAlumni -> {
                replaceFragment(ListAlumniFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.profile -> {
                replaceFragment(ProfileFragment())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bottomNavigation.setOnNavigationItemSelectedListener (mOnNavigationItemSelected)

//        Log.d("RESULT_TOKEN", Hawk.get("token"))
//        textView.text = Hawk.get("token")

    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentTrasaction = supportFragmentManager.beginTransaction()
        fragmentTrasaction.replace(R.id.fragmentContainer, fragment)
        fragmentTrasaction.commit()
    }

}