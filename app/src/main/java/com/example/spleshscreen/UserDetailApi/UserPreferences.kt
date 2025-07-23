package com.example.spleshscreen.UserDetailApi

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json

val Context.dataStore by preferencesDataStore("user_pref")

object PreferenceKeys {
    val ACCESS_TOKEN = stringPreferencesKey("access_token")
    val TOKEN_TYPE = stringPreferencesKey("token_type")
    val IS_FIRST_LAUNCH = booleanPreferencesKey("is_first_launch")
    val USER_DETAILS = stringPreferencesKey("user_details")
}

class UserPreferences(context: Context) {
    private val dataStore = context.dataStore

    val accessTokenFlow : Flow<String?>  = dataStore.data.map {
        it[PreferenceKeys.ACCESS_TOKEN]
    }
    val tokenTypeFlow : Flow<String?> = dataStore.data.map {
        it[PreferenceKeys.TOKEN_TYPE]
    }

    val isFirstLaunchFlow : Flow<Boolean> = dataStore.data.map {
        it[PreferenceKeys.IS_FIRST_LAUNCH] ?: true
    }


    suspend fun saveToken(accessToken: String, tokenType: String){
        dataStore.edit {
            it[PreferenceKeys.ACCESS_TOKEN] = accessToken
            it[PreferenceKeys.TOKEN_TYPE] = tokenType
        }
    }

    suspend fun getToken(): String {
        return dataStore.data.map {
            it[PreferenceKeys.ACCESS_TOKEN] ?: ""
        }.first()
    }

    suspend fun clearToken(){
        dataStore.edit {
            it.remove(PreferenceKeys.ACCESS_TOKEN)
            it.remove(PreferenceKeys.TOKEN_TYPE)
        }
    }

    suspend fun setFirstLaunch(){
        dataStore.edit {
            it[PreferenceKeys.IS_FIRST_LAUNCH] = false
        }
    }
    suspend fun saveUserDetails(userDetails: UserDetails) {
        val jsonString = jsonFormat.encodeToString(UserDetails.serializer(), userDetails)
        Log.d("SaveUserDetails_JSON", jsonString)
        dataStore.edit {
            it[PreferenceKeys.USER_DETAILS] = jsonString
        }
    }

    suspend fun getUserDetails(): UserDetails? {
        val json = dataStore.data.map {
            it[PreferenceKeys.USER_DETAILS] ?: ""
        }.first()

        return if (json.isNotBlank()) {
            jsonFormat.decodeFromString(UserDetails.serializer(), json)
        } else null
    }

    suspend fun clearUserDetails(){
        dataStore.edit {
            it.remove(PreferenceKeys.USER_DETAILS)
        }
    }
}