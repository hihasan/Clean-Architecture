package com.hihasan.core.usecase

import com.hihasan.core.data.NoteDataClass
import com.hihasan.core.repository.NoteRepository

class AddNote(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(noteDataClass: NoteDataClass) = noteRepository.addNote(noteDataClass)
}