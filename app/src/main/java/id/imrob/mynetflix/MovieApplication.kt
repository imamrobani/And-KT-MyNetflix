package id.imrob.mynetflix

import android.app.Application
import id.imrob.mynetflix.data.AppMovieContainer
import id.imrob.mynetflix.data.DefaultAppMovieContainer

class MovieApplication: Application() {

    lateinit var appMovieContainer: AppMovieContainer
    override fun onCreate() {
        super.onCreate()
        appMovieContainer = DefaultAppMovieContainer()
    }
}