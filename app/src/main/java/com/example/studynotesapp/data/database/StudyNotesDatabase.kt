package com.example.studynotesapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.studynotesapp.data.entities.Folder
import com.example.studynotesapp.data.entities.Set
import com.example.studynotesapp.data.entities.Term
import com.example.studynotesapp.data.entities.User

@Database(entities = [User::class, Folder::class, Set::class, Term::class], version = 13)
abstract class StudyNotesDatabase : RoomDatabase(){

    abstract fun dao() : StudyNotesDao
}