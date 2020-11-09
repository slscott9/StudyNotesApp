package com.example.studynotesapp.data.remote

import com.example.studynotesapp.data.database.StudyNotesDao
import com.example.studynotesapp.network.StudyNotesApi
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val notesApi: StudyNotesApi
) : RemoteDataSource{
}