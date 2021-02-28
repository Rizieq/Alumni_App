package com.project.alumniapp.model

import com.google.gson.annotations.SerializedName

data class ResponseUserEdit(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("cv")
	val cv: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("bio")
	val bio: String? = null,

	@field:SerializedName("no_handphone")
	val noHandphone: String? = null,

	@field:SerializedName("tahun_alumni")
	val tahunAlumni: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)
