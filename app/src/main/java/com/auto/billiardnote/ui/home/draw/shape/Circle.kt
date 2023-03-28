package com.auto.billiardnote.ui.home.draw.shape

import android.graphics.Canvas
import android.graphics.Paint

class Circle {
    private var x: Float
    private var y: Float
    private val r: Float

    constructor(circle: Circle) {
        x = circle.x
        y = circle.y
        r = circle.r
    }

    constructor(x: Float, y: Float, r: Float) {
        this.x = x
        this.y = y
        this.r = r
    }

    fun setCoordinate(x: Float, y: Float) {
        this.x = x
        this.y = y
    }

    fun isWithin(x: Float, y: Float): Boolean {
        return this.x - r <= x && x <= this.x + r && this.y - r <= y && y <= this.y + r
    }

    fun draw(canvas: Canvas, paint: Paint?) {
        canvas.drawCircle(x, y, r, paint!!)
    }

    fun abs(n: Float, forX: Boolean): Float {
        return if (forX) Math.abs(n - x) else Math.abs(n - y)
    }
}