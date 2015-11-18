package com.example.arkadiuszkarbowy.tasklog.view.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
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
    EditText mText;
    @Bind(R.id.checkBox)
    CheckBox mCheckBox;

    private Context mContext;
    private boolean mIsFirst;

    public Row(Context context) {
        this(context, null);
    }

    public Row(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext= context;
        View v = inflate(context, R.layout.create_task_item, this);
        ButterKnife.bind(v);
        obtainStyle(attrs);
        init();

    }

    private void init() {
        mText.setOnFocusChangeListener(mOnFocusChangeListener);
        InputMethodManager mgr = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        mText.requestFocus();
        mgr.showSoftInput(mText, InputMethodManager.SHOW_IMPLICIT);
    }

    private void obtainStyle(AttributeSet attrs) {
        TypedArray a = mContext.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.Row,
                0, 0);

        try {
            mIsFirst = a.getBoolean(R.styleable.Row_isFirst, false);
        } finally {
            a.recycle();
        }
    }

    public String getNoteText(){
        return mText.getText().toString();
    }

    public Boolean isNoteChecked(){
        return mCheckBox.isChecked();
    }

    private OnFocusChangeListener mOnFocusChangeListener = new OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus & !mIsFirst) mDelete.setVisibility(VISIBLE);
            else mDelete.setVisibility(GONE);
        }
    };

    @OnClick(R.id.delete)
    void removeRow(){
        ((TaskRowLayout)getParent()).removeView(this);

    }
}