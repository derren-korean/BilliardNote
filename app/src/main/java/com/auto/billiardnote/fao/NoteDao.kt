package com.auto.billiardnote.fao

import androidx.room.*

@Dao
interface NoteDao {
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertNote(vararg note: NoteInfo)

        @Update
        suspend fun updateNote(vararg note: NoteInfo)

        @Delete
        suspend fun deleteNote(vararg note: NoteInfo)

        @Query("SELECT * FROM note")
        suspend fun getAll(): List<NoteInfo>

        @Query("SELECT * FROM note WHERE id = :id")
        suspend fun loadUserById(id: Int): NoteInfo

        @Query("SELECT * from note WHERE region IN (:regions)")
        suspend fun loadUsersByRegion(regions: List<String>): List<NoteInfo>

}