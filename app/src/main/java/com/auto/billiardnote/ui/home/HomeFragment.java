package com.auto.billiardnote.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.auto.billiardnote.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final ImageButton undoButton = binding.undoLine;
        final TextView textView = binding.textHome;
        final CanvasView canvasView = binding.canvas;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        TODO: undo버튼을 비활성 하기: xml이나 java에 binding하여 버튼이 unclickable이나 enable을 제어한다.
        또한 비활성화 시 눌러도 반응이 없거나, 그림이 조금 연해야 한다.
        undoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (canvasView.lineArrayList.size() > 0) {
                    canvasView.lineArrayList.remove(canvasView.lineArrayList.size()-1);
                }
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
