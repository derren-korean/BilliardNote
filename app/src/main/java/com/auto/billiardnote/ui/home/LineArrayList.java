package com.auto.billiardnote.ui.home;

import java.util.ArrayList;

public class LineArrayList extends ArrayList<MyLine>{

    private ArrayList<MyLine> lineArrayList;

    public LineArrayList() {
        this.lineArrayList = new ArrayList<>();
    }

    public float getLastEndX() {
        return _getLastLine().getEndX();
    }

    public float getLastEndY() {
        return _getLastLine().getEndY();
    }

    private MyLine _getLastLine() {
        return this.get(this.size()-1);
    }

    @Override
    public boolean isEmpty() {
        return this == null || this.size() == 0;
    }
}
