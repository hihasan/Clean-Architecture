package com.hihasan.core.repository

import com.hihasan.core.data.NoteDataClass

class NoteRepository(private val dataSource: NoteDataSource) {
    suspend fun addNote(noteDataClass: NoteDataClass) = dataSource.add(noteDataClass)
    suspend fun  getNote (id : Long) = dataSource.get(id)
    suspend fun getAllNotes () = dataSource.getAll()
    suspend fun removeNotes(noteDataClass: NoteDataClass) = dataSource.remove(noteDataClass)
}