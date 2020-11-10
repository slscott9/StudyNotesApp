package com.example.studynotesapp.network.dto.responses

import com.example.studynotesapp.data.entities.Set
import com.example.studynotesapp.data.entities.Term


data class SetResponse(
        val id: Long,
        val setName: String,
        val userEmail: String,
        val termCount : Int,
        val isSynced: Boolean,
        val folderId: Long?,
        val userName: String,
        val terms: List<TermResponse>
)


data class TermResponse(
        val id: Long,
        val parentSetId: Long?,
        val question: String,
        val answer: String,
        val userEmail: String,
        val isSynced: Boolean ///may need variable for the set id
)

fun SetResponse.asDatabaseSet() : Set {
    return Set(
            setId = id,
            folderId = folderId,
            setName = setName,
            userEmail = userEmail,
            termCount = termCount,
            isSynced = isSynced,
            userName = userName
    )
}

fun List<TermResponse>.asDatabaseTermsList(parentSetId: Long) : List<Term> {
    return map {
        Term(
                termId = it.id,
                setId = parentSetId,
                question = it.question,
                answer = it.answer,
                userEmail = it.userEmail,
                isSynced = it.isSynced
        )
    }
}