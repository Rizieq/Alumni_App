package com.project.alumniapp.ui.profile

import com.project.alumniapp.model.Data
import com.project.alumniapp.model.ResponseUser

interface ProfileContract {

    interface View{
        fun showError(msg: String)
        fun showLoginSuccess(data: ResponseUser)


    }

    interface Presenter{
        fun getProfile(token: String)

    }
}