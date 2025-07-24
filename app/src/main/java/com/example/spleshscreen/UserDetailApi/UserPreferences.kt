package com.example.spleshscreen.UserDetailApi

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.spleshscreen.UserDetailApi.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json
import kotlin.text.set

val Context.dataStore by preferencesDataStore("user_pref")

object PreferenceKeys {
    val ACCESS_TOKEN = stringPreferencesKey("access_token")
    val TOKEN_TYPE = stringPreferencesKey("token_type")
    val IS_FIRST_LAUNCH = booleanPreferencesKey("is_first_launch")
    val USER_DETAILS = stringPreferencesKey("user_details")
}

class UserPreferences(context: Context) {
    private val dataStore = context.dataStore

    private val jsonFormat = Json {
        ignoreUnknownKeys = true
        prettyPrint = true
        isLenient = true
    }

    val accessTokenFlow : Flow<String?>  = dataStore.data.map {
        it[PreferenceKeys.ACCESS_TOKEN]
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
        try {
            Log.d("SaveUserDetails", "Called with user: ${userDetails.firstname} (ID: ${userDetails.user_id})")

            val jsonString = jsonFormat.encodeToString(UserDetails.serializer(), userDetails)
            Log.d("SaveUserDetails_JSON", jsonString)

            dataStore.edit {
                it[PreferenceKeys.USER_DETAILS] = jsonString
            }

        } catch (e: Exception) {
            Log.e("SaveUserDetails", "Error saving user details", e)
        }
    }
    suspend fun getUserDetails(): UserDetails? {

        return try {
            val json = dataStore.data.map {
                it[PreferenceKeys.USER_DETAILS] ?: ""
            }.first()

            if (json.isNotEmpty()) {
                jsonFormat.decodeFromString(UserDetails.serializer(), json).also {
                    Log.d("UserPreferences","Loaded: ${it.firstname}")
                }
            }else { null }
        }catch (e: Exception){
            Log.e("UserPreferences" , "Error loading user detail" , e)
            null
        }
    }

    suspend fun clearUserDetails(){
        dataStore.edit {
            it.remove(PreferenceKeys.USER_DETAILS)
        }
    }
}