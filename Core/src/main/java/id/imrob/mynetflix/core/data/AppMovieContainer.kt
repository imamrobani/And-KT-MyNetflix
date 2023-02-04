package id.imrob.mynetflix.core.data

import android.content.Context
import id.imrob.mynetflix.core.data.local.LocalDataSource
import id.imrob.mynetflix.core.data.local.datastore.MovieDataStore
import id.imrob.mynetflix.core.data.local.room.MovieDatabase
import id.imrob.mynetflix.core.data.remote.RemoteDataSource
import id.imrob.mynetflix.core.data.remote.network.MovieService
import id.imrob.mynetflix.core.domain.usecase.AuthUseCase
import id.imrob.mynetflix.core.domain.usecase.MovieUseCase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppMovieContainer {
    val remoteDataSource: RemoteDataSource
    val movieRepository: MovieRepository
    val localDataSource: LocalDataSource
    val authRepository: AuthRepository
    val movieUseCase: MovieUseCase
    val authUseCase: AuthUseCase
}

class DefaultAppMovieContainer(
    private val context: Context
): AppMovieContainer {
    private val BASE_URL = "http://54.236.81.227/api/"

    // print response json
    private val interceptor: OkHttpClient get() {
        val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    }

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(interceptor)
        .build()

    private val retrofitService: MovieService by lazy { retrofit.create(MovieService::class.java) }

    private val movieDatabase: MovieDatabase by lazy {
        MovieDatabase.getInstance(context)
    }

    private val dataStore: MovieDataStore by lazy {
        MovieDataStore(context)
    }

    override val remoteDataSource: RemoteDataSource by lazy { RemoteDataSource(retrofitService) }

    override val movieRepository: MovieRepository by lazy { MovieRepository(remoteDataSource) }

    override val localDataSource: LocalDataSource by lazy { LocalDataSource(movieDatabase.userDao(), dataStore) }

    override val authRepository: AuthRepository by lazy { AuthRepository(localDataSource, remoteDataSource) }

    override val movieUseCase: MovieUseCase by lazy { MovieUseCase(movieRepository) }

    override val authUseCase: AuthUseCase by lazy { AuthUseCase(authRepository) }
}