package com.example.arkadiuszkarbowy.tasklog.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.arkadiuszkarbowy.tasklog.R;
import com.example.arkadiuszkarbowy.tasklog.data.Note;
import com.example.arkadiuszkarbowy.tasklog.events.NoteDeletedEvent;
import com.example.arkadiuszkarbowy.tasklog.util.BusProvider;
import com.example.arkadiuszkarbowy.tasklog.view.adapters.holders.TodoNoteViewHolder;
import com.example.arkadiuszkarbowy.tasklog.view.interactors.OnTaskInteractionListener;

import java.util.List;

public abstract class BaseRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected Context mContext;
    protected List<Note> mNotes;

    protected OnTaskInteractionListener mListener;

    public void setNotes(List<Note> notes) {
        mNotes = notes;
    }

    public void setOnTaskInteractionListener(OnTaskInteractionListener listener){
        mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View v = LayoutInflater.from(mContext).inflate(getItemLayout(), parent, false);
        return createViewHolder(v);
    }

    protected abstract RecyclerView.ViewHolder createViewHolder(View v);

    protected abstract int getItemLayout();

    @Override
    public int getItemCount() {
        return mNotes == null ? 0 : mNotes.size();
    }
}