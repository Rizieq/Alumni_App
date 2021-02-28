package com.project.alumniapp.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.orhanobut.hawk.Hawk
import com.project.alumniapp.R
import com.project.alumniapp.data.PreferencesHelper
import com.project.alumniapp.model.ResponseUser
import com.project.alumniapp.model.ResponseUserEdit
import com.project.alumniapp.ui.editProfile.BottomSheetEdit
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity(),ProfileContract.View {

    private val preferencesHelper: PreferencesHelper? = null
    private val profilePresenter = ProfilePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val token: String = Hawk.get("token")
        profilePresenter.getProfile("Bearer $token")

        editButton.setOnClickListener {
            val bottomsheet: BottomSheetEdit =
                BottomSheetEdit()
            bottomsheet.show(supportFragmentManager,"bottomSheetEdit")
        }


    }

    override fun showError(msg: String) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
    }



    override fun showGetProfileSuccess(data: ResponseUser) {
        Log.d("RESULT_TOKEN","DATA_TEST")
        fieldName.setText(data.data?.name)
        fieldEmail.setText(data.data?.email)
        fieldNoHandphone.setText(data.data?.noHandphone)
        fieldPassword.setText(data.data?.password)
        fieldGraduationYear.setText(data.data?.tahunAlumni)
    }


}