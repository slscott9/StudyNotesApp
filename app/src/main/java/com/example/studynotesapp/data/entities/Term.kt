package com.example.studynotesapp.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "term_table", foreignKeys = arrayOf(
    ForeignKey(entity = Set::class,
    parentColumns = arrayOf("setId"),
    childColumns = arrayOf("setId"),
    onDelete = ForeignKey.CASCADE

)
))
data class Term(
    @PrimaryKey(autoGenerate = true)
    val termId: Long = 0,
    val setId: Long,
    val question: String,
    val answer: String,
    val userEmail: String,
    val isSynced: Boolean

)