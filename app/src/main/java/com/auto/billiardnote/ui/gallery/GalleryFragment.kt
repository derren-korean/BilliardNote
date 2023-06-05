package com.auto.billiardnote.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.auto.billiardnote.databinding.FragmentGalleryBinding
import com.auto.billiardnote.fao.Note
import com.auto.billiardnote.fao.NoteRepository

class GalleryFragment : Fragment() {
    private var binding: FragmentGalleryBinding? = null
    val repo: NoteRepository by lazy {
        NoteRepository(this.requireContext())
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val galleryViewModel: GalleryViewModel = ViewModelProvider(this).get<GalleryViewModel>(
            GalleryViewModel::class.java
        )
        binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding!!.root
//        val textView: TextView = binding!!.textGallery
//        galleryViewModel.text.observe(viewLifecycleOwner) { text: CharSequence? ->
//            textView.setText(
//                text
//            )
//        }
        var list: List<Note> = repo.getNoteAll().toList()
        for (note in list) {
            System.out.println(note)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}