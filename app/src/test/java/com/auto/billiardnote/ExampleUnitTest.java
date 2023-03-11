package com.auto.billiardnote;

import org.junit.Test;

import static org.junit.Assert.*;

import com.auto.billiardnote.ui.home.draw.Ball;
import com.auto.billiardnote.ui.home.draw.DrawingTool;
import com.auto.billiardnote.ui.home.draw.shape.Circle;
import com.google.gson.Gson;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        float t = 3;
        float x = 1;
        float r = 2;
        assertEquals( (x-r) <= t && t <= (x+r), true);
    }

    @Test
    public void toStringBYJson() {
        Ball a = new Ball(10f, 20f, 30f, DrawingTool.CUE_BALL);
        Gson b = new Gson();
        assertEquals("{x: 10.0, y: 20.0, r: 30.0}", b.toJson(a));
    }
}