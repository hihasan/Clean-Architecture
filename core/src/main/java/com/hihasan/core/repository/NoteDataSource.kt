package com.hihasan.core.repository

import com.hihasan.core.data.NoteDataClass

interface NoteDataSource {
    suspend fun add(noteDataClass: NoteDataClass)
    suspend fun get(id : Long)
    suspend fun getAll () : List<NoteDataClass>
    suspend fun remove(noteDataClass: NoteDataClass)
}