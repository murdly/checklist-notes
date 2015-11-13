package com.example.arkadiuszkarbowy.tasklog.note;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.arkadiuszkarbowy.tasklog.AndroidApplication;
import com.example.arkadiuszkarbowy.tasklog.R;
import com.example.arkadiuszkarbowy.tasklog.data.Note;
import com.example.arkadiuszkarbowy.tasklog.data.TasksDataSource;
import com.example.arkadiuszkarbowy.tasklog.util.BusProvider;

import javax.inject.Inject;

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
    @Inject
    TasksDataSource ds;

    private DeadlineAlarmFragment mFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_dialog);
        AndroidApplication.getComponent(this).inject(this);
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
        ds.open();
        Note note = ds.createNote(mTasksLayout.getEntries(), mFragment.getDeadlineCalendar(), mFragment
                .getAlarmCalendar());
        ds.close();

        BusProvider.getBus().post(new NoteCreatedEvent(note));
    }
}
