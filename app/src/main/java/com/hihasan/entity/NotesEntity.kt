package com.hihasan.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hihasan.constant.DatabaseConstant
import com.hihasan.core.data.NoteDataClass

@Entity(tableName = "tbl_note")
data class NotesEntity(
    val title: String,
    val content: String,

    @ColumnInfo(name = "creation_date")
    val creationTime: Long,

    @ColumnInfo(name = "update_time")
    val updateTime: Long,

    @PrimaryKey(autoGenerate = true)
    val id : Long = 0L
){
    companion object{
        fun fromNote(note: NoteDataClass) = NotesEntity(note.title, note.content, note.creationTime, note.updateTime)
    }

    fun toNote() = NoteDataClass(title, content, creationTime, updateTime, id)
}
