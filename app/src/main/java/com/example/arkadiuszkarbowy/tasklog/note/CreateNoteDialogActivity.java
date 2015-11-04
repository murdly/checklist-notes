package com.example.arkadiuszkarbowy.tasklog.note;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.arkadiuszkarbowy.tasklog.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by arkadiuszkarbowy on 02/11/15.
 */
public class CreateNoteDialogActivity extends AppCompatActivity {
    @Bind(R.id.nextRow)
    TextView mNextRow;
    @Bind(R.id.taskList)
    TaskRowLinearLayout mTasksLayout;


    private Calendar mDeadlineCalendar, mAlarmCalendar;
    private SimpleDateFormat mDateFormat, mTimeFormat;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_dialog);
        ButterKnife.bind(this);
        mDeadlineCalendar = Calendar.getInstance();
        mAlarmCalendar = Calendar.getInstance();
        mDateFormat = new SimpleDateFormat("EEE, MMM d", Locale.getDefault());
        mTimeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());

        getFragmentManager().beginTransaction().add(R.id.deadlineAlarmContainer, new DeadlineAlarmFragment
                ()).commit();

    }


    @OnClick(R.id.nextRow)
    void next() {
        mTasksLayout.addRow();
    }
}
