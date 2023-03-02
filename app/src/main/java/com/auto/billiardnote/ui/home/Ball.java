package com.auto.billiardnote.ui.home;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Ball extends Circle{

    private Paint paint;
    private final float TOLERANCE = 4;
    private ShapeClickInterface clickListener;

    public Ball (float x, float y, float r, DrawingTool tool)
    {
        super(x, y, r);
        paint = DrawingTool.getPaint(tool);
    }

    public float getX() {
        return super.getX();
    }

    public float getY() {
        return super.getY();
    }

    public void setCoordinate(float x, float y) {
        super.setCoordinate(x, y);
    }

    public boolean touch_move(float x, float y) {
        float dx = Math.abs(x - super.getX());
        float dy = Math.abs(y - super.getY());
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
        canvas.drawCircle(super.getX(), super.getY(), super.getRadius(), paint);
    }

    public void setClickListener(ShapeClickInterface listener) {
        this.clickListener = listener;
    }

    public boolean isWithin(float x, float y) {
        return super.isWithin(x, y);
    }
}
