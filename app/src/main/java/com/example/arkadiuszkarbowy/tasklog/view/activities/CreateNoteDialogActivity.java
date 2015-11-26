package com.example.arkadiuszkarbowy.tasklog.view.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.arkadiuszkarbowy.tasklog.R;
import com.example.arkadiuszkarbowy.tasklog.events.NoteCreatedEvent;
import com.example.arkadiuszkarbowy.tasklog.notifications.ScheduleClient;
import com.example.arkadiuszkarbowy.tasklog.util.BusProvider;
import com.example.arkadiuszkarbowy.tasklog.view.custom.TaskRowLayout;
import com.example.arkadiuszkarbowy.tasklog.view.fragments.dialogs.DeadlineAlarmFragment;

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
    private ScheduleClient mScheduleClient;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_dialog);
        ButterKnife.bind(this);
        mScheduleClient = new ScheduleClient(this);
        mScheduleClient.doBindService();
        mFragment = new DeadlineAlarmFragment();
        getFragmentManager().beginTransaction().add(R.id.deadlineAlarmContainer, mFragment).commit();
    }

    @OnClick(R.id.nextRow)
    void next() {
        mTasksLayout.addRow();
    }

    @Override
    protected void onStop() {
        NoteCreatedEvent event = new NoteCreatedEvent(mTasksLayout.getEntries(),
                mFragment.getDeadlineCalendar(), mFragment.getReminderCalendar());
        BusProvider.getBus().post(event);

        if(mFragment.getReminderCalendar() != null)
            mScheduleClient.setAlarmForNotification(mFragment.getReminderCalendar());

        if(mScheduleClient != null)
            mScheduleClient.doUnbindService();
        super.onStop();
    }
}
