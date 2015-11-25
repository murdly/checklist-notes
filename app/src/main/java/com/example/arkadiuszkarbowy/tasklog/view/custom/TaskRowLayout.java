package com.example.arkadiuszkarbowy.tasklog.view.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import java.util.LinkedHashMap;

/**
 * Created by arkadiuszkarbowy on 02/11/15.
 */
public class TaskRowLayout extends LinearLayout {
    private Context mContext;

    public TaskRowLayout(Context context) {
        super(context);
    }

    public TaskRowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public void addRow() {
        Row row = new Row(mContext);
        addView(row);
    }

    public LinkedHashMap<Integer, Entry> getEntries(){
        LinkedHashMap<Integer, Entry> map = new LinkedHashMap<>();
        for(int i = 0 ; i < getChildCount(); i++) {
            Row row = (Row) getChildAt(i);
            map.put(i, new Entry(row));
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

    public class Entry {
        public String noteText;
        public boolean isNoteChecked;

        public Entry(Row row) {
            this.noteText = row.getNoteText();
            this.isNoteChecked = row.isNoteChecked();
        }

        public boolean hasContent(){
            return !noteText.isEmpty();
        }
    }
}
