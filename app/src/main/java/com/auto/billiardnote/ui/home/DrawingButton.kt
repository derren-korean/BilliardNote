package com.auto.billiardnote.ui.home

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageButton
import com.auto.billiardnote.ui.home.draw.DrawingTool

class DrawingButton : AppCompatImageButton {
    var tool: DrawingTool? = null

    constructor(context: Context?) : super(context!!) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {}
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!, attrs, defStyleAttr
    ) {
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
//        children.forEach { it.isEnabled = enabled }
    }

    fun setModeChange(enable: Boolean) {
        isEnabled = enable
        val enableColor = Color.WHITE
        val disableColor = Color.DKGRAY
        setBackgroundColor(if (enable) enableColor else disableColor)
    }
}