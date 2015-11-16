package com.example.arkadiuszkarbowy.tasklog.view.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.arkadiuszkarbowy.tasklog.R;
import com.example.arkadiuszkarbowy.tasklog.events.NoteCreatedEvent;
import com.example.arkadiuszkarbowy.tasklog.util.BusProvider;
import com.example.arkadiuszkarbowy.tasklog.view.custom.TaskRowLayout;
import com.example.arkadiuszkarbowy.tasklog.view.dialogs.DeadlineAlarmFragment;

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
    TaskRowLayout mTasksLayout;

    private DeadlineAlarmFragment mFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_dialog);
        ButterKnife.bind(this);
        mFragment = new DeadlineAlarmFragment();
        getFragmentManager().beginTransaction().add(R.id.deadlineAlarmContainer, mFragment).commit();
    }

    @OnClick(R.id.nextRow)
    void next() {
        mTasksLayout.addRow();
    }

    @Override
    protected void onStop() {
        super.onStop();

        NoteCreatedEvent event = new NoteCreatedEvent(mTasksLayout.getEntries(), mFragment.getDeadlineCalendar(),
                mFragment.getAlarmCalendar());
        BusProvider.getBus().post(event);
    }
}
