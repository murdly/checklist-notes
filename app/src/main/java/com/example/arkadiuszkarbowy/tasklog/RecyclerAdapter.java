package com.example.arkadiuszkarbowy.tasklog;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.example.arkadiuszkarbowy.tasklog.data.Task;
import com.example.arkadiuszkarbowy.tasklog.data.TaskNote;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<TaskNote> mTaskNotes;
    private int mType;

    public RecyclerAdapter(List<TaskNote> itemList) {
        mTaskNotes = itemList;
    }

    @Override
    public int getItemViewType(int position) {
        mType = mTaskNotes.get(position).getType();
        return mType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TaskNote.SIMPLE_TASK_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
            return new SimpleTaskViewHolder(v);
        }

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_list_item, parent, false);
        return new ListTaskViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (mType == TaskNote.SIMPLE_TASK_ITEM)
            onBindSimpleTaskViewHolder((SimpleTaskViewHolder) viewHolder, position);
        else if (mType == TaskNote.LIST_TASK_ITEM)
            onBindListTaskViewHolder((ListTaskViewHolder) viewHolder, position);
    }

    private void onBindSimpleTaskViewHolder(SimpleTaskViewHolder holder, int position) {
        TaskNote note = mTaskNotes.get(position);
        holder.mSimpleText.setText(note.getTaskContent());
    }

    private void onBindListTaskViewHolder(ListTaskViewHolder holder, int position) {
        TaskNote listNote = mTaskNotes.get(position);
        for(Task task: listNote.getTaskList()){
            holder.listText.setText(task.getText());
        }
    }

    @Override
    public int getItemCount() {
        return mTaskNotes == null ? 0 : mTaskNotes.size();
    }


    public static class SimpleTaskViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.simpleText)
        TextView mSimpleText;

        public SimpleTaskViewHolder(final View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public static class ListTaskViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.listText)
        CheckedTextView listText;

        public ListTaskViewHolder(final View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
