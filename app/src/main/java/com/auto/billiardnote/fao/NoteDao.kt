package com.auto.billiardnote.fao

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao
interface NoteDao {
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insert(vararg note: Note): Completable

        @Update
        fun update(vararg note: Note): Completable

        @Delete
        fun delete(vararg note: Note)

        @Query("SELECT * FROM note")
        fun getAll(): Flowable<List<Note>>

        @Query("SELECT * FROM note WHERE id = :id")
        fun loadById(id: Int): Flowable<Note>
}