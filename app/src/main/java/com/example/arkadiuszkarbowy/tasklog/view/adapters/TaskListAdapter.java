package com.example.arkadiuszkarbowy.tasklog.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.arkadiuszkarbowy.tasklog.R;
import com.example.arkadiuszkarbowy.tasklog.data.Task;
import com.example.arkadiuszkarbowy.tasklog.view.interactors.OnTaskInteractionListener;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by arkadiuszkarbowy on 02/11/15.
 */
public class TaskListAdapter extends ArrayAdapter<Task>{

    private OnTaskInteractionListener mTaskInteractionListener;

    public TaskListAdapter(Context context, int layout, ArrayList<Task> tasks) {
        super(context, layout, tasks);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TaskViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_list_item, parent, false);
            holder = new TaskViewHolder(convertView);
            convertView.setTag(holder);
        }
        {
            holder = (TaskViewHolder) convertView.getTag();
        }

        Task task = getItem(position);
        holder.mBox.setChecked(task.isDone());
        holder.mText.setText(task.getText());
        setListeners(holder, task.getId());

        return convertView;

    }

    private void setListeners(TaskViewHolder holder, final long taskId) {
        holder.mBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    mTaskInteractionListener.onChecked(taskId);
                else
                    mTaskInteractionListener.onUnchecked(taskId);
            }
        });
    }

    public void setOnTaskInteractionListener(OnTaskInteractionListener listener) {
        mTaskInteractionListener = listener;
    }

    public static class TaskViewHolder {
        @Bind(R.id.checkBox)
        CheckBox mBox;
        @Bind(R.id.text)
        TextView mText;

        public TaskViewHolder(View v) {
            ButterKnife.bind(this, v);
        }
    }
}
