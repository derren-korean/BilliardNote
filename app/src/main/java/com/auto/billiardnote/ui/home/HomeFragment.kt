package com.auto.billiardnote.ui.home

import android.graphics.Color
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.auto.billiardnote.R
import com.auto.billiardnote.databinding.FragmentHomeBinding
import com.auto.billiardnote.fao.Note
import com.auto.billiardnote.fao.NoteRepository
import com.auto.billiardnote.ui.home.draw.CanvasView
import com.auto.billiardnote.ui.home.draw.DrawingTool
import com.auto.billiardnote.ui.home.draw.ShapeClickInterface
import com.google.android.material.snackbar.Snackbar
import java.util.*

class HomeFragment : Fragment(), ShapeClickInterface {
    private var binding: FragmentHomeBinding? = null
    private var drawingButtons: ArrayList<DrawingButton>? = null
    private var functionView: ArrayList<View>? = null
    var isEnabled = false
    val repo:NoteRepository by lazy {
        NoteRepository(this.requireContext())
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val homeViewModel = ViewModelProvider(this).get(
            HomeViewModel::class.java
        )
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding!!.root
        val textView: TextView = binding!!.textHome
        val canvasView = binding!!.canvas
        homeViewModel.text.observe(viewLifecycleOwner) { text: CharSequence? ->
            textView.text = text
        }
        _initView(textView, canvasView)
        isEnabled = canvasView.mode
        binding!!.undoLine.setOnClickListener {
            canvasView.unDo()
            _setToolNBGColor(binding!!.line)
        }
        binding!!.fab.setOnClickListener { v: View? ->
            // TODO: load input data if exist
            val noteName = EditText(binding!!.root.context)
            noteName.inputType = InputType.TYPE_CLASS_TEXT

            val builder = AlertDialog.Builder(
                binding!!.root.context
            )
            builder.setView(noteName)
                .setTitle("노트 저장하기")
                .setPositiveButton("확인") { _, _ ->
                    save(noteName.text.toString())
                    // TODO: 저장할 때 타이틀 입력하기 & path serialize에 특수문자 때문에 JSON malformed occurred!
                    Snackbar.make((v)!!, "저장완료!!!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }
                .setNegativeButton(
                    "취소"
                ) { dialog, _ -> dialog.cancel() }
            val dialog = builder.create()
            dialog.show()
        }
        // TODO: data save and load
        binding!!.imageView2.setOnClickListener {
            isEnabled = false

//            binding!!.canvas.load(note)
        }
        binding!!.line.setOnClickListener {
            _setToolNBGColor(
                binding!!.line
            )
        }
        binding!!.cueBall.setOnClickListener {
            _setToolNBGColor(
                binding!!.cueBall
            )
        }
        binding!!.orangeBall.setOnClickListener {
            _setToolNBGColor(
                binding!!.orangeBall
            )
        }
        binding!!.redBall.setOnClickListener {
            _setToolNBGColor(
                binding!!.redBall
            )
        }
        binding!!.changeMode.setOnClickListener { changeTo(!binding!!.canvas.mode) }
        return root
    }

    private fun _initView(textView: TextView, canvasView: CanvasView) {
        canvasView.setClickListener(this)
        functionView = ArrayList(listOf(textView, binding!!.undoLine))
        drawingButtons = ArrayList(
            Arrays.asList(
                binding!!.line,
                binding!!.cueBall,
                binding!!.orangeBall,
                binding!!.redBall
            )
        )
        for (i in drawingButtons!!.indices) {
            drawingButtons!![i].tool = DrawingTool.values()[i]
        }
        changeTo(canvasView.mode)
    }

    private fun _setInitUIDrawingButton(enable: Boolean) {
        if (enable) {
            var button = binding!!.cueBall
            for (_button in drawingButtons!!) {
                if (_button.tool == binding!!.canvas.drawingTool) {
                    button = _button
                    break
                }
            }
            _setOtherToolsBGColor(button)
        }
    }

    private fun _setToolNBGColor(button: DrawingButton) {
        selectTool(button.tool)
        _setOtherToolsBGColor(button)
    }

    private fun _setOtherToolsBGColor(except: DrawingButton) {
        for (button in drawingButtons!!) {
            button.setBackgroundColor(_getEnableColor(button == except))
        }
    }

    private fun setEnable(enable: Boolean) {
        binding!!.canvas.isEnabled = enable
        for (button in drawingButtons!!) {
            button.changeTo(enable)
        }
        _setInitUIDrawingButton(enable)
        for (view in functionView!!) {
            view.isEnabled = enable
            view.setBackgroundColor(_getEnableColor(enable))
        }
    }

    private fun selectTool(tool: DrawingTool?) {
        binding!!.canvas.drawingTool = tool!!
    }

    private fun changeTo(edit: Boolean) {
        setEnable(binding!!.canvas.changeTo(edit))
        binding!!.changeMode.setBackgroundResource(if (edit) R.drawable.ic_read_mode_foreground else R.drawable.ic_edit_mode_foreground)
        Toast.makeText(this.context, if (edit) "수정 모드" else "읽기 모드", Toast.LENGTH_SHORT).show()
    }

    private fun _getEnableColor(enable: Boolean): Int {
        return if (enable) Color.WHITE else Color.DKGRAY
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onCircleClick() {}
    private fun save(noteName: String) {
        val canvas = binding!!.canvas
        val note = Note(
            0,
            canvas.line,
            canvas.balls,
            binding!!.textHome.text.toString(),
            noteName)
        repo.insertNote(note)
        changeTo(false)
    }
}