package com.project.alumniapp.ui.register



interface RegisterContract {

    interface View {
        fun showError(msg: String)
        fun showLoginSuccess(msg: String)

    }

    interface Presenter{
        fun doRegister(email: String, no_handphone: String, password: String, address: String, name: String)

    }
}