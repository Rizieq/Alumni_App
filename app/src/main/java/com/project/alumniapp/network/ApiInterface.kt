package com.project.alumniapp.network

import com.project.alumniapp.model.ResponseLogin
import com.project.alumniapp.model.ResponseRegister
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiInterface {

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<ResponseLogin>

    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("email") email: String,
        @Field("no_handphone") no_handphone: String,
        @Field("password") password: String,
        @Field("address") address: String,
        @Field("name") name: String
    ): Call<ResponseRegister>
}