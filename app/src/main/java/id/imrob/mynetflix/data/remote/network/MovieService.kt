package id.imrob.mynetflix.data.remote.network

import id.imrob.mynetflix.data.remote.response.ListMovieResponse
import retrofit2.http.GET
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

}