package com.auto.billiardnote.ui.home;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import java.util.ArrayList;

public class StraightLine {
    private ArrayList<Path> paths;
    private ArrayList<PathPoint> pathPoints;
    private PathPoint pathPoint;
    private float startX, startY, stopX, stopY;
    private Path path;
    private Paint paint;
    final float TOLERANCE = 4;

    protected StraightLine() {
        this.pathPoint = new PathPoint();
        this.pathPoints = new ArrayList<>();
        this.paint = new Paint();
        this.path = new Path();
        this.paths = new ArrayList<>();
        initPoints();
        initPaint();
    }

    private void initPoints() {
        this.startX = -1.0f;
        this.startY = -1.0f;
        this.stopX = -1.0f;
        this.stopY = -1.0f;
    }

    protected void initPaint() {
        this.paint.setAntiAlias(true);
        this.paint.setColor(Color.BLACK);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeCap(Paint.Cap.BUTT);
        this.paint.setStrokeJoin(Paint.Join.BEVEL);
        this.paint.setStrokeWidth(4f);
    }

    protected void setStartPoint(float x, float y) {
        this.startX = x;
        this.startY = y;
    }

    protected void setStopPoint(float x, float y) {
        this.stopX = x;
        this.stopY = y;
    }

    private boolean _isEmpty() {return this.stopX < 0.0f && this.stopY < 0.0f;}

    private void _saveAndInitPath() {
        this.pathPoint.setPoint(this.startX, this.startY, this.stopX, this.stopY);
        this.pathPoints.add(this.pathPoint);
        this.paths.add(this.path);
        this.path = new Path();
        this.pathPoint = new PathPoint();
    }

    protected boolean unDo() {
        if (this.paths.size() > 0) {
            this.paths.remove(this.paths.size()-1);
            this.pathPoints.remove(this.pathPoints.size()-1);
            if (this.paths.size() == 0) {
                initPoints();
                return true;
            }
            _setLastPoint(this.pathPoints.size()-1);
            return true;
        }
        return false;
    }

    private void _setLastPoint(int index) {
        this.setStopPoint(
                this.pathPoints.get(index).getStopX(),
                this.pathPoints.get(index).getStopY()
        );
        this.setStartPoint(
                this.pathPoints.get(index).getStartX(),
                this.pathPoints.get(index).getStartY()
        );
    }

    protected void touch_start(float x, float y) {
        this.path.reset();
        if (_isEmpty()) {
            this.setStopPoint(x, y);
        } else {
            x = stopX;
            y = stopY;
        }
        path.moveTo(x, y);
        this.setStartPoint(x, y);
    }

    protected void touch_move(float x, float y) {
        float dx = Math.abs(x - startX);
        float dy = Math.abs(y - startY);
        if (dx >= TOLERANCE || dy >= TOLERANCE) {
            this.setStopPoint(x, y);
        }
    }

    protected void touch_up(float x, float y) {
        path.lineTo(x, y);
        this.setStopPoint(x, y);
        _saveAndInitPath();
    }

    protected void draw(Canvas canvas) {
        drawLine(canvas);
        paths.forEach(_path -> canvas.drawPath(_path, paint));
    }

    protected void drawLine(Canvas canvas) {
        canvas.drawLine(startX, startY, stopX, stopY, paint);
    }
}
