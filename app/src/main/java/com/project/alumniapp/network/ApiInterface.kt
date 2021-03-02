package com.project.alumniapp.network

import com.project.alumniapp.model.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

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
        @Field("address") address: String,
        @Field("name") name: String,
        @Field("no_handphone") no_handphone: String,
        @Field("password") password: String,
        @Field("email") email: String
    ): Call<ResponseRegister>

    @GET("profile/user")
    fun profile(
        @Header("Authorization") token: String?
    ): Call<ResponseUser>

    @GET("profile/getallprofile")
    fun listAlumni(
        @Header("Authorization") token: String?
    ): Call<ResponseAlumni>

    @Multipart
    @POST("profile/user/update?_method=PUT")
    fun editProfile(
        @Part image: MultipartBody.Part?,
        @Part cv: MultipartBody.Part?,
        @Part("bio") bio: RequestBody?,
        @Part("tahun_alumni") tahun_alumni: RequestBody?,
        @Header("Authorization") token: String?
    ): Call<ResponseUserEdit>





}