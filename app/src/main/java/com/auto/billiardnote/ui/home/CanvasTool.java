package com.auto.billiardnote.ui.home;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import java.util.ArrayList;

public class CanvasTool {
    private ArrayList<Path> paths;
    private ArrayList<PathPoint> pathPoints;
    private PathPoint pathPoint;
    private float startX, startY, stopX, stopY;
    private Path path;
    private Paint paint;

    protected CanvasTool() {
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

    protected ArrayList<Path> getPaths() {
        return this.paths;
    }

    protected float getStartX() {
        return this.startX;
    }

    protected float getStartY() {
        return this.startY;
    }

    protected float getStopX() {
        return this.stopX;
    }

    protected float getStopY() {
        return this.stopY;
    }

    protected Path getPath() {
        return this.path;
    }

    protected Paint getPaint() {
        return this.paint;
    }

    protected boolean isEmpty() {
        return this.stopX < 0.0f && this.stopY < 0.0f;
    }

    protected void saveAndInitPath() {
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
}
