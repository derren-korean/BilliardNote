package com.auto.billiardnote.fao

import android.content.Context
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.flow.Flow

class NoteRepository(context: Context) {

    var db: NoteDao = AppDatabase.getInstance(context).noteDao()

//    fun getAllNotes(): Flow<List<Note>> {
//        return db.getAll()
//    }

    fun getNote(id: Int): Flowable<Note>     {
        return db.loadById(id)
    }

    fun getNoteAll(): List<Note> {
        return db.getNoteAll()
    }

    fun insertNote(note: Note) {
        return db.insert(note)
    }

    fun updateNote(note: Note): Completable {
        return db.update(note)
    }

    fun delete(note: Note) {
        db.delete(note)
    }
}