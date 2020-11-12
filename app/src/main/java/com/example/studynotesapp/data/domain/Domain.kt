package com.example.studynotesapp.data.domain

import com.example.studynotesapp.data.entities.Folder
import com.example.studynotesapp.data.entities.Set

data class DomainFolder(
    val folderId: Long,
    val name: String,
    val userEmail: String,
    val userName: String,
    val description: String?,
    val isSynced: Boolean,
    val setCount: String
)


data class Term(
        val term: String,
        val answer : String
)

fun List<Folder>.asDomainFolderList() : List<DomainFolder> {
    return map {
        DomainFolder(
            folderId = it.folderId,
            name = it.name,
            userEmail = it.userEmail,
            userName = it.userName,
            description = it.description,
            isSynced = it.isSynced,
            setCount = it.setCount
        )
    }
}

fun Folder.asDomainFolder() : DomainFolder {
    return DomainFolder(
        folderId, name, userEmail, userName, description, isSynced, setCount
    )
}


data class DomainSet(
    val setId: Long = 0,
    val folderId: Long?,
    val setName : String,
    val userEmail: String,
    val termCount: Int,
    val isSynced: Boolean,
    val userName: String
)

fun List<DomainSet>.asDatabaseSets(folderId: Long) : List<Set>{
    return map {
        Set(
            setId = it.setId,
            folderId = folderId,
            setName = it.setName,
            userEmail = it.userEmail,
            termCount = it.termCount,
            isSynced = it.isSynced,
            userName = it.userName
        )
    }
}
fun Set.asDomainSet() : DomainSet {

    return DomainSet(
        setId, folderId, setName, userEmail, termCount, isSynced, userName
    )
}

fun List<Set>.asDomainSetList() : List<DomainSet>{
    return map {
        DomainSet(
            setId = it.setId,
            folderId = it.folderId,
            setName = it.setName,
            userName = it.userName,
            termCount = it.termCount,
            isSynced = it.isSynced,
            userEmail = it.userEmail
        )
    }
}