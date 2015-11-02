package com.example.arkadiuszkarbowy.tasklog;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by arkadiuszkarbowy on 02/11/15.
 */
public class ExpandedListView extends ListView {
    private android.view.ViewGroup.LayoutParams mParams;
    private int mItemsCount = 0;

    public ExpandedListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (getCount() != mItemsCount) {
            mItemsCount = getCount();
            mParams = getLayoutParams();
            mParams.height = getCount() * (mItemsCount > 0 ? getChildAt(0).getHeight() : 0);
            setLayoutParams(mParams);
        }

        super.onDraw(canvas);
    }
}
