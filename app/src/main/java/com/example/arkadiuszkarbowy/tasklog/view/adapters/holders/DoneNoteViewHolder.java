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
import com.example.arkadiuszkarbowy.tasklog.view.interactors.OnTaskInteractionListener;

import java.util.ArrayList;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by arkadiuszkarbowy on 18/11/15.
 */
public class DoneNoteViewHolder extends RecyclerView.ViewHolder{

    @Bind(R.id.list)
    ExpandedListView mList;

    public DoneNoteViewHolder(final View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    public void setTasks(Context context, ArrayList<Task> tasks, OnTaskInteractionListener
            mListener) {
        TaskListAdapter tasksAdapter = new TaskListAdapter(context, R.layout.task_list_item, tasks);
        tasksAdapter.setOnTaskInteractionListener(mListener);
        mList.setAdapter(tasksAdapter);
    }
}