package com.auto.billiardnote.fao

import android.content.Context

class NoteRepository(context: Context) {

    var db: NoteDao = AppDatabase.getInstance(context)?.noteDao()!!

    suspend fun getAllNotes(): List<NoteInfo> {
        return db.getAll();
    }

    suspend fun getNote(id: Int) {
        db.loadUserById(id)
    }

    suspend fun insertNote(note: NoteInfo) {
        db.insertNote(note)
    }

    suspend fun updateUser(note: NoteInfo) {
        db.updateNote(note)
    }

    suspend fun deleteUser(note: NoteInfo) {
        db.deleteNote(note)
    }
}