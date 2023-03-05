package com.auto.billiardnote.ui.home.draw;

import android.graphics.Color;
import android.graphics.Paint;

public enum DrawingTool {
    LINE(Color.BLACK),
    CUE_BALL(Color.WHITE),
    ORANGE_BALL(Color.rgb(255, 165, 0)),
    RED_BALL(Color.RED);

    private int color;

    DrawingTool(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public static Paint getPaint(DrawingTool type) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(type.color);
        paint.setStyle(Paint.Style.STROKE);
        if (DrawingTool.LINE == type) {
            paint.setStrokeCap(Paint.Cap.BUTT);
            paint.setStrokeJoin(Paint.Join.BEVEL);
            paint.setStrokeWidth(4f);
        } else {
            paint.setStyle(Paint.Style.FILL);
        }
        return paint;
    }

}
