package com.example.arkadiuszkarbowy.tasklog.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.arkadiuszkarbowy.tasklog.R;
import com.example.arkadiuszkarbowy.tasklog.data.Note;
import com.example.arkadiuszkarbowy.tasklog.view.adapters.holders.DoneNoteViewHolder;

public class DoneRecyclerAdapter extends BaseRecyclerAdapter {

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final int invertedPosition = getItemCount() - position - 1;
        final Note note = mNotes.get(invertedPosition);
        ((DoneNoteViewHolder) holder).setTasks(mContext, note.getTasks(), mListener);
    }

    @Override
    protected RecyclerView.ViewHolder createViewHolder(View v) {
        return new DoneNoteViewHolder(v);
    }

    @Override
    protected int getItemLayout() {
        return R.layout.recycler_item_done;
    }
}