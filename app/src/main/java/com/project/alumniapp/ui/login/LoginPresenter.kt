package com.project.alumniapp.ui.login

import android.util.Log
import com.project.alumniapp.model.ResponseLogin
import com.project.alumniapp.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter(model: LoginContract.View):LoginContract.Presenter {

    var view: LoginContract.View? = null
    init {
        view = model
    }
    override fun doLogin(email: String, password: String) {

        val apiInterface = ApiClient.create()
        apiInterface.login(email,password)
            .enqueue(object :Callback<ResponseLogin>{
                override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                    view?.showError(t.message!!)
                    Log.d("ERROR_FAILURE",t.localizedMessage)
                }

                override fun onResponse(
                    call: Call<ResponseLogin>,
                    response: Response<ResponseLogin>
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