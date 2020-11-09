package com.example.studynotesapp.data.repo

import android.app.Application
import android.content.Context
import com.example.studynotesapp.data.local.LocalDataSource
import com.example.studynotesapp.data.remote.RemoteDataSource
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val context: Application
): Repository {
}