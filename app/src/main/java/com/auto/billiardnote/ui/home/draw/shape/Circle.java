package com.auto.billiardnote.ui.home.draw.shape;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Circle {
    private float x;
    private float y;
    private final float r;
    public Circle(float x, float y, float r)
    {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public void setCoordinate(float x, float y) {
        this.x = x;
        this.y = y;
    }

    protected boolean isWithin(float x, float y) {
        return (this.x-r) <= x && x <= (this.x+r) && (this.y-r) <= y && y <= (this.y+r);
    }

    public void draw(Canvas canvas, Paint paint) {
        canvas.drawCircle(x, y, r, paint);
    }

    protected float abs(float n, boolean forX) {
        return forX ? Math.abs(n - this.x) : Math.abs(n - this.y);
    }
}
