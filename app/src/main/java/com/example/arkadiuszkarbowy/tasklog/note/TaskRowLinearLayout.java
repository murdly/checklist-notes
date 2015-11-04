package com.example.arkadiuszkarbowy.tasklog.note;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.example.arkadiuszkarbowy.tasklog.note.Row;

/**
 * Created by arkadiuszkarbowy on 02/11/15.
 */
public class TaskRowLinearLayout extends LinearLayout {
    private int mRowCounter = 0;
    private Context mContext;


    public TaskRowLinearLayout(Context context) {
        super(context);
    }

    public TaskRowLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        mContext = context;
    }

    private void init() {
    }


    public void addRow() {
        addView(new Row(mContext));
    }

    @Override
    public void removeView(View view) {
        int removedIndex = indexOfChild(view);
        super.removeView(view);

        int index = (removedIndex == getChildCount()) ? removedIndex - 1 : removedIndex;
        if(getChildCount() > 0 )
            getChildAt(index).requestFocus();
    }
}
