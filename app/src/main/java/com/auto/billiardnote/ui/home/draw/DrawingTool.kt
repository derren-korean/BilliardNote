package com.auto.billiardnote.ui.home.draw

import android.graphics.Color
import android.graphics.Paint

enum class DrawingTool(private val color: Int) {
    LINE(Color.BLACK),  // 순서가 중요함.
    CUE_BALL(Color.WHITE), ORANGE_BALL(Color.parseColor("#FFA500")), RED_BALL(Color.RED);

    fun isSame(color: Int): Boolean {
        return this.color == color
    }

    companion object {
        fun getPaint(tool: DrawingTool): Paint {
            val paint = Paint()
            paint.isAntiAlias = true
            paint.color = tool.color
            paint.style = Paint.Style.STROKE
            if (LINE == tool) {
                paint.strokeCap = Paint.Cap.BUTT
                paint.strokeJoin = Paint.Join.BEVEL
                paint.strokeWidth = 4f
            } else {
                paint.style = Paint.Style.FILL
            }
            return paint
        }
    }
}