package com.auto.billiardnote.ui.home.draw;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

import com.auto.billiardnote.ui.home.draw.shape.PathPoint;

import java.util.ArrayList;

public class StraightLine {
    private float startX, startY, stopX, stopY;
    private final float IGNORE = -1.0f;
    private final ArrayList<Path> paths;
    private final ArrayList<PathPoint> pathHistory;
    private final Paint paint;
    private Path path;

    protected StraightLine() {
        pathHistory = new ArrayList<>();
        paint = DrawingTool.getPaint(DrawingTool.LINE);
        path = new Path();
        paths = new ArrayList<>();
        _initPoints();
    }

    protected void touch_start(float x, float y) {
        path.reset();
        if (_isEmpty()) {
            _setPoint(IGNORE, IGNORE, x, y);
        } else {
            x = stopX;
            y = stopY;
        }
        path.moveTo(x, y);
        _setPoint(x, y, IGNORE, IGNORE);
    }

    protected void touch_move(float x, float y) {
        float dx = Math.abs(x - startX);
        float dy = Math.abs(y - startY);
        float TOLERANCE = 4;
        if (dx >= TOLERANCE || dy >= TOLERANCE) {
            _setPoint(IGNORE, IGNORE, x, y);
        }
    }

    protected void touch_up(float x, float y) {
        path.lineTo(x, y);
        _setPoint(IGNORE, IGNORE, x, y);
        _addAndInitPath();
    }

    protected void draw(Canvas canvas) {
        canvas.drawLine(startX, startY, stopX, stopY, paint);
        for (Path path : paths) {
            canvas.drawPath(path, paint);
        }
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
        startX = startY = stopX = stopY = IGNORE;
    }

    private boolean _isEmpty() {return stopX == IGNORE  && stopY == IGNORE;}

    private void _addAndInitPath() {
        pathHistory.add(new PathPoint(startX, startY, stopX, stopY));
        paths.add(path);
        path = new Path();
    }

    private void _setLastPoint(int index) {
        _setPoint(
                pathHistory.get(index).getStartX(),
                pathHistory.get(index).getStartY(),
                pathHistory.get(index).getStopX(),
                pathHistory.get(index).getStopY()
        );
    }

    private void _setPoint(float startX, float startY, float stopX, float stopY) {
        this.startX = startX == IGNORE ? this.startX : startX;
        this.startY = startY == IGNORE  ? this.startY : startY;
        this.stopX = stopX == IGNORE  ? this.stopX : stopX;
        this.stopY = stopY == IGNORE  ? this.stopY : stopY;
    }
}
