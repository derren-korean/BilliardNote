package com.auto.billiardnote.ui.home.draw

import android.graphics.Canvas
import android.graphics.Paint
import com.auto.billiardnote.ui.home.draw.shape.Circle
import kotlin.math.roundToInt

class Ball {
    private val paint: Paint?
    val color: Int
    private val circle: Circle

    constructor(ball: Ball) {
        var tmp: Paint? = null
        for (tool in DrawingTool.values()) {
            if (tool.isSame(ball.color)) {
                tmp = DrawingTool.Companion.getPaint(tool)
                break
            }
        }
        circle = Circle(ball.circle)
        paint = tmp
        color = paint!!.color
    }

    constructor(x: Float, y: Float, r: Float, tool: DrawingTool) {
        circle = Circle(x, y, r)
        paint = DrawingTool.Companion.getPaint(tool)
        color = paint.color
    }

    fun touch_move(x: Float, y: Float): Boolean {
        val dx = circle.abs(x, true)
        val dy = circle.abs(x, false)
        val tolerance = 4f
        if (dx >= tolerance || dy >= tolerance) {
            circle.setCoordinate(x.roundToInt().toFloat(), y.roundToInt().toFloat())
            return true
        }
        return false
    }

    fun draw(canvas: Canvas) {
        circle.draw(canvas, paint)
    }

    fun setClickListener() {}
    fun isWithin(x: Float, y: Float): Boolean {
        return circle.isWithin(x, y)
    }
}