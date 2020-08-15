package com.example.janasarthi.DhanSarthi;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BusinessItemDecorator extends RecyclerView.ItemDecoration {

    private final int height;

    public BusinessItemDecorator(int height) {
        this.height = height;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.bottom = height;
    }
}
