package com.auto.billiardnote;

import org.junit.Test;

import static org.junit.Assert.*;

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
}