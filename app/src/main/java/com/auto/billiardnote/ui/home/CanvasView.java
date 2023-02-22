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

    protected ArrayList<MyLine> lineArrayList = new ArrayList<>();

    private static final float TOLERANCE = 5;
    private Path m_path;
    private Bitmap m_bitmap;
    private Canvas m_canvas;
    private final Paint m_paint;
    private boolean m_isTouchUp;
    private boolean m_drawPoint;
    private float m_startX, m_startY, m_endX, m_endY;
    private MyLine m_currentLine;

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
        super.onDraw(canvas);
        canvas.drawLine(m_startX, m_startY, m_endX, m_endY, m_paint);

        if (this.m_isTouchUp) {
            m_currentLine = new MyLine(m_startX, m_startY, m_endX, m_endY, m_paint);
            lineArrayList.add(m_currentLine);
        }
        for(MyLine line: lineArrayList)
        {
            _draw_line(line, canvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                m_isTouchUp = false;
                this.m_drawPoint = true;
                m_startX = event.getX();
                m_startY = event.getY();
                // Set the end to prevent initial jump (like on the demo recording)
                m_endX = event.getX();
                m_endY = event.getY();
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                _touch_move(event.getX(), event.getY());
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                m_isTouchUp = true;
                m_endX = event.getX();
                m_endY = event.getY();
                _touch_up();
                invalidate();
                break;
        }
        return true ;
    }

    private void _draw_line(MyLine line, Canvas canvas) {
        canvas.drawLine(line.startX, line.startY, line.endX, line.endY, line.paint);
    }

    private void _touch_up() {
        if(m_drawPoint == true) {
            m_canvas.drawPoint(m_endX, m_endY, m_paint);
        } else {
            m_path.lineTo(m_endX, m_endY);
            // commit the path to our offscreen
            m_canvas.drawPath(m_path, m_paint);
            // kill this so we don't double draw
            m_path.reset();
        }
    }

    private void _touch_move(float x, float y) {
        float dx = Math.abs(x - m_endX);
        float dy = Math.abs(y - m_endY);
        if (dx >= TOLERANCE || dy >= TOLERANCE) {
            m_path.quadTo(m_endX, m_endY, (x + m_endX)/2, (y + m_endY)/2);
            m_endX = x;
            m_endY = y;
            m_drawPoint = false;
        }
    }

    class MyLine {
        private float startX, startY, endX, endY;
        Paint paint;

        public MyLine(float startX, float startY, float endX, float endY, Paint paint) {
            this.startX = startX;
            this.startY = startY;
            this.endX = endX;
            this.endY = endY;
            this.paint = paint;
        }
    }
}
