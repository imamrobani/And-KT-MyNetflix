package id.imrob.mynetflix.core.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.imrob.mynetflix.core.data.local.room.dao.UserDao
import id.imrob.mynetflix.core.data.local.room.entity.FavoriteMovieEntity
import id.imrob.mynetflix.core.data.local.room.entity.UserEntity

@Database(
    entities = [UserEntity::class, FavoriteMovieEntity::class],
    version = 2,
    exportSchema = true,
)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: MovieDatabase? = null
        fun getInstance(context: Context): MovieDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    val dbBuilder = Room.databaseBuilder(
                        context.applicationContext,
                        MovieDatabase::class.java,
                        "netflix_clone.db"
                    )
                        .fallbackToDestructiveMigration()

                    instance = dbBuilder.build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}