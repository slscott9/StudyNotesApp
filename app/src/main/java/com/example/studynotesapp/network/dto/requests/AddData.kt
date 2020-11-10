package com.example.studynotesapp.network.dto.requests

import com.example.studynotesapp.data.domain.Term
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

data class AddSet(
        val setName: String,
        val userEmail: String,
        val termCount: Int,
        val isSynced: Boolean,
        val folderId: Long?,
        val userName: String,
        val terms : List<AddTerm>
)

data class AddTerm(
        val question: String,
        val answer: String,
        val userEmail: String,
        val isSynced: Boolean ///may need variable for the set id
)

fun List<Term>.asAddTermList(userEmail: String) : List<AddTerm>{
    return map {
        AddTerm(
                question = it.term,
                answer = it.answer,
                userEmail = userEmail,
                isSynced = false

        )
    }
}



