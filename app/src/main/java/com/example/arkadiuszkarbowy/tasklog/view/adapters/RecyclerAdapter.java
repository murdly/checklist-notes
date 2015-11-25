package com.example.arkadiuszkarbowy.tasklog.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.arkadiuszkarbowy.tasklog.view.adapters.holders.NoteViewHolder;
import com.example.arkadiuszkarbowy.tasklog.R;
import com.example.arkadiuszkarbowy.tasklog.data.Note;
import com.example.arkadiuszkarbowy.tasklog.events.NoteDeletedEvent;
import com.example.arkadiuszkarbowy.tasklog.util.BusProvider;
import com.example.arkadiuszkarbowy.tasklog.view.interactors.OnTaskInteractionListener;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Note> mNotes;
    private Context mContext;

    private OnTaskInteractionListener mListener;

    public RecyclerAdapter() {
    }

    public void setNotes(List<Note> notes) {
        mNotes = notes;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View v = LayoutInflater.from(mContext).inflate(R.layout.recycler_item, parent, false);
        return new NoteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        onBindListTaskViewHolder((NoteViewHolder) viewHolder, position);
    }

    private void onBindListTaskViewHolder(NoteViewHolder holder, final int position) {
        final int invertedPosition = getItemCount() - position - 1;
        final Note note = mNotes.get(invertedPosition);
        holder.setTasks(mContext, note.getTasks(), mListener);
        holder.setTimers(note.getDeadline(), note.getReminder());
        setListeners(holder, note);


    }

    public void setListeners(NoteViewHolder holder, final Note note){
        holder.setOnDeleteListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BusProvider.getBus().post(new NoteDeletedEvent(note.getId()));
            }
        });
    }

    public void setOnTaskInteractionListener(OnTaskInteractionListener listener){
        mListener = listener;
    }

    @Override
    public int getItemCount() {
        return mNotes == null ? 0 : mNotes.size();
    }
}
