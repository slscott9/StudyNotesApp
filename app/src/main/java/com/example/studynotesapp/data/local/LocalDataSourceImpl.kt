package com.example.studynotesapp.data.local

import com.example.studynotesapp.data.database.StudyNotesDao
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val dao: StudyNotesDao
): LocalDataSource{
}