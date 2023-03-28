package com.auto.billiardnote.ui.home.draw

import android.graphics.Canvas
import android.graphics.Paint
import com.auto.billiardnote.ui.home.draw.shape.PathPoint

class StraightLine {
    val pathHistory: ArrayList<PathPoint>
    private val paint: Paint
    private var pathPoint: PathPoint

    constructor(line: StraightLine) {
        pathHistory = line.pathHistory
        paint = line.paint
        pathPoint = line.pathPoint
    }

    constructor() {
        pathPoint = PathPoint()
        pathHistory = ArrayList()
        paint = DrawingTool.Companion.getPaint(DrawingTool.LINE)
    }

    fun touch_start(x: Float, y: Float) {
        pathPoint.touch_start(x, y)
    }

    fun touch_move(x: Float, y: Float) {
        pathPoint.touch_move(x, y)
    }

    fun touch_up(x: Float, y: Float) {
        pathPoint.touch_up(x, y)
        pathHistory.add(PathPoint(pathPoint))
    }

    fun draw(canvas: Canvas) {
        pathPoint.draw(canvas, paint)
        for (_pathPoint in pathHistory) {
            _pathPoint.draw(canvas, paint)
        }
    }

    fun unDo(): Boolean {
        if (pathHistory.size > 0) {
            pathHistory.removeAt(pathHistory.size - 1)
            if (pathHistory.size == 0) {
                pathPoint = PathPoint()
                return true
            }
            pathPoint = pathHistory[pathHistory.size - 1]
            return true
        }
        return false
    }
}