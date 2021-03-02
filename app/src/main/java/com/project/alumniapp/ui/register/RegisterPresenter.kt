package com.project.alumniapp.ui.register

import android.util.Log
import com.project.alumniapp.model.ResponseRegister
import com.project.alumniapp.network.ApiClient
import com.project.alumniapp.ui.login.LoginContract
import com.project.alumniapp.ui.login.LoginPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterPresenter(model: RegisterContract.View): RegisterContract.Presenter {

    var view: RegisterContract.View? = null
    init {
        view = model
    }

    override fun doRegister(
        email: String,
        no_handphone: String,
        password: String,
        address: String,
        name: String
    ) {
        val apiInterface = ApiClient.create()
        apiInterface.register(address, name, no_handphone, password, email)
            .enqueue(object : Callback<ResponseRegister>{
                override fun onFailure(call: Call<ResponseRegister>, t: Throwable) {
                    view?.showError(t.message!!)
                    Log.d("ERROR_FAILURE",t.localizedMessage)
                }

                override fun onResponse(
                    call: Call<ResponseRegister>,
                    response: Response<ResponseRegister>
                ) {
                    if (response?.body() != null){
                        view?.showLoginSuccess(response.message().toString())
                        Log.d("ERROR_ONRESPONSE_BODY",response.message())
                    } else{
                        view?.showError(response.message().toString())
                        Log.d("ERROR_ONRESPONSE_NO_BODY",response.message())
                    }
                }

            })
    }

}