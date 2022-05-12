package com.example.rnmediadev007.livesoccerapp.model;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by zeeshanbutt on 5/9/2017.
 */
public class NonScrollableList extends ListView {

    public NonScrollableList(Context context) {
        super(context);
    }

    public NonScrollableList(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NonScrollableList(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightMeasureSpec_custom = View.MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, View.MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec_custom);
        ViewGroup.LayoutParams params = getLayoutParams();
        params.height = getMeasuredHeight();
    }
}

