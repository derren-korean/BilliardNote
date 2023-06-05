package com.auto.billiardnote.fao

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insert(vararg note: Note)

        @Update
        fun update(vararg note: Note): Completable

        @Delete
        fun delete(vararg note: Note)

//        @Query("SELECT * FROM note")
//        fun getAll(): Flow<List<Note>>

        @Query("SELECT * FROM note")
        fun getNoteAll(): List<Note>

        @Query("SELECT * FROM note WHERE id = :id")
        fun loadById(id: Int): Flowable<Note>
}