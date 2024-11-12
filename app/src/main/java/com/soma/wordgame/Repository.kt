package com.soma.wordgame

import android.content.Context
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


val Context.dataStore by preferencesDataStore(
    name = "level_datastore"
)

object PreferencesKeys {
    val level_key = intPreferencesKey("level_key")


}
class Repository(
    private val context: Context
) {

    suspend fun saveLevel(level:Int){
        context.dataStore.edit {pref->
            pref[PreferencesKeys.level_key] = level

        }
    }

     fun readLevel():Flow<Int>{
       return  context.dataStore.data.map {pref->
            val level = pref[PreferencesKeys.level_key]?:-1
            level
        }

    }
}