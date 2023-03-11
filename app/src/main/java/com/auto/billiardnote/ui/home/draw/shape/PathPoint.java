package com.auto.billiardnote.ui.home.draw.shape;

import android.graphics.Canvas;
import android.graphics.Paint;

public class PathPoint {
    public PathPoint(float startX, float startY, float stopX, float stopY) {
        this.startX = startX;
        this.startY = startY;
        this.stopX = stopX;
        this.stopY = stopY;
    }

    private float startX, startY, stopX, stopY;

    public void setPoint(float startX, float startY, float stopX, float stopY) {
        this.startX = startX < 0.0f ? this.startX : startX;
        this.startY = startY < 0.0f ? this.startY : startY;
        this.stopX = stopX < 0.0f ? this.stopX : stopX;
        this.stopY = stopY < 0.0f ? this.stopY : stopY;
    }

    public float getStartX() {
        return startX;
    }

    public float getStartY() {
        return startY;
    }

    public float getStopX() {
        return stopX;
    }

    public float getStopY() {
        return stopY;
    }
}
