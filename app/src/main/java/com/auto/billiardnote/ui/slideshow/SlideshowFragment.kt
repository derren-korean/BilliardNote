package com.auto.billiardnote.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.auto.billiardnote.databinding.FragmentSlideshowBinding

class SlideshowFragment : Fragment() {
    private var binding: FragmentSlideshowBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val slideshowViewModel: SlideshowViewModel =
            ViewModelProvider(this).get<SlideshowViewModel>(
                SlideshowViewModel::class.java
            )
        binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding!!.root
        val textView: TextView = binding!!.textSlideshow
        slideshowViewModel.text.observe(viewLifecycleOwner) { text: CharSequence? ->
            textView.setText(
                text
            )
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}