package com.auto.billiardnote.ui.home;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class CanvasView extends View {

    Context context;
    private Bitmap m_bitmap;
    private Canvas m_canvas;
    private StraightLine line;

    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        line = new StraightLine();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        m_bitmap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
        m_canvas = new Canvas(m_bitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        line.draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                line.touch_start(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                line.touch_move(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                line.touch_up(x, y);
                invalidate();
                break;
        }
        return true;
    }

    public void unDo() {
        if (line.unDo()) {invalidate();}
    }
}
