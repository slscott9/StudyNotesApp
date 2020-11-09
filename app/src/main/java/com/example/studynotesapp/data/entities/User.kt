package com.example.studynotesapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User (
    @PrimaryKey
    val userEmail: String,
    val displayName: String,
    val password: String
)