package com.project.alumniapp.ui.login

import com.project.alumniapp.model.ResponseLogin

interface LoginContract {

    interface View{
        fun showError(msg: String)
        fun showLoginSuccess(msg: ResponseLogin?)
        fun moveToProfile()

    }

    interface Presenter{
        fun doLogin(email: String, password: String)

    }

}