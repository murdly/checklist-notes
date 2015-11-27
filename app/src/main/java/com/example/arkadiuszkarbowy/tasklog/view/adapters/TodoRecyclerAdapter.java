package com.example.arkadiuszkarbowy.tasklog.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.arkadiuszkarbowy.tasklog.view.adapters.holders.TodoNoteViewHolder;
import com.example.arkadiuszkarbowy.tasklog.R;
import com.example.arkadiuszkarbowy.tasklog.data.Note;
import com.example.arkadiuszkarbowy.tasklog.events.NoteDeletedEvent;
import com.example.arkadiuszkarbowy.tasklog.util.BusProvider;

public class TodoRecyclerAdapter extends BaseRecyclerAdapter {

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final int invertedPosition = getItemCount() - position - 1;
        final Note note = mNotes.get(invertedPosition);
        ((TodoNoteViewHolder) holder).setTasks(mContext, note.getTasks(), mListener);
        ((TodoNoteViewHolder) holder).setTimers(note.getDeadline(), note.getReminder());
        setListeners(((TodoNoteViewHolder) holder), note);
    }

    public void setListeners(TodoNoteViewHolder holder, final Note note) {
        holder.setOnDeleteListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BusProvider.getBus().post(new NoteDeletedEvent(note.getId()));
            }
        });
    }

    @Override
    protected RecyclerView.ViewHolder createViewHolder(View v) {
        return new TodoNoteViewHolder(v);
    }

    @Override
    protected int getItemLayout() {
        return R.layout.recycler_item_todo;
    }
}