package com.example.studynotesapp.network.dto.responses

import com.example.studynotesapp.data.entities.Folder

data class FolderResponse(
    val id: Long,
    val folderName: String,
    val userEmail: String,
    val userName: String,
    val description: String?,
    val isSynced: Boolean,
    val setCount: String
)

fun FolderResponse.asDatabaseModel() : Folder {
    return Folder(
        folderId = id,
        name = folderName,
        userEmail = userEmail,
        userName = userName,
        description = description,
        isSynced = isSynced,
        setCount = setCount
    )
}