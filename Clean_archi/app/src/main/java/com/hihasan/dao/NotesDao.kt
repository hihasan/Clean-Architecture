package com.hihasan.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.hihasan.entity.NotesEntity

@Dao
interface NotesDao {
    @Insert(onConflict = REPLACE)
    suspend fun addNoteEntity(noteEntity: NotesEntity)

    @Query("SELECT * FROM tbl_note WHERE id = :id")
    suspend fun getNoteEntity(id: Long): NotesEntity?

    @Query("SELECT * FROM tbl_note")
    suspend fun getAllNoteEntities(): List<NotesEntity>

    @Delete
    suspend fun deleteNoteEntity(noteEntity: NotesEntity)
}