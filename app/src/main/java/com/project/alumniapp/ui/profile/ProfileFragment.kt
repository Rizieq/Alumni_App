package com.project.alumniapp.ui.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.orhanobut.hawk.Hawk
import com.project.alumniapp.R
import com.project.alumniapp.model.ResponseUser
import com.project.alumniapp.ui.editProfile.BottomSheetEdit
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*


/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment(), ProfileContract.View {

    private val profilePresenter = ProfilePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_profile, container, false)
        val token: String = Hawk.get("token")
        profilePresenter.getProfile("Bearer $token")

        v?.editButton?.setOnClickListener {
            val bottomsheet: BottomSheetEdit =
                BottomSheetEdit()
            activity?.supportFragmentManager?.let { it1 -> bottomsheet.show(it1,"bottomSheetEdit") }
        }
        return v
    }

    override fun showError(msg: String) {
        Toast.makeText(context,msg, Toast.LENGTH_SHORT).show()
    }



    override fun showGetProfileSuccess(data: ResponseUser) {
        Log.d("RESULT_TOKEN","DATA_TEST")
        textView11.setText(data.data?.name)
        textView12.setText(data.data?.bio)
        textView15.setText(data.data?.email)
        textView17.setText(data.data?.noHandphone)
        tvGraduation.setText(data.data?.tahunAlumni)
        activity?.let {
            Glide.with(it)
                .load("https://ujikom.phoenixcendekia.com/images/" + data.data?.image)
                .into(imageView)
        }
    }

}