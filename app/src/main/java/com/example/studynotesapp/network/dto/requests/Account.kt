package com.example.studynotesapp.network.dto.requests

data class AccountRequest(
        val userName: String,
        val email : String,
        val password: String
)