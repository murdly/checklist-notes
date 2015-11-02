package com.example.arkadiuszkarbowy.tasklog;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.arkadiuszkarbowy.tasklog.data.Note;
import com.example.arkadiuszkarbowy.tasklog.data.Task;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Note> mNotes;
    private int mType;
    private Context mContext;

    public RecyclerAdapter(Context context, List<Note> itemList) {
        mContext = context;
        mNotes = itemList;
    }

//    @Override
//    public int getItemViewType(int position) {
//        mType = mNotes.get(position).getType();
//        return mType;
//    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        if (viewType == TaskNote.SIMPLE_TASK_ITEM) {
//            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
//            return new SimpleTaskViewHolder(v);
//        }

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new NoteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
//        if (mType == TaskNote.SIMPLE_TASK_ITEM)
//            onBindSimpleTaskViewHolder((SimpleTaskViewHolder) viewHolder, position);
//        else if (mType == TaskNote.LIST_TASK_ITEM)
        onBindListTaskViewHolder((NoteViewHolder) viewHolder, position);
    }

//    private void onBindSimpleTaskViewHolder(SimpleTaskViewHolder holder, int position) {
//        TaskNote note = mNotes.get(position);
//        holder.mSimpleText.setText(note.getTaskContent());
//    }

    private void onBindListTaskViewHolder(NoteViewHolder holder, int position) {
        Note note = mNotes.get(position);
//        for(Task task : note.getTasks()){
//            holder.listText.setText(task.getText());
//        }
        holder.setTasks(mContext, note.getTasks());


    }

    @Override
    public int getItemCount() {
        return mNotes == null ? 0 : mNotes.size();
    }


    public static class NoteViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.list)
        ExpandedListView list;

        public NoteViewHolder(final View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void setTasks(Context context, ArrayList<Task> tasks) {
            list.setAdapter(new TaskListAdapter(context, R.layout.task_list_item, tasks));
        }
    }
}
