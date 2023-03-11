package com.auto.billiardnote.ui.home.draw;

import android.graphics.Color;
import android.graphics.Paint;

public enum DrawingTool {
    LINE(Color.BLACK), // 순서가 중요함.
    CUE_BALL(Color.WHITE),
    ORANGE_BALL(Color.parseColor("#FFA500")),
    RED_BALL(Color.RED);

    private final int color;

    DrawingTool(int color) {
        this.color = color;
    }

    public static Paint getPaint(DrawingTool tool) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(tool.color);
        paint.setStyle(Paint.Style.STROKE);
        if (DrawingTool.LINE == tool) {
            paint.setStrokeCap(Paint.Cap.BUTT);
            paint.setStrokeJoin(Paint.Join.BEVEL);
            paint.setStrokeWidth(4f);
        } else {
            paint.setStyle(Paint.Style.FILL);
        }
        return paint;
    }

    public boolean isSame(int color) {
        return this.color == color;
    }
}
