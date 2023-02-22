package com.auto.billiardnote.ui.home;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.media.ImageWriter;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private final MutableLiveData<ImageView> mImgView;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mImgView = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }

    public  LiveData<ImageView> getImageView() {
        return mImgView;
    }
}