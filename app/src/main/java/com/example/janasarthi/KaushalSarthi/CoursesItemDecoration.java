package com.example.janasarthi.KaushalSarthi;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CoursesItemDecoration extends RecyclerView.ItemDecoration {

    public final int height;

    public CoursesItemDecoration(int height) {
        this.height = height;
    }


    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.bottom = height;
    }
}
