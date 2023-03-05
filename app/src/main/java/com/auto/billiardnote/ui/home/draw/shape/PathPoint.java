package com.auto.billiardnote.ui.home.draw.shape;

public class PathPoint {
    private float startX, startY, stopX, stopY;

    public PathPoint() {
        startX = startY = stopX = stopY = -1.0f;
    }

    public void setPoint(float startX, float startY, float stopX, float stopY) {
        this.startX = startX;
        this.startY = startY;
        this.stopX = stopX;
        this.stopY = stopY;
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
