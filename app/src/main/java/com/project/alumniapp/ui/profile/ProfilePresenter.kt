package com.project.alumniapp.ui.profile

import com.project.alumniapp.model.ResponseUser
import com.project.alumniapp.model.ResponseUserEdit
import com.project.alumniapp.network.ApiClient
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfilePresenter(model: ProfileContract.View) : ProfileContract.Presenter{

    var view: ProfileContract.View? = null


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
                            view?.showGetProfileSuccess(responseUser)
                        } else {
                            view?.showError(response.message())
                        }
                    }

                })
    }


}