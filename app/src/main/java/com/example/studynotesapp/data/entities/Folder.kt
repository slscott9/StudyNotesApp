package com.example.studynotesapp.data.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "folder_table")
data class Folder(
        @PrimaryKey(autoGenerate = true)
        val folderId: Long = 0,
        val name: String,
        val userEmail: String,
        val userName: String,
        val description: String?,
        val isSynced: Boolean,
        var setCount: String
)

data class FolderwithSets(
        @Embedded val folder: Folder,
        @Relation(
                parentColumn = "folderId",
                entityColumn = "folderId"
        )
        val setList: List<Set>
)