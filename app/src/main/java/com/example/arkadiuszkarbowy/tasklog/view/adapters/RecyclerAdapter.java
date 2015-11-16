package com.example.arkadiuszkarbowy.tasklog.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.arkadiuszkarbowy.tasklog.view.custom.ExpandedListView;
import com.example.arkadiuszkarbowy.tasklog.R;
import com.example.arkadiuszkarbowy.tasklog.data.Note;
import com.example.arkadiuszkarbowy.tasklog.data.Task;
import com.example.arkadiuszkarbowy.tasklog.events.NoteDeletedEvent;
import com.example.arkadiuszkarbowy.tasklog.util.BusProvider;

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

    private void onBindListTaskViewHolder(NoteViewHolder holder, final int position) {
        final int invertedPosition = getItemCount() - position - 1;
       final Note note = mNotes.get(invertedPosition);
//        for(Task task : note.getTasks()){
//            holder.listText.setText(task.getText());
//        }
        holder.setTasks(mContext, note.getTasks());
        holder.mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BusProvider.getBus().post(new NoteDeletedEvent(note.getId(), invertedPosition));
            }
        });

    }

    @Override
    public int getItemCount() {
        return mNotes == null ? 0 : mNotes.size();
    }



    public static class NoteViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.list)
        ExpandedListView list;
        @Bind(R.id.deleteNote)
        ImageView mDelete;

        public NoteViewHolder(final View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void setTasks(Context context, ArrayList<Task> tasks) {
            list.setAdapter(new TaskListAdapter(context, R.layout.task_list_item, tasks));
        }
    }
}
