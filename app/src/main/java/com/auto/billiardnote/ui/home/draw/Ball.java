package com.auto.billiardnote.ui.home.draw;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.auto.billiardnote.ui.home.draw.shape.Circle;

public class Ball {

    private final Paint paint;
    private final int color;
    private final Circle circle;

    public Ball (Ball ball) {
        this.paint = ball.paint;
        this.color = ball.color;
        this.circle = new Circle(ball.circle);
    }

    public Ball (float x, float y, float r, DrawingTool tool)
    {
        circle = new Circle(x, y, r);
        paint = DrawingTool.getPaint(tool);
        this.color = paint.getColor();
    }

    public int getColor() { return color; }

    public boolean touch_move(float x, float y) {
        float dx = circle.abs(x, true);
        float dy = circle.abs(x, false);
        float TOLERANCE = 4;
        if (dx >= TOLERANCE || dy >= TOLERANCE) {
            circle.setCoordinate(Math.round(x), Math.round(y));
            return true;
        }
        return false;
    }

    public void draw(Canvas canvas) {
        circle.draw(canvas, paint);
    }

    public void setClickListener() {
    }

    public boolean isWithin(float x, float y) {
        return circle.isWithin(x, y);
    }
}
