package com.auto.billiardnote.ui.home.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.HashSet;

public class CanvasView extends View {

    Context context;
    private final StraightLine line;
    private final HashSet<Ball> balls;
    private DrawingTool drawingTool;
    public boolean enabled = true; // read-only: false, editable: true

    public void setClickListener(ShapeClickInterface listener) {
    }

    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        balls = new HashSet<>();
        line = new StraightLine();
        drawingTool = DrawingTool.CUE_BALL;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        for (DrawingTool tool : DrawingTool.values()) {
            if(tool != DrawingTool.LINE) {
                createCircle(tool);
            }
        }
        this.setDrawingTool(DrawingTool.CUE_BALL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        line.draw(canvas);
        for (Ball ball : balls) {
            ball.draw(canvas);
        }
        canvas.save();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(!enabled) {
            return false;
        }
        float x = event.getX();
        float y = event.getY();
        if (x < 0f) {
            x = 0f;
        } else if (x > getWidth()) {
            x = getWidth();
        }
        if (y < 0f) {
            y = 0f;
        } else if (y > getHeight()) {
            y = getHeight();
        }

        if (this.drawingTool == DrawingTool.LINE) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    line.touch_start(x, y);
                    invalidate();
                    break;
                case MotionEvent.ACTION_MOVE:
                    line.touch_move(x, y);
                    invalidate();
                    break;
                case MotionEvent.ACTION_UP:
                    line.touch_up(x, y);
                    invalidate();
                    break;
            }
        } else {
            switch (event.getAction()) {
                case MotionEvent.ACTION_MOVE:
                    for (Ball ball : balls) {
                        if(this.drawingTool.isSame(ball.getColor())) {
                            ball.touch_move(x, y);
                            break;
                        }
                    }
                    invalidate();
                    break;
                case MotionEvent.ACTION_DOWN: case MotionEvent.ACTION_UP:
                    break;
            }
        }
        return true;
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

    public void createCircle(DrawingTool tool) {
        this.drawingTool = tool;
        Ball ball = new Ball( getWidth() / 2f, getHeight() / 2f, 40f, tool);
        ball.setClickListener();
        balls.add(ball);
        this.invalidate();
    }

    public void unDo() {
        if (line.unDo()) {
            invalidate();
        }
    }

    public boolean setEnable(boolean enable) {
        this.enabled = enable;
        return this.enabled;
    }

    public void setDrawingTool(DrawingTool tool) {
        this.drawingTool = tool;
    }

    public DrawingTool getDrawingTool() {
        return this.drawingTool;
    }
}
