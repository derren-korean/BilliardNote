package com.auto.billiardnote.ui.home.draw.shape;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

public class PathPoint {

    protected final float IGNORE = -1.0f;
    private float startX, startY, stopX, stopY;
    private Path path;

    public PathPoint() {
        startX = startY = stopX = stopY = IGNORE;
        path = new Path();
    }

    public PathPoint(PathPoint pathPoint) {
        this.startX = pathPoint.startX;
        this.startY = pathPoint.startY;
        this.stopX = pathPoint.stopX;
        this.stopY = pathPoint.stopY;
        path = pathPoint.path;
    }

    public void setPoint(float startX, float startY, float stopX, float stopY) {
        this.startX = startX == IGNORE ? this.startX : startX;
        this.startY = startY == IGNORE ? this.startY : startY;
        this.stopX = stopX == IGNORE ? this.stopX : stopX;
        this.stopY = stopY == IGNORE ? this.stopY : stopY;
    }

    public boolean isEmpty() {return stopX == IGNORE  && stopY == IGNORE;}

    public void touch_start(float x, float y) {
        path.reset();
        if(this.isEmpty()) {
            setPoint(IGNORE, IGNORE, x, y);
        } else {
            x = this.stopX;
            y = this.stopY;
        }
        path.moveTo(x, y);
        setPoint(x, y, IGNORE, IGNORE);
    }


    public void touch_move(float x, float y) {
        float dx = Math.abs(x - this.startX);
        float dy = Math.abs(y - this.startY);
        float TOLERANCE = 4;
        if (dx >= TOLERANCE || dy >= TOLERANCE) {
            setPoint(IGNORE, IGNORE, x, y);
        }
    }

    public void touch_up(float x, float y) {
        path.lineTo(x, y);
        setPoint(IGNORE, IGNORE, x, y);
        path = new Path();
    }

    public void draw(Canvas canvas, Paint paint) {
        canvas.drawLine(startX, startY, stopX, stopY, paint);
    }

}
