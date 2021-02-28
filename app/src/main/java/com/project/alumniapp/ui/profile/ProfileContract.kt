package com.project.alumniapp.ui.profile

import com.project.alumniapp.model.ResponseUser
import com.project.alumniapp.model.ResponseUserEdit
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface ProfileContract {

    interface View{
        fun showError(msg: String)

        fun showGetProfileSuccess(data: ResponseUser)


    }

    interface Presenter{
        fun getProfile(token: String)


    }
}