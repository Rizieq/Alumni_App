package com.project.alumniapp.ui.login

import android.util.Log
import com.project.alumniapp.data.PreferencesHelper
import com.project.alumniapp.model.ResponseLogin
import com.project.alumniapp.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginPresenter(model: LoginContract.View):LoginContract.Presenter {

    var view: LoginContract.View? = null
    private var preferencesHelper: PreferencesHelper? = null

    init {
        view = model
    }
    override fun doLogin(email: String, password: String) {

        val apiInterface = ApiClient.create()
        apiInterface.login(email,password)
            .enqueue(object :Callback<ResponseLogin> {
                override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                    view?.showError(t.message!!)
                    Log.d("ERROR_FAILURE",t.localizedMessage)
                }

                override fun onResponse(
                    call: Call<ResponseLogin>,
                    response: Response<ResponseLogin>
                ) {
                    if (response.body() != null){
                        preferencesHelper?.saveLogin(response.body())
                        preferencesHelper?.hasLogin(true)
                        response.body()?.token.let {
                            if (it != null) {
                                preferencesHelper?.saveToken(it)
                            }
                        }
                        view?.showLoginSuccess(response.body())
                        Log.d("ERROR_ONRESPONSE_BODY",response.message())
                    } else{
                        view?.showError(response.message().toString())
                        Log.d("ERROR_ONRESPONSE_NO_BODY",response.message())
                    }
                }

            })
    }


}