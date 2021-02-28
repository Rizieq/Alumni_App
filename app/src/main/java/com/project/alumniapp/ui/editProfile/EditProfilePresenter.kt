package com.project.alumniapp.ui.editProfile

import com.project.alumniapp.model.ResponseUserEdit
import com.project.alumniapp.network.ApiClient
import com.project.alumniapp.ui.profile.ProfileContract
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditProfilePresenter(model: EditProfileContract.View) : EditProfileContract.Presenter {

    var view: EditProfileContract.View? = null


    init {
        view = model
    }
    override fun editProfile(
        image: MultipartBody.Part,
        cv: MultipartBody.Part,
        bio: RequestBody,
        tahunAlumni: RequestBody,
        token: String
    ) {
        val apiInterface = ApiClient.create()
        apiInterface.editProfile(image,cv,bio,tahunAlumni,token)
            .enqueue(object : Callback<ResponseUserEdit> {
                override fun onFailure(call: Call<ResponseUserEdit>, t: Throwable) {
                    t.message?.let { view?.showError(it) }
                }

                override fun onResponse(
                    call: Call<ResponseUserEdit>,
                    response: Response<ResponseUserEdit>
                ) {
                    if (response.body() != null){
                        val dataUser: ResponseUserEdit = response.body()!!
                        view?.showEditProfileSuccess(dataUser)
                    }
                }

            })
    }
}