package com.auto.billiardnote.ui.home.draw

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.auto.billiardnote.fao.Note

class CanvasView(context: Context, attrs: AttributeSet?) : View(
    context, attrs
) {
    val line: StraightLine = StraightLine()
//    val balls: HashSet<Ball> = HashSet()
    val balls: Balls = Balls()
    var drawingTool: DrawingTool
    var mode = true // read-only: false, editable: true

    fun setClickListener(listener: ShapeClickInterface?) {}

    init {
        drawingTool = DrawingTool.CUE_BALL
        changeTo(true)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        for (tool in DrawingTool.values()) {
            if (tool != DrawingTool.LINE) {
                createCircle(tool)
            }
        }
        drawingTool = DrawingTool.CUE_BALL
    }

    override fun onDraw(canvas: Canvas) {
        line.draw(canvas)
        for (ball in balls) {
            ball.draw(canvas)
        }
        canvas.save()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (!mode) {
            return false
        }
        var x = event.x
        var y = event.y
        if (x < 0f) {
            x = 0f
        } else if (x > width) {
            x = width.toFloat()
        }
        if (y < 0f) {
            y = 0f
        } else if (y > height) {
            y = height.toFloat()
        }
        if (drawingTool == DrawingTool.LINE) {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    line.touch_start(x, y)
                    invalidate()
                }
                MotionEvent.ACTION_MOVE -> {
                    line.touch_move(x, y)
                    invalidate()
                }
                MotionEvent.ACTION_UP -> {
                    line.touch_up(x, y)
                    invalidate()
                }
            }
        } else {
            when (event.action) {
                MotionEvent.ACTION_MOVE -> {
                    for (ball in balls) {
                        if (drawingTool.isSame(ball.color)) {
                            ball.touch_move(x, y)
                            break
                        }
                    }
                    invalidate()
                }
                MotionEvent.ACTION_DOWN, MotionEvent.ACTION_UP -> {}
            }
        }
        return true
    }

    //    private DrawingTool _getBallTool(float x, float y) {
    //        DrawingTool tool = this.drawingTool;
    //        balls.containsValue(balls.values().stream()
    //                .filter(ball -> ball.isWithin(x, y))
    //                .findFirst()
    //                .get());
    //        for (Map.Entry<DrawingTool, Ball> entry : balls.entrySet()) {
    //            if (entry.getValue().isWithin(x, y)) {
    //                tool = entry.getKey();
    //                break;
    //            }
    //        }
    //        return tool;
    //    }
    //    private boolean _isBall(float x, float y) {
    //        return balls.values().stream()
    //                .anyMatch(ball -> ball.isWithin(x, y));
    //    }
    private fun createCircle(tool: DrawingTool) {
        drawingTool = tool
        val ball = Ball(width / 2f, height / 2f, 40f, tool)
        ball.setClickListener()
        balls.add(ball)
        this.invalidate()
    }

    fun unDo() {
        if (line.unDo()) {
            invalidate()
        }
    }

    fun changeTo(edit: Boolean): Boolean {
        this.mode = edit
        return this.mode
    }

    fun load(note: Note) {
//        this.line = StraightLine(info.straightLine)
//        this.line = new StraightLine(info.getStraightLine());
//        this.balls = new HashSet<>();
//        for (Ball ball : info.getBalls()) {
//            this.balls.add(new Ball(ball));
//        }
//        invalidate();
    }
}