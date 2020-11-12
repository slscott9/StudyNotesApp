package com.example.studynotesapp.data.entities

import androidx.room.*

@Entity(tableName = "set_table", foreignKeys = arrayOf(
    ForeignKey(entity = Folder::class,
    parentColumns = arrayOf("folderId"),
    childColumns = arrayOf("folderId"),
    onDelete = ForeignKey.SET_NULL
)
))
data class Set(
    @PrimaryKey(autoGenerate = true)
    val setId: Long = 0,
    var folderId: Long?,
    val setName : String,
    val userEmail: String,
    val termCount: Int,
    val isSynced: Boolean,
    val userName: String

)

data class SetWithTerms(
    @Embedded val set: Set,
    @Relation(
        parentColumn = "setId",
        entityColumn = "setId"
    )
    val termList: List<Term>
)
