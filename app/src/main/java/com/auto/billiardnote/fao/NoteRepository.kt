package com.auto.billiardnote.fao

import android.content.Context
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

class NoteRepository(context: Context) {

    var db: NoteDao = AppDatabase.getInstance(context).noteDao()

    fun getAllNotes(): Flowable<List<Note>> {
        return db.getAll()
    }

    fun getNote(id: Int): Flowable<Note>     {
        return db.loadById(id)
    }

    fun insertNote(note: Note): Completable {
        return db.insert(note)
    }

    fun updateNote(note: Note): Completable {
        return db.update(note)
    }

    fun delete(note: Note) {
        db.delete(note)
    }
}