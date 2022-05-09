package binar.ganda.datastorepreferences

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.Flow

class UserManager(context : Context) {

    private val dataStore : DataStore<Preferences> = context.createDataStore(name = "user_prefs")

    companion object {
        val NAME = preferencesKey<String>("USER_NAME")
        val AGE = preferencesKey<Int>("USER_AGE")
    }

    suspend fun saveData(name : String, age : Int){
        dataStore.edit {
            it[NAME] = name
            it[AGE] = age
        }
    }

    val userNama : Flow<String> = dataStore.data.map {
        it[NAME] ?: ""
    }

    val userAge : Flow<Int> = dataStore.data.map {
        it[AGE] ?: 0
    }

    suspend fun clearData() {
        dataStore.edit {
            it.clear()
        }
    }


}