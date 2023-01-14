package com.dayscode.todojpc.data.repositories

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.dayscode.todojpc.data.models.Priority
import com.dayscode.todojpc.util.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import java.util.concurrent.Flow
import javax.inject.Inject

@ViewModelScoped
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = Constants.PREFERENCE_NAME)

class DataStoreRepository @Inject constructor(
    @ApplicationContext private val context: Context
){
    private object PreferenceKey{
        val sortKey = stringPreferencesKey(name = Constants.PREFERENCE_KEY)
    }

    private val dataStore = context.dataStore

    //function to save the value in the datastore
    suspend fun persistSortState(priority: Priority){
        dataStore.edit {  preference->
            preference[PreferenceKey.sortKey] = priority.name

        }
    }

    //variable to read from the datastore
    val readSortState: kotlinx.coroutines.flow.Flow<String> = dataStore.data
        .catch { exception->
            if (exception is IOException){
                emit(emptyPreferences())
            }else{
                throw exception
            }
        }
        .map {preferences->
            //if there is no value store in the data store then just return the none priority
            val sortState = preferences[PreferenceKey.sortKey] ?: Priority.NONE.name
            sortState
        }
}