package com.auto.billiardnote.ui.home;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class CanvasView extends View {

    Context context;

    private static final float TOLERANCE = 4;
    private Path m_path;
    private Bitmap m_bitmap;
    private Canvas m_canvas;
    private final Paint m_paint;
    private float m_endX, m_endY;
    private float m_X, m_Y;
    private ArrayList<Path> paths = new ArrayList<>();

    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        m_path = new Path();
        m_paint = new Paint();
        m_paint.setAntiAlias(true);
        m_paint.setColor(Color.BLACK);
        m_paint.setStyle(Paint.Style.STROKE);
        m_paint.setStrokeCap(Paint.Cap.BUTT);
        m_paint.setStrokeJoin(Paint.Join.BEVEL);
        m_paint.setStrokeWidth(4f);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        m_bitmap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
        m_canvas = new Canvas(m_bitmap);
    }


    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawBitmap(m_bitmap, 0, 0, m_paint);
        canvas.drawPath(m_path, m_paint);

//      DRAWING A PREVIEW LINE.
        canvas.drawLine(m_X, m_Y, m_endX, m_endY, m_paint);//
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
                touch_up();
                invalidate();
                break;
        }
        return true;
    }

    private void touch_start(float x, float y) {
        m_path.reset();
        if (m_endX > 0.0f && m_endY > 0.0f) {
            x = m_endX;
            y = m_endY;
        } else {
            m_endX = x;
            m_endY = y;
        }
        m_path.moveTo(x, y);
        m_X = x;
        m_Y = y;
    }

    private void touch_move(float x, float y) {
        float dx = Math.abs(x - m_X);
        float dy = Math.abs(y - m_Y);
        if (dx >= TOLERANCE || dy >= TOLERANCE) {
            m_X = x;
            m_Y = y;
        }
    }

    private void touch_up() {
        m_path.lineTo(m_X, m_Y);
        m_endX = m_X;
        m_endY = m_Y;
        // commit the path to our offscreen
        m_canvas.drawPath(m_path, m_paint);
        // kill this so we don't double draw
        m_path.reset();
        m_path = new Path();
        paths.add(m_path);
    }
}
