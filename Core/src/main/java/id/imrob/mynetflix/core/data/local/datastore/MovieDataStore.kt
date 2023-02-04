package id.imrob.mynetflix.core.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieDataStore constructor(
    private val context: Context
) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "netflix_clone.pb")

    suspend fun <T> storeData(key: Preferences.Key<T>, value: T) {
        context.dataStore.edit { preference ->
            preference[key] = value
        }
    }

    suspend fun clear() {
        context.dataStore.edit { preference ->
            preference.remove(EMAIL)
            preference.remove(TOKEN)

        }
    }

    val email: Flow<String>
        get() = context.dataStore.data.map { preference ->
            preference[EMAIL] ?: ""
        }

    val token: Flow<String>
        get() = context.dataStore.data.map { preference ->
            preference[TOKEN] ?: ""
        }

    companion object {
        val EMAIL = stringPreferencesKey("EMAIL")
        val TOKEN = stringPreferencesKey("TOKEN")
    }
}