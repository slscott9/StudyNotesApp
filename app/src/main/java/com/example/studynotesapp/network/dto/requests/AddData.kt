package com.example.studynotesapp.network.dto.requests

import com.example.studynotesapp.data.entities.Folder

data class AddFolder(
    val folderName: String,
    val userEmail: String,
    val userName: String,
    val description: String?,
    val isSynced: Boolean,
    val termCount: String

)

fun Folder.asAddFolderObject() : AddFolder {
    return AddFolder(
        folderName = name,
        userEmail = userEmail,
        userName = userName,
        description = description,
        isSynced = isSynced,
        termCount = termCount
    )
}

