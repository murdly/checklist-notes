package com.example.arkadiuszkarbowy.tasklog.note;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.example.arkadiuszkarbowy.tasklog.note.Row;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by arkadiuszkarbowy on 02/11/15.
 */
public class TaskRowLinearLayout extends LinearLayout {
    private Context mContext;

    public TaskRowLinearLayout(Context context) {
        super(context);
    }

    public TaskRowLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public void addRow() {
        Row entry = new Row(mContext);
        addView(entry);
    }

    public LinkedHashMap<String, Boolean> getEntries(){
        LinkedHashMap<String,Boolean> map = new LinkedHashMap<>();
        for(int i = 0 ; i < getChildCount(); i++) {
            Row entry = ((Row)getChildAt(i));
            map.put(entry.getNoteText(), entry.isNoteChecked());
        }

        return map;
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
