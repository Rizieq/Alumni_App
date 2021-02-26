package com.project.alumniapp.ui.listAlumni

import com.project.alumniapp.model.DataItem
import com.project.alumniapp.model.ResponseAlumni
import com.project.alumniapp.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListAlumniPresenter(model: ListAlumniContract.View): ListAlumniContract.Presenter {

    var view: ListAlumniContract.View? = null


    init {
        view = model
    }

    override fun showData(token: String) {
        val apiInterface = ApiClient.create()
        apiInterface.listAlumni(token)
            .enqueue(object : Callback<ResponseAlumni>{
                override fun onFailure(call: Call<ResponseAlumni>, t: Throwable) {
                    t.message?.let { view?.showFailureMessage(it) }
                }

                override fun onResponse(
                    call: Call<ResponseAlumni>,
                    response: Response<ResponseAlumni>
                ) {
                    if (response.body() != null){
                        val data = response.body()
                        view?.getdata(data?.data as List<DataItem>)
                    } else {
                        view?.showFailureMessage(response.message())
                    }
                }

            })
    }
}