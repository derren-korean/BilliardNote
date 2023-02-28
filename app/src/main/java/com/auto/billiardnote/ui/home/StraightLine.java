package com.auto.billiardnote.ui.home;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

import java.util.ArrayList;

public class StraightLine {
    private final float TOLERANCE = 4;
    private float startX, startY, stopX, stopY;
    private ArrayList<Path> paths;
    private ArrayList<PathPoint> pathHistory;
    private PathPoint pathPoint;
    private Path path;
    private Paint paint;

    protected StraightLine() {
        pathPoint = new PathPoint();
        pathHistory = new ArrayList<>();
        paint = DrawingTool.getPaint(DrawingTool.LINE);
        path = new Path();
        paths = new ArrayList<>();
        _initPoints();
    }

    protected void touch_start(float x, float y) {
        path.reset();
        if (_isEmpty()) {
            _setStopPoint(x, y);
        } else {
            x = stopX;
            y = stopY;
        }
        path.moveTo(x, y);
        _setStartPoint(x, y);
    }

    protected void touch_move(float x, float y) {
        float dx = Math.abs(x - startX);
        float dy = Math.abs(y - startY);
        if (dx >= TOLERANCE || dy >= TOLERANCE) {
            _setStopPoint(x, y);
        }
    }

    protected void touch_up(float x, float y) {
        path.lineTo(x, y);
        _setStopPoint(x, y);
        _saveAndInitPath();
    }

    protected void draw(Canvas canvas) {
        drawLine(canvas);
        paths.forEach(_path -> canvas.drawPath(_path, paint));
    }

    protected void drawLine(Canvas canvas) {
        canvas.drawLine(startX, startY, stopX, stopY, paint);
    }

    protected boolean unDo() {
        if (paths.size() > 0) {
            paths.remove(paths.size()-1);
            pathHistory.remove(pathHistory.size()-1);
            if (paths.size() == 0) {
                _initPoints();
                return true;
            }
            _setLastPoint(pathHistory.size()-1);
            return true;
        }
        return false;
    }

    private void _initPoints() {
        startX = -1.0f;
        startY = -1.0f;
        stopX = -1.0f;
        stopY = -1.0f;
    }

    private void _setStartPoint(float x, float y) {
        startX = x;
        startY = y;
    }

    private void _setStopPoint(float x, float y) {
        stopX = x;
        stopY = y;
    }

    private boolean _isEmpty() {return stopX < 0.0f && stopY < 0.0f;}

    private void _saveAndInitPath() {
        pathPoint.setPoint(startX, startY, stopX, stopY);
        pathHistory.add(pathPoint);
        paths.add(path);
        path = new Path();
        pathPoint = new PathPoint();
    }

    private void _setLastPoint(int index) {
        _setStopPoint(
                pathHistory.get(index).getStopX(),
                pathHistory.get(index).getStopY()
        );
        _setStartPoint(
                pathHistory.get(index).getStartX(),
                pathHistory.get(index).getStartY()
        );
    }
}
