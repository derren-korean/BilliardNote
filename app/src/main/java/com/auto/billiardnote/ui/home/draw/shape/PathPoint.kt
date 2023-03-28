package com.auto.billiardnote.ui.home.draw.shape

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path

class PathPoint {
    private var startX: Float
    private var startY: Float
    private var stopX: Float
    private var stopY: Float
    private var path: Path

    constructor() {
        stopY = IGNORE
        stopX = stopY
        startY = stopX
        startX = startY
        path = Path()
    }

    constructor(pathPoint: PathPoint) {
        startX = pathPoint.startX
        startY = pathPoint.startY
        stopX = pathPoint.stopX
        stopY = pathPoint.stopY
        path = pathPoint.path
    }

    fun setPoint(startX: Float, startY: Float, stopX: Float, stopY: Float) {
        this.startX = if (startX == IGNORE) this.startX else startX
        this.startY = if (startY == IGNORE) this.startY else startY
        this.stopX = if (stopX == IGNORE) this.stopX else stopX
        this.stopY = if (stopY == IGNORE) this.stopY else stopY
    }

    val isEmpty: Boolean
        get() = stopX == IGNORE && stopY == IGNORE

    fun touch_start(x: Float, y: Float) {
        var x = x
        var y = y
        path.reset()
        if (isEmpty) {
            setPoint(IGNORE, IGNORE, x, y)
        } else {
            x = stopX
            y = stopY
        }
        path.moveTo(x, y)
        setPoint(x, y, IGNORE, IGNORE)
    }

    fun touch_move(x: Float, y: Float) {
        val dx = Math.abs(x - startX)
        val dy = Math.abs(y - startY)
        val TOLERANCE = 4f
        if (dx >= TOLERANCE || dy >= TOLERANCE) {
            setPoint(IGNORE, IGNORE, x, y)
        }
    }

    fun touch_up(x: Float, y: Float) {
        path.lineTo(x, y)
        setPoint(IGNORE, IGNORE, x, y)
        path = Path()
    }

    fun draw(canvas: Canvas, paint: Paint?) {
        canvas.drawLine(startX, startY, stopX, stopY, paint!!)
    }

    companion object {
        protected const val IGNORE = -1.0f
    }
}