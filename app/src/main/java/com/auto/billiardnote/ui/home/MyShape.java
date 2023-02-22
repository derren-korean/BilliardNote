package com.auto.billiardnote.ui.home;

import android.graphics.Paint;

public class MyShape {

    float shape_type, startX, startY, stopX, stopY;
    Paint paint;

    public MyShape(float shape_type, float startX, float startY, float stopX, Paint paint) {
        this.shape_type = shape_type;
        this.startX = startX;
        this.startY = startY;
        this.stopX = stopX;
        this.stopY = stopY;
        this.paint = paint;
    }
}
