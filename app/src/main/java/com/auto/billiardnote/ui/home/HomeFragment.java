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

import com.auto.billiardnote.R;
import com.auto.billiardnote.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.Arrays;

public class HomeFragment extends Fragment implements ShapeClickInterface {

    private FragmentHomeBinding binding;
    private ArrayList<ImageButton> buttons;
    final static int DISABLED = Color.rgb(200, 200, 200);

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        final CanvasView canvasView = binding.canvas;

        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        canvasView.setClickListener(this);
        buttons = new ArrayList<>(Arrays.asList(binding.cueBall, binding.redBall, binding.orangeBall, binding.line));
        setReadMode(canvasView.isReadOnly);
        binding.changeMode.setBackgroundResource(canvasView.isReadOnly ? R.drawable.ic_read_mode_foreground : R.drawable.ic_edit_mode_foreground);

        binding.undoLine.setOnClickListener(v -> {
            canvasView.unDo();
            _setToolNBGColor(DrawingTool.LINE, binding.line);
        });
        binding.cueBall.setOnClickListener(v -> _setToolNBGColor(DrawingTool.CUE_BALL, binding.cueBall));
        binding.orangeBall.setOnClickListener(v -> _setToolNBGColor(DrawingTool.ORANGE_BALL, binding.orangeBall));
        binding.redBall.setOnClickListener(v -> _setToolNBGColor(DrawingTool.RED_BALL, binding.redBall));
        binding.line.setOnClickListener(v -> _setToolNBGColor(DrawingTool.LINE, binding.line));
        binding.changeMode.setOnClickListener(v -> modeChange(binding.canvas.isReadOnly));

        return root;
    }

    private void _setToolNBGColor(DrawingTool tool, ImageButton button) {
        selectTool(tool);
        _setOtherToolsBGColor(button);
    }

    private void _setOtherToolsBGColor(ImageButton except) {
        for (ImageButton button : buttons) {
            if (button.equals(except)) {
                button.setBackgroundColor(Color.WHITE);
            } else {
                button.setBackgroundColor(DISABLED);
            }
        }
    }

    private void setReadMode(boolean status) {
        for (ImageButton button : buttons) {
            //TODO: disabled color 지정을 위해서, edit모드시 선택된 tool만 활성화하는 기능을 위해서 custom button으로 변경한다.
            button.setEnabled(!status);
            button.setBackgroundColor(status ? DISABLED : Color.WHITE);
        }
        binding.line.setEnabled(!status);
        binding.canvas.setEnabled(!status);
        binding.textHome.setEnabled(!status);
        binding.undoLine.setEnabled(!status);
        if(status) {
            binding.textHome.setBackgroundColor(DISABLED);
            binding.undoLine.setBackgroundColor(DISABLED);
        } else {
            binding.textHome.setBackgroundColor(Color.WHITE);
            binding.undoLine.setBackgroundColor(Color.WHITE);
        }
    }

    public void selectTool(DrawingTool tool) {
        this.binding.canvas.selectTool(tool);
    }

    public void modeChange(boolean toggle) {
        setReadMode(binding.canvas.setReadOnlyState(toggle));
        binding.changeMode.setBackgroundResource(toggle ? R.drawable.ic_edit_mode_foreground : R.drawable.ic_read_mode_foreground);
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
