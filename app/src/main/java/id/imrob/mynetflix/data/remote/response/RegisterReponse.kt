package id.imrob.mynetflix.data.remote.response

import com.google.gson.annotations.SerializedName

data class RegisterReponse(

	@field:SerializedName("token_type")
	val tokenType: String,

	@field:SerializedName("user")
	val user: User,

	@field:SerializedName("token")
	val token: String
)
