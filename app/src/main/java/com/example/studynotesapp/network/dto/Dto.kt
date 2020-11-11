package com.example.studynotesapp.network.dto

/*
    Send server the folder id to add sets to

    send server the

    find all sets that are in itemClickedList

    set each sets folder id

    send them to the network with the folder id

    set the folders sets

    onsuccess insert the sets
 */
data class NetworkFolder(
    val folderName: String,
    val userEmail: String,
    val userName: String,
    val description: String?,
    val isSynced: Boolean,
    val termCount: String,
    val userSets: List<NetworkSet>

)

data class NetworkSet(
    val setId: Long,
    val setName: String,
    val userEmail: String,
    val termCount: Int,
    val isSynced: Boolean,
    val folderId: Long?,
    val userName: String,
    val terms : List<NetworkTerm>
)


data class NetworkTerm(
    val id: Long,
    val parentSetId: Long,
    val question: String,
    val answer: String,
    val userEmail: String,
    val isSynced: Boolean ///may need variable for the set id
)