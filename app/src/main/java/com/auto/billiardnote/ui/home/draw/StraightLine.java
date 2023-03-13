package com.auto.billiardnote.ui.home.draw;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.auto.billiardnote.ui.home.draw.shape.PathPoint;

import java.util.ArrayList;

public class StraightLine {

    private final ArrayList<PathPoint> pathHistory;
    private final Paint paint;
    private PathPoint pathPoint;

    public StraightLine(StraightLine line) {
        this.pathHistory = line.pathHistory;
        this.paint = line.paint;
        this.pathPoint = line.pathPoint;
    }

    protected StraightLine() {
        pathPoint = new PathPoint();
        pathHistory = new ArrayList<>();
        paint = DrawingTool.getPaint(DrawingTool.LINE);
    }

    protected void touch_start(float x, float y) {
        pathPoint.touch_start(x, y);
    }

    protected void touch_move(final float x, final float y) {
        pathPoint.touch_move(x, y);
    }

    protected void touch_up(final float x, final float y) {
        pathPoint.touch_up(x, y);
        pathHistory.add(new PathPoint(pathPoint));
    }

    protected void draw(Canvas canvas) {
        pathPoint.draw(canvas, paint);
        for (PathPoint _pathPoint : pathHistory) {
            _pathPoint.draw(canvas, paint);
        }
    }

    protected boolean unDo() {
        if (pathHistory.size() > 0) {
            pathHistory.remove(pathHistory.size()-1);
            if (pathHistory.size() == 0) {
                pathPoint = new PathPoint();
                return true;
            }
            this.pathPoint = pathHistory.get(pathHistory.size()-1);
            return true;
        }
        return false;
    }

    public ArrayList<PathPoint> getPathHistory() {
        return pathHistory;
    }
}
