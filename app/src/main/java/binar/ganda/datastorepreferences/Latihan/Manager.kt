package binar.ganda.datastorepreferences.Latihan

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class Manager(context : Context) {

    private val dataStore : DataStore<Preferences> = context.createDataStore(name = "user_prefs")

    companion object {
        val NAME = preferencesKey<String>("USER_NAME")
        val PASSWORD = preferencesKey<String>("USER_PASSWORD")
    }

    suspend fun saveData(name : String, password : String){
        dataStore.edit {
            it[NAME] = name
            it[PASSWORD] = password
        }
    }

    val userUsername : Flow<String> = dataStore.data.map {
        it[NAME] ?: ""
    }

    val userPassword : Flow<String> = dataStore.data.map {
        it[PASSWORD] ?: ""
    }

    suspend fun clearData() {
        dataStore.edit {
            it.clear()
        }
    }
}