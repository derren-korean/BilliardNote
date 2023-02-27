package com.auto.billiardnote.ui.home;

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

//      TODO: undo버튼을 비활성 하기: xml이나 java에 binding하여 버튼이 unclickable이나 enable을 제어한다.
//        TODO: 왼쪽은 당구공 팔렛트이다. Circle을 만들어서 canvasView에 위치를 선정하여 넣는다. 클래스를 만들어서 진행한다.
        undoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {canvasView.unDo();}
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
