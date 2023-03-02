package com.auto.billiardnote.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.auto.billiardnote.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.Arrays;

public class HomeFragment extends Fragment implements ShapeClickInterface {

    private FragmentHomeBinding binding;
    private ArrayList<ImageButton> buttons;
    private final int disabled = Color.rgb(200, 200, 200);

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        final CanvasView canvasView = binding.canvas;
        buttons = new ArrayList<>(Arrays.asList(binding.cueBall, binding.redBall, binding.orangeBall, binding.line));
        canvasView.setClickListener(this);

        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        binding.undoLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                canvasView.unDo();
                _setToolNBGColor(DrawingTool.LINE, binding.line, Color.DKGRAY);
            }
        });
        binding.cueBall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _setToolNBGColor(DrawingTool.CUE_BALL, binding.cueBall, disabled);
            }
        });
        binding.orangeBall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _setToolNBGColor(DrawingTool.ORANGE_BALL, binding.orangeBall, disabled);
            }
        });

        binding.redBall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _setToolNBGColor(DrawingTool.RED_BALL, binding.redBall, disabled);
            }
        });
        binding.line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _setToolNBGColor(DrawingTool.LINE, binding.line, Color.DKGRAY);
            }
        });

        return root;
    }

    private void _setToolNBGColor(DrawingTool tool, ImageButton button, int color) {
        selectTool(tool);
        _setOtherToolsBGColor(button, color);
    }

    private void _setOtherToolsBGColor(ImageButton except, int disabled) {
        buttons.stream().forEach(imageButton -> {
            if (imageButton.equals(except)) {
                imageButton.setBackgroundColor(Color.WHITE);
            } else {
                imageButton.setBackgroundColor(disabled);
            }
        });
    }

    public void selectTool(DrawingTool tool) {
        this.binding.canvas.selectTool(tool);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onCircleClick() {

    }

}
