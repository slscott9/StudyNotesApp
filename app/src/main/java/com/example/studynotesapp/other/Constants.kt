package com.example.studynotesapp.other

object Constants {

    val IGNORE_AUTH_URLS = listOf<String>("/login", "/register")

    const val DATABASE_NAME = "study_notes_database"



//    const val BASE_URL = "http://studynotes6-env.eba-bdssvaau.us-east-2.elasticbeanstalk.com"

        const val BASE_URL = "https://192.168.1.2:5000"


//    const val BASE_URL = "https://172.20.10.2:8082"

    const val ENCRYPTED_SHARED_PREF_NAME = "study_notes_encr_share_pref"

    const val USER_NAME = "USER_NAME"
    const val NO_USERNAME = "NO_USER_NAME"


    const val KEY_LOGGED_IN_USERNAME = "KEY_LOGGED_IN_USERNAME"
    const val KEY_LOGGED_IN_EMAIL = "KEY_LOGGED_IN_EMAIL"
    const val KEY_PASSWORD = "KEY_PASSWORD"
    const val NO_EMAIL = "NO_EMAIL"
    const val NO_PASSWORD = "NO_PASSWORD"

}