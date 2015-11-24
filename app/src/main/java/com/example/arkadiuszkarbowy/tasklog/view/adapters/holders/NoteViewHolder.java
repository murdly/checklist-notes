package com.example.arkadiuszkarbowy.tasklog.view.adapters.holders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.arkadiuszkarbowy.tasklog.R;
import com.example.arkadiuszkarbowy.tasklog.data.Task;
import com.example.arkadiuszkarbowy.tasklog.view.adapters.TaskListAdapter;
import com.example.arkadiuszkarbowy.tasklog.view.custom.ExpandedListView;

import java.util.ArrayList;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by arkadiuszkarbowy on 18/11/15.
 */
public class NoteViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.list)
    ExpandedListView mList;
    @Bind(R.id.deleteNote)
    ImageView mDelete;
    @Bind(R.id.timersContainer)
    LinearLayout mContainer;
    @Bind(R.id.deadlineLayout)
    LinearLayout mDeadlineLayout;
    @Bind(R.id.reminderLayout)
    LinearLayout mReminderLayout;

    private DateTimeViewHolder mDeadlineHolder;
    private DateTimeViewHolder mReminderHolder;

    public NoteViewHolder(final View view) {
        super(view);
        ButterKnife.bind(this, view);
        mDeadlineHolder = new DateTimeViewHolder(mDeadlineLayout);
        mReminderHolder = new DateTimeViewHolder(mReminderLayout);
    }

    public void setTasks(Context context, ArrayList<Task> tasks) {
        mList.setAdapter(new TaskListAdapter(context, R.layout.task_list_item, tasks));
    }

    public void setTimers(Date deadline, Date reminder){
        boolean visible = deadline != null || reminder != null;
            showContainer(visible);

        setDeadline(deadline);
        setReminder(reminder);
    }

    private void showContainer(boolean visible) {
        mContainer.setVisibility(visible ? View.VISIBLE :View.GONE);
    }

    private void setDeadline(Date deadline) {
        if (deadline != null) {
            mDeadlineLayout.setVisibility(View.VISIBLE);
            mDeadlineHolder.setIcon(R.drawable.ic_deadline_light);
            mDeadlineHolder.setDateTime(deadline);
        } else {
            mDeadlineLayout.setVisibility(View.GONE);
        }
    }


    private void setReminder(Date reminder) {
        if (reminder != null) {
            mReminderLayout.setVisibility(View.VISIBLE);
            mReminderHolder.setIcon(R.drawable.ic_reminder_light);
            mReminderHolder.setDateTime(reminder);
        } else {
            mReminderLayout.setVisibility(View.GONE);
        }
    }

    public void setOnDeleteListener(View.OnClickListener onDeleteListener) {
        mDelete.setOnClickListener(onDeleteListener);
    }
}