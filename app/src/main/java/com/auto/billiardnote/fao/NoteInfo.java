package com.auto.billiardnote.fao;

import androidx.annotation.NonNull;

import com.auto.billiardnote.ui.home.draw.Ball;
import com.auto.billiardnote.ui.home.draw.StraightLine;
import com.auto.billiardnote.ui.home.draw.shape.PathPoint;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.GsonBuilder;

import java.util.HashSet;

public class NoteInfo {
    public StraightLine straightLine;
    public HashSet<Ball> balls;
    public String memo;
    public String title;

    public NoteInfo(StraightLine straightLine, HashSet<Ball> balls, String memo, String title) {
        this.title = title;
        this.straightLine = straightLine;
        this.balls = balls;
        this.memo = memo;
    }

    public StraightLine getStraightLine() {
        return straightLine;
    }

    public HashSet<Ball> getBalls() {
        return balls;
    }

    public String getMemo() {
        return memo;
    }

    @NonNull
    @Override
    public String toString() {
        return new GsonBuilder()
                .addSerializationExclusionStrategy(strategy)
                .create().toJson(this, NoteInfo.class);
    }
    ExclusionStrategy strategy = new ExclusionStrategy() {
        @Override
        public boolean shouldSkipField(FieldAttributes field) {
            return field.getDeclaringClass() == PathPoint.class && field.getName().equals("IGNORE");
        }

        @Override
        public boolean shouldSkipClass(Class<?> clazz) {
            return false;
        }
    };
}
