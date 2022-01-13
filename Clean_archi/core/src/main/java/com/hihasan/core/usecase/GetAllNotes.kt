package com.hihasan.core.usecase

import com.hihasan.core.repository.NoteRepository

class GetAllNotes(private val noteRepository: NoteRepository) {
    suspend operator fun invoke() = noteRepository.getAllNotes()
}