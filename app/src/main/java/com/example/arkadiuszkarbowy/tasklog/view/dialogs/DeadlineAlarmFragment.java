package com.example.arkadiuszkarbowy.tasklog.view.dialogs;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.arkadiuszkarbowy.tasklog.R;
import com.example.arkadiuszkarbowy.tasklog.view.dialogs.DatePickerFragment;
import com.example.arkadiuszkarbowy.tasklog.view.dialogs.TimePickerFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by arkadiuszkarbowy on 04/11/15.
 */
public class DeadlineAlarmFragment extends Fragment {

    @Bind(R.id.setDeadline)
    TextView mSetDeadline;
    @Bind(R.id.dateTimeDeadlineLayout)
    LinearLayout mDeadlineLayout;
    @Bind(R.id.deadlineDate)
    EditText mDeadlineDate;
    @Bind(R.id.deadlineTime)
    EditText mDeadlineTime;
    @Bind(R.id.deadlineCancel)
    ImageView mDeadlineCancel;

    @Bind(R.id.setAlarm)
    TextView mSetAlarm;
    @Bind(R.id.dateTimeAlarmLayout)
    LinearLayout mAlarmLayout;
    @Bind(R.id.alarmDate)
    EditText mAlarmDate;
    @Bind(R.id.alarmTime)
    EditText mAlarmTime;
    @Bind(R.id.alarmCancel)
    ImageView mAlarmCancel;

    private Calendar mDeadlineCalendar, mAlarmCalendar;
    private SimpleDateFormat mDateFormat, mTimeFormat;
    private boolean isDeadlineSet, isAlarmSet;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.datetime_fragment, container, false);
        ButterKnife.bind(this, v);
        init();
        return v;
    }

    private void init() {
        mDeadlineCalendar = Calendar.getInstance();
        mAlarmCalendar = Calendar.getInstance();
        mDateFormat = new SimpleDateFormat("EEE, MMM d", Locale.getDefault());
        mTimeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
    }

    @OnClick(R.id.setDeadline)
    void onSetDeadline() {
        isDeadlineSet = true;
        mDeadlineDate.setText(mDateFormat.format(Calendar.getInstance().getTime()));
        mDeadlineTime.setText(mTimeFormat.format(Calendar.getInstance().getTime()));
        mSetDeadline.setVisibility(View.GONE);
        mDeadlineLayout.setVisibility(View.VISIBLE);
        mDeadlineCancel.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.deadlineDate)
    void showDeadlineCalendar() {
        new DatePickerFragment().newInstance(mDeadlineDateListener, mDeadlineCalendar).show(getFragmentManager(),
                "deadline");
    }

    @OnClick(R.id.deadlineTime)
    void showDeadlineTimer() {
        new TimePickerFragment().newInstance(mDeadlineTimeListener, mDeadlineCalendar).show(getFragmentManager(),
                "deadline");
    }

    @OnClick(R.id.deadlineCancel)
    void onDeadlineCancel() {
        isDeadlineSet = false;
        mDeadlineCalendar.clear();
        mDeadlineCancel.setVisibility(View.GONE);
        mDeadlineLayout.setVisibility(View.GONE);
        mSetDeadline.setVisibility(View.VISIBLE);
    }


    @OnClick(R.id.setAlarm)
    void onSetAlarm() {
        isAlarmSet = true;
        mAlarmDate.setText(mDateFormat.format(Calendar.getInstance().getTime()));
        mAlarmTime.setText(mTimeFormat.format(Calendar.getInstance().getTime()));
        mSetAlarm.setVisibility(View.GONE);
        mAlarmLayout.setVisibility(View.VISIBLE);
        mAlarmCancel.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.alarmDate)
    void showAlarmCalendar() {
        new DatePickerFragment().newInstance(mAlarmDateListener, mAlarmCalendar).show(getFragmentManager(),
                "alarmdate");
    }

    @OnClick(R.id.alarmTime)
    void showAlarmTimer() {
        new TimePickerFragment().newInstance(mAlarmTimeListener, mDeadlineCalendar).show(getFragmentManager(),
                "alarmtime");
    }

    @OnClick(R.id.alarmCancel)
    void onAlarmCancel() {
        isAlarmSet = false;
        mAlarmCalendar.clear();
        mAlarmCancel.setVisibility(View.GONE);
        mAlarmLayout.setVisibility(View.GONE);
        mSetAlarm.setVisibility(View.VISIBLE);
    }


    DatePickerDialog.OnDateSetListener mDeadlineDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mDeadlineCalendar.set(year, monthOfYear, dayOfMonth);
            mDeadlineDate.setText(mDateFormat.format(mDeadlineCalendar.getTime()));
        }
    };

    TimePickerDialog.OnTimeSetListener mDeadlineTimeListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            mDeadlineCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            mDeadlineCalendar.set(Calendar.MINUTE, minute);
            mDeadlineTime.setText(mTimeFormat.format(mDeadlineCalendar.getTime()));
        }
    };

    DatePickerDialog.OnDateSetListener mAlarmDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mAlarmCalendar.set(year, monthOfYear, dayOfMonth);
            mAlarmDate.setText(mDateFormat.format(mAlarmCalendar.getTime()));
        }
    };

    TimePickerDialog.OnTimeSetListener mAlarmTimeListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            mAlarmCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            mAlarmCalendar.set(Calendar.MINUTE, minute);
            mAlarmTime.setText(mTimeFormat.format(mAlarmCalendar.getTime()));
        }
    };

    public Calendar getDeadlineCalendar() {
        return isDeadlineSet ? mDeadlineCalendar : null;
    }

    public Calendar getAlarmCalendar() {
        return isAlarmSet ? mAlarmCalendar : null;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        ButterKnife.unbind(this);
    }
}