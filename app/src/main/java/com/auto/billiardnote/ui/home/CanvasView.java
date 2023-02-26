package com.auto.billiardnote.ui.home;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class CanvasView extends View {

    Context context;
    static final float TOLERANCE = 4;
    private Bitmap m_bitmap;
    private Canvas m_canvas;
    private CanvasTool canvasTool;

    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        canvasTool = new CanvasTool();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        m_bitmap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
        m_canvas = new Canvas(m_bitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        _drawLine(canvas);
        for (Path path : canvasTool.getPaths()){
            canvas.drawPath(path, canvasTool.getPaint());
        }
    }

    private void _drawLine(Canvas canvas) {
        canvas.drawLine(
                canvasTool.getStartX(), canvasTool.getStartY(),
                canvasTool.getStopX(), canvasTool.getStopY(),
                canvasTool.getPaint()
        );
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touch_start(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                touch_move(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                touch_up(x, y);
                invalidate();
                break;
        }
        return true;
    }

    private void touch_start(float x, float y) {
        canvasTool.getPath().reset();
        if (canvasTool.isEmpty()) {
            canvasTool.setStopPoint(x, y);
        } else {
            x = canvasTool.getStopX();
            y = canvasTool.getStopY();
        }
        canvasTool.getPath().moveTo(x, y);
        canvasTool.setStartPoint(x, y);
    }

    private void touch_move(float x, float y) {
        float dx = Math.abs(x - canvasTool.getStartX());
        float dy = Math.abs(y - canvasTool.getStartY());
        if (dx >= TOLERANCE || dy >= TOLERANCE) {
            canvasTool.setStopPoint(x, y);
        }
    }

    private void touch_up(float x, float y) {
        canvasTool.getPath().lineTo(x, y);
        canvasTool.setStopPoint(x, y);
        m_canvas.drawPath(canvasTool.getPath(), canvasTool.getPaint());
        canvasTool.saveAndInitPath();
    }

    public void unDo() {
        if (canvasTool.unDo())
        {
            invalidate();
        }
    }
}
