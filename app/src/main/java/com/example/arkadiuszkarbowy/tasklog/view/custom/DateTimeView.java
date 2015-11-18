package com.example.arkadiuszkarbowy.tasklog.view.custom;

import android.app.DatePickerDialog;
import android.app.FragmentManager;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.arkadiuszkarbowy.tasklog.R;
import com.example.arkadiuszkarbowy.tasklog.view.dialogs.DatePickerFragment;
import com.example.arkadiuszkarbowy.tasklog.view.dialogs.TimePickerFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by arkadiuszkarbowy on 16/11/15.
 */
public class DateTimeView extends RelativeLayout {

    @Bind(R.id.set)
    TextView mSet;
    @Bind(R.id.date)
    EditText mDate;
    @Bind(R.id.time)
    EditText mTime;
    @Bind(R.id.dateTimeLayout)
    LinearLayout mDateTimeLayout;
    @Bind(R.id.cancel)
    ImageView mCancel;
    @Bind(R.id.ic)
    ImageView mIcon;

    private FragmentManager mFragmentManager; //todo inject
    private Calendar mCalendar;
    private SimpleDateFormat mDateFormat, mTimeFormat;
    private boolean isSet = false;

    public DateTimeView(Context context) {
        this(context, null);
    }

    public DateTimeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DateTimeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View v = inflate(context, R.layout.datetime_view, this);
        ButterKnife.bind(v);
        obtainStyle(context, attrs);
    }

    private void obtainStyle(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.DateTimeView,
                0, 0);

        try {
            mSet.setText(a.getString(R.styleable.DateTimeView_text));
            mIcon.setImageResource(a.getResourceId(R.styleable.DateTimeView_myicon, -1));
        } finally {
            a.recycle();
        }
    }

    public void initialize(FragmentManager fm) {
        mFragmentManager = fm;
        mCalendar = Calendar.getInstance();
        mDateFormat = new SimpleDateFormat("EEE, MMM d", Locale.getDefault());
        mTimeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
    }

    @OnClick(R.id.set)
    void onSet() {
        isSet = true;
        mDate.setText(mDateFormat.format(mCalendar.getTime()));
        mTime.setText(mTimeFormat.format(mCalendar.getTime()));
        mSet.setVisibility(View.GONE);
        mDateTimeLayout.setVisibility(View.VISIBLE);
        mCancel.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.date)
    void showCalendar() {
        new DatePickerFragment().newInstance(mDateListener, mCalendar).show(mFragmentManager,
                "calendar");
    }

    @OnClick(R.id.time)
    void showTimer() {
        new TimePickerFragment().newInstance(mTimeListener, mCalendar).show(mFragmentManager,
                "time");
    }

    @OnClick(R.id.cancel)
    void onCancel() {
        isSet = false;
        mCalendar.clear();
        mCancel.setVisibility(View.GONE);
        mDateTimeLayout.setVisibility(View.GONE);
        mSet.setVisibility(View.VISIBLE);
    }

    private DatePickerDialog.OnDateSetListener mDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mCalendar.set(year, monthOfYear, dayOfMonth);
            mDate.setText(mDateFormat.format(mCalendar.getTime()));
        }
    };

    private TimePickerDialog.OnTimeSetListener mTimeListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            mCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            mCalendar.set(Calendar.MINUTE, minute);
            mTime.setText(mTimeFormat.format(mCalendar.getTime()));
        }
    };

    public Calendar getCalendar() {
        return isSet ? mCalendar : null;
    }
}