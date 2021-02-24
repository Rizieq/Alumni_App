package com.project.alumniapp.ui.login

import com.project.alumniapp.model.ResponseLogin

interface LoginContract {

    interface View{
        fun showError(msg: String)
        fun showLoginSuccess(user: ResponseLogin)

    }

    interface Presenter{
        fun doLogin(email: String, password: String)

    }

}