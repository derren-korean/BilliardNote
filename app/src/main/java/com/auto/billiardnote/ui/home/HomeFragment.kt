package com.auto.billiardnote.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.auto.billiardnote.R
import com.auto.billiardnote.databinding.FragmentHomeBinding
import com.auto.billiardnote.fao.FileIO
import com.auto.billiardnote.fao.NoteInfo
import com.auto.billiardnote.ui.home.draw.CanvasView
import com.auto.billiardnote.ui.home.draw.DrawingTool
import com.auto.billiardnote.ui.home.draw.ShapeClickInterface
import com.google.gson.GsonBuilder
import java.util.*

class HomeFragment : Fragment(), ShapeClickInterface {
    private var binding: FragmentHomeBinding? = null
    private var drawingButtons: ArrayList<DrawingButton>? = null
    private var functionView: ArrayList<View>? = null
    var isEnabled = false
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
        isEnabled = canvasView.status
        binding!!.undoLine.setOnClickListener {
            canvasView.unDo()
            _setToolNBGColor(binding!!.line)
        }
        // TODO: data save and load
        binding!!.imageView2.setOnClickListener {
            isEnabled = false
            FileIO.read()
            val note = GsonBuilder().create().fromJson(FileIO.myData, NoteInfo::class.java)
            binding!!.canvas.load(note)
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
        binding!!.changeMode.setOnClickListener { modeChange(!binding!!.canvas.status) }
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
        modeChange(canvasView.status)
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
            button.setModeChange(enable)
        }
        _setInitUIDrawingButton(enable)
        for (view in functionView!!) {
            view.isEnabled = enable
            view.setBackgroundColor(_getEnableColor(enable))
        }
    }

    fun selectTool(tool: DrawingTool?) {
        binding!!.canvas.drawingTool = tool!!
    }

    fun modeChange(enable: Boolean) {
        setEnable(binding!!.canvas.setEnable(enable))
        binding!!.changeMode.setBackgroundResource(if (enable) R.drawable.ic_read_mode_foreground else R.drawable.ic_edit_mode_foreground)
        Toast.makeText(this.context, if (enable) "수정 모드" else "읽기 모드", Toast.LENGTH_SHORT).show()
    }

    private fun _getEnableColor(enable: Boolean): Int {
        return if (enable) Color.WHITE else Color.DKGRAY
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onCircleClick() {}
    fun save(noteName: String?) {
//        NoteInfo note = new NoteInfo(
//                binding.canvas.getLine(),
//                binding.canvas.getBalls(),
//                binding.textHome.getText().toString(),
//                noteName
//        );
//        FileIO.write(note.toString());
        modeChange(false)
    }
}