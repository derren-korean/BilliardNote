package com.auto.billiardnote.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.auto.billiardnote.R;
import com.auto.billiardnote.databinding.FragmentHomeBinding;
import com.auto.billiardnote.fao.FileIO;
import com.auto.billiardnote.ui.home.draw.CanvasView;
import com.auto.billiardnote.ui.home.draw.DrawingTool;
import com.auto.billiardnote.ui.home.draw.ShapeClickInterface;

import java.util.ArrayList;
import java.util.Arrays;

public class HomeFragment extends Fragment implements ShapeClickInterface {

    private FragmentHomeBinding binding;
    private ArrayList<DrawingButton> drawingButtons;
    private ArrayList<View> functionView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        final TextView textView = binding.textHome;
        final CanvasView canvasView = binding.canvas;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        _initView(textView, canvasView);


        binding.undoLine.setOnClickListener(v -> {
            canvasView.unDo();
            _setToolNBGColor(binding.line);
        });
        // TODO: data save and load
        binding.imageView2.setOnClickListener(v -> {
            FileIO.read();
            binding.textHome.setText(FileIO.getMyData());
        });
        binding.line.setOnClickListener(v -> _setToolNBGColor(binding.line));
        binding.cueBall.setOnClickListener(v -> _setToolNBGColor(binding.cueBall));
        binding.orangeBall.setOnClickListener(v -> _setToolNBGColor(binding.orangeBall));
        binding.redBall.setOnClickListener(v -> _setToolNBGColor(binding.redBall));
        binding.changeMode.setOnClickListener(v -> modeChange(!binding.canvas.enabled));

        return root;
    }

    private void _initView(final TextView textView, final CanvasView canvasView) {

        canvasView.setClickListener(this);

        functionView = new ArrayList<>(Arrays.asList(textView, binding.undoLine));
        drawingButtons = new ArrayList<>(Arrays.asList(binding.line, binding.cueBall, binding.orangeBall, binding.redBall));
        for (int i = 0; i < drawingButtons.size(); i++) {
            drawingButtons.get(i).setTool(DrawingTool.values()[i]);
        }
        modeChange(canvasView.enabled);
    }

    private void _setInitUIDrawingButton(boolean enable) {
        if (enable) {
            DrawingButton button = binding.cueBall;
            for (DrawingButton _button : drawingButtons) {
                if (_button.getTool() == binding.canvas.getDrawingTool()) {
                    button = _button;
                    break;
                }
            }
            _setOtherToolsBGColor(button);
        }
    }

    private void _setToolNBGColor(DrawingButton button) {
        selectTool(button.getTool());
        _setOtherToolsBGColor(button);
    }

    private void _setOtherToolsBGColor(DrawingButton except) {
        for (DrawingButton button : drawingButtons) {
            button.setBackgroundColor(_getEnableColor(button.equals(except)));
        }
    }

    private void setEnable(boolean enable) {
        binding.canvas.setEnabled(enable);
        for (DrawingButton button : drawingButtons) {
            button.setModeChange(enable);
        }
        _setInitUIDrawingButton(enable);
        for (View view : functionView) {
            view.setEnabled(enable);
            view.setBackgroundColor(_getEnableColor(enable));
        }
    }

    public void selectTool(DrawingTool tool) {
        binding.canvas.setDrawingTool(tool);
    }

    public void modeChange(boolean enable) {
        setEnable(binding.canvas.setEnable(enable));
        binding.changeMode.setBackgroundResource(enable ? R.drawable.ic_read_mode_foreground : R.drawable.ic_edit_mode_foreground);
        Toast.makeText(this.getContext(), enable ? "수정 모드" : "읽기 모드", Toast.LENGTH_SHORT).show();
    }

    private int _getEnableColor(boolean enable) {
        return enable ? Color.WHITE : Color.DKGRAY;
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
