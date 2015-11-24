package com.example.arkadiuszkarbowy.tasklog.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.arkadiuszkarbowy.tasklog.R;
import com.example.arkadiuszkarbowy.tasklog.di.AndroidApplication;
import com.example.arkadiuszkarbowy.tasklog.data.Note;
import com.example.arkadiuszkarbowy.tasklog.presenters.NotesTodoPresenter;
import com.example.arkadiuszkarbowy.tasklog.view.TodoView;

import java.util.ArrayList;

import javax.inject.Inject;

public class TodoFragment extends TabFragment implements TodoView {

    @Inject
    NotesTodoPresenter mNotesTodoPresenter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initialize();
    }

    private void initialize() {
        AndroidApplication.getComponent(getActivity()).inject(this);
        mNotesTodoPresenter.setView(this);
        mNotesTodoPresenter.loadNoteList();
    }

    @Override
    public void setData(ArrayList<Note> notes) {
        mData = notes;
        setUpRecyclerView();
    }

    @Override
    public void addNew(Note note) {
        mData.add(note);
        mAdapter.notifyDataSetChanged();
        mRecyclerView.scrollToPosition(0);
    }

    @Override
    public void remove(int position) {
        mData.remove(position);
        mAdapter.notifyDataSetChanged();
        mAdapter.notifyItemRangeChanged(0, mData.size());
    }

    @Override
    public void showToastBlankNote() {
        Toast.makeText(getActivity() , getResources().getString(R.string.blank), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mNotesTodoPresenter.onDestroy();
    }
}