package com.auto.billiardnote.ui.home.draw;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.auto.billiardnote.ui.home.draw.shape.Circle;

public class Ball extends Circle {

    private final Paint paint;
    private final int color;

    public Ball (float x, float y, float r, DrawingTool tool)
    {
        super(x, y, r);
        paint = DrawingTool.getPaint(tool);
        this.color = paint.getColor();
    }

    public int getColor() { return color; }

    public void setCoordinate(float x, float y) {
        super.setCoordinate(x, y);
    }

    public boolean touch_move(float x, float y) {
        float dx = super.abs(x, true);
        float dy = super.abs(x, false);
        float TOLERANCE = 4;
        if (dx >= TOLERANCE || dy >= TOLERANCE) {
            super.setCoordinate(Math.round(x), Math.round(y));
            return true;
        }
        return false;
    }

    public float getRadius() {
        return super.getRadius();
    }

    public void draw(Canvas canvas) {
        super.draw(canvas, paint);
    }

    public void setClickListener() {
    }

    public boolean isWithin(float x, float y) {
        return super.isWithin(x, y);
    }
}
