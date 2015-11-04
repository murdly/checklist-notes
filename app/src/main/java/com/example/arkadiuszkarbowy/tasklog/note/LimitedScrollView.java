package com.example.arkadiuszkarbowy.tasklog.note;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ScrollView;

/**
 * Created by arkadiuszkarbowy on 04/11/15.
 */
public class LimitedScrollView extends ScrollView {
    private static final int MAX_HEIGHT = 180;
    public LimitedScrollView(Context context) {
        super(context);
    }

    public LimitedScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, MAX_HEIGHT, getResources()
                .getDisplayMetrics());
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(px, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
