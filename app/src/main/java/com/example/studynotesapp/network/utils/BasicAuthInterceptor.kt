package com.example.studynotesapp.network.utils

import android.content.SharedPreferences
import com.example.studynotesapp.other.Constants.IGNORE_AUTH_URLS
import com.example.studynotesapp.other.Constants.KEY_LOGGED_IN_USERNAME
import com.example.studynotesapp.other.Constants.KEY_PASSWORD
import com.example.studynotesapp.other.Constants.NO_EMAIL
import com.example.studynotesapp.other.Constants.NO_PASSWORD
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response

class BasicAuthInterceptor(sharedPreferences: SharedPreferences) : Interceptor {

    /*
        Can attach interceptors to each http request we make
        Each interceptor modify requests we make
     */


    var userName = sharedPreferences.getString(KEY_LOGGED_IN_USERNAME, NO_EMAIL)
    var password = sharedPreferences.getString(KEY_PASSWORD, NO_PASSWORD)


    /*
        If the http request is one of the IGNORE_AUTH_URLS then we dont authenticate that request
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        if(request.url.encodedPath in IGNORE_AUTH_URLS){
            return chain.proceed(request)
        }
        //if request was not one of the IGNORE we need to add auth header to request
        //Authorization means are you allowed to make that request
        //Authentication means making sure you are  actually you
        //If peter makes request he must be Authorized and Athenticated

        val authenticatedRequest = request.newBuilder()
            .header("Authorization", Credentials.basic(userName ?: "", password ?: ""))
            .build()
        return chain.proceed(authenticatedRequest)
    }
}