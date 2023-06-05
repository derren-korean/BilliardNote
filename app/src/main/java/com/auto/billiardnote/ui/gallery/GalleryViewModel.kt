package com.auto.billiardnote.ui.gallery

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.auto.billiardnote.fao.AppDatabase
import com.auto.billiardnote.fao.Note
import com.auto.billiardnote.fao.NoteRepository

class GalleryViewModel() : ViewModel() {
    private val mText: MutableLiveData<String?>

    init {
        mText = MutableLiveData()
        mText.value = "This is gallery fragment"
    }

    val text: LiveData<String?>
        get() = mText
    val noteList: LiveData<List<Note>>
        get() = noteList
}