package com.example.arkadiuszkarbowy.tasklog.note;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.arkadiuszkarbowy.tasklog.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by arkadiuszkarbowy on 02/11/15.
 */
public class Row extends LinearLayout {
    @Bind(R.id.delete)
    ImageView mDelete;
    @Bind(R.id.text)
    EditText text;
    @Bind(R.id.checkBox)
    CheckBox mCheckBox;

    public Row(Context context) {
        this(context, null);
    }

    public Row(Context context, AttributeSet attrs) {
        super(context, attrs);
        View v = inflate(context, R.layout.create_task_item, this);
        ButterKnife.bind(v);

        text.setOnFocusChangeListener(mOnFocusChangeListener);
        InputMethodManager mgr = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        text.requestFocus();
        mgr.showSoftInput(text, InputMethodManager.SHOW_IMPLICIT);
    }

    public String getNoteText(){
        return text.getText().toString();
    }

    public Boolean isNoteChecked(){
        return mCheckBox.isChecked();
    }

    OnFocusChangeListener mOnFocusChangeListener = new OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) mDelete.setVisibility(VISIBLE);
            else mDelete.setVisibility(GONE);
        }
    };

    @OnClick(R.id.delete)
    void removeRow(){
        ((TaskRowLinearLayout)getParent()).removeView(this);

    }

}