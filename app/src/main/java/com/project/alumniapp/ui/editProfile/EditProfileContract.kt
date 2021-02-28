package com.project.alumniapp.ui.editProfile

import com.project.alumniapp.model.ResponseUser
import com.project.alumniapp.model.ResponseUserEdit
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface EditProfileContract {

    interface View{
        fun showError(msg: String)
        fun showEditProfileSuccess(data: ResponseUserEdit)
        


    }

    interface Presenter{
        fun editProfile(image: MultipartBody.Part, cv: MultipartBody.Part, bio : RequestBody, tahunAlumni: RequestBody, token: String)

    }
}