package com.example.arkadiuszkarbowy.tasklog.view.adapters.holders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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

    public void setDeadline(Date deadline) {
        if (deadline != null) {
            showContainerIfNotVisibleYet();
            mDeadlineLayout.setVisibility(View.VISIBLE);
            mDeadlineHolder.setIcon(R.drawable.ic_deadline_light);
            mDeadlineHolder.setDateTime(deadline);
        }
    }

    public void setReminder(Date reminder) {
        if (reminder != null) {
            showContainerIfNotVisibleYet();
            mReminderLayout.setVisibility(View.VISIBLE);
            mReminderHolder.setIcon(R.drawable.ic_reminder_light);
            mReminderHolder.setDateTime(reminder);
        }
    }

    public void setOnDeleteListener(View.OnClickListener onDeleteListener) {
        mDelete.setOnClickListener(onDeleteListener);
    }

    private void showContainerIfNotVisibleYet() {
        if(mContainer.getVisibility() != View.VISIBLE)
            mContainer.setVisibility(View.VISIBLE);
    }
}