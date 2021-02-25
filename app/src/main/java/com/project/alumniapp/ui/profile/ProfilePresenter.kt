package com.project.alumniapp.ui.profile

import com.project.alumniapp.data.PreferencesHelper
import com.project.alumniapp.model.ResponseUser
import com.project.alumniapp.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfilePresenter(model: ProfileContract.View) : ProfileContract.Presenter{

    var view: ProfileContract.View? = null
    private var preferencesHelper: PreferencesHelper? = null

    init {
        view = model
    }
    override fun getProfile(token: String) {
        val apiInterface= ApiClient.create()
            apiInterface.profile(token)
                .enqueue(object : Callback<ResponseUser>{
                    override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                        view?.showError(t.message!!)
                    }

                    override fun onResponse(
                        call: Call<ResponseUser>,
                        response: Response<ResponseUser>
                    ) {
                        if (response.body() != null){
                            val responseUser: ResponseUser = response.body()!!
                            view?.showLoginSuccess(responseUser)
                        } else {
                            view?.showError(response.message())
                        }
                    }

                })
    }
}