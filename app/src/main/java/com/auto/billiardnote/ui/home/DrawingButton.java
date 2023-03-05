package com.auto.billiardnote.ui.home;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import com.auto.billiardnote.ui.home.draw.DrawingTool;

public class DrawingButton extends androidx.appcompat.widget.AppCompatImageButton {

    private DrawingTool tool;

    public DrawingButton(Context context) {
        super(context);
    }

    public DrawingButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawingButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DrawingTool getTool() {
        return tool;
    }

    public void setTool(DrawingTool tool) {
        this.tool = tool;
    }

    public void setModeChange(boolean enable) {
        this.setEnabled(enable);
        int enableColor = Color.WHITE;
        int disableColor = Color.DKGRAY;
        setBackgroundColor(enable ? enableColor : disableColor);
    }
}
