package id.imrob.mynetflix.data

import android.content.Context
import id.imrob.mynetflix.data.local.LocalDataSource
import id.imrob.mynetflix.data.local.datastore.MovieDataStore
import id.imrob.mynetflix.data.local.room.MovieDatabase
import id.imrob.mynetflix.data.remote.RemoteDataSource
import id.imrob.mynetflix.data.remote.network.MovieService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppMovieContainer {
    val remoteDataSource: RemoteDataSource
    val movieRepository: MovieRepository
    val localDatasource: LocalDataSource
    val authRepository: AuthRepository
}

class DefaultAppMovieContainer(
    private val context: Context
): AppMovieContainer{
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

    private val movieService: MovieService by lazy { retrofit.create(MovieService::class.java) }

    private val movieDataBase: MovieDatabase by lazy {
        MovieDatabase.getInstance(context)
    }

    private val dataStore: MovieDataStore by lazy {
        MovieDataStore(context)
    }

    override val remoteDataSource: RemoteDataSource by lazy { RemoteDataSource(movieService) }

    override val movieRepository: MovieRepository by lazy { MovieRepository(remoteDataSource)  }

    override val localDatasource: LocalDataSource by lazy { LocalDataSource(movieDataBase.userDao(), dataStore) }

    override val authRepository: AuthRepository by lazy { AuthRepository(localDatasource, remoteDataSource)  }
}