package com.auto.billiardnote.ui.home;

import android.graphics.Paint;

public class Circle {
    private float x, y, r;
    public Circle(float x, float y, float r)
    {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setCoordinate(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getRadius() {
        return r;
    }

    protected boolean isWithin(float x, float y) {
        return (this.x-r) <= x && x <= (this.x+r) && (this.y-r) <= y && y <= (this.y+r);
    }
}
