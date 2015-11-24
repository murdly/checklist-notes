package com.example.arkadiuszkarbowy.tasklog.view.dialogs;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.arkadiuszkarbowy.tasklog.R;
import com.example.arkadiuszkarbowy.tasklog.view.custom.DateTimeView;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by arkadiuszkarbowy on 04/11/15.
 */
public class DeadlineAlarmFragment extends Fragment {

    @Bind(R.id.deadline)
    DateTimeView mDeadline;
    @Bind(R.id.alarm)
    DateTimeView mAlarm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.datetime_fragment, container, false);
        ButterKnife.bind(this, v);
        init();
        return v;
    }

    private void init() {
        mDeadline.initialize(getFragmentManager());
        mAlarm.initialize(getFragmentManager());
    }

    public Calendar getDeadlineCalendar() {
        return mDeadline.getCalendar();
    }

    public Calendar getReminderCalendar() {
        return mAlarm.getCalendar();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}