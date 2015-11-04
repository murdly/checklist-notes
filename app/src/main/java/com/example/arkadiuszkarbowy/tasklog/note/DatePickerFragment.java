package com.example.arkadiuszkarbowy.tasklog.note;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

import java.util.Calendar;

/**
 * Created by arkadiuszkarbowy on 04/11/15.
 */
public class DatePickerFragment extends DialogFragment {
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    private Calendar mCurrentCalendar;

    public DatePickerFragment newInstance(DatePickerDialog.OnDateSetListener onDateSetListener, Calendar calendar) {
        DatePickerFragment pickerFragment = new DatePickerFragment();
        pickerFragment.setOnDateSetListener(onDateSetListener);
        pickerFragment.setCurrentCalendar(calendar);
        return pickerFragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int year = mCurrentCalendar.get(Calendar.YEAR);
        int month = mCurrentCalendar.get(Calendar.MONTH);
        int day = mCurrentCalendar.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), onDateSetListener, year, month, day);
    }

    private void setOnDateSetListener(DatePickerDialog.OnDateSetListener listener) {
        this.onDateSetListener = listener;
    }

    private void setCurrentCalendar(Calendar calendar){
        mCurrentCalendar = calendar;
    }
}

