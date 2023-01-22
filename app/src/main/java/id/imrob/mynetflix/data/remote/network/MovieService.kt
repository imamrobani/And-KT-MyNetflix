package id.imrob.mynetflix.data.remote.network

import id.imrob.mynetflix.data.remote.request.LoginRequest
import id.imrob.mynetflix.data.remote.request.RegisterRequest
import id.imrob.mynetflix.data.remote.response.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    // https://api.themoviedb.org/3/ --> Base URL
    // movie/now_playing?api_key=0a597bad68c0b95d5fab612cff9d8891&language=en-US&page=1

    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("api_key") apiKey: String = "0a597bad68c0b95d5fab612cff9d8891",
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): ListMovieResponse

    @GET("movie/{movieId}")
    suspend fun getMovieDetail(
        @Path("movieId") movieId: String,
        @Query("api_key") apiKey: String = "0a597bad68c0b95d5fab612cff9d8891"
    ): MovieResponse

    @POST("v1/user/register")
    suspend fun register(
        @Body register: RegisterRequest
    ): WebResponse<RegisterReponse>

    @POST("v1/user/login")
    suspend fun login(
        @Body login: LoginRequest
    ): WebResponse<LoginReponse>

}