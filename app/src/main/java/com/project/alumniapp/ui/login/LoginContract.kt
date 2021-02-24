package com.project.alumniapp.ui.login

interface LoginContract {

    interface View{
        fun showError(msg: String)
        fun showLoginSuccess(msg: String)

    }

    interface Presenter{
        fun doLogin(email: String, password: String)

    }

}