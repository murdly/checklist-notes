package com.example.arkadiuszkarbowy.tasklog.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.arkadiuszkarbowy.tasklog.R;
import com.example.arkadiuszkarbowy.tasklog.di.AndroidApplication;
import com.example.arkadiuszkarbowy.tasklog.data.Note;
import com.example.arkadiuszkarbowy.tasklog.events.NoteCreatedEvent;
import com.example.arkadiuszkarbowy.tasklog.events.NoteDeletedEvent;
import com.example.arkadiuszkarbowy.tasklog.presenters.NotesTodoPresenter;
import com.example.arkadiuszkarbowy.tasklog.util.BusProvider;
import com.example.arkadiuszkarbowy.tasklog.view.TodoView;
import com.example.arkadiuszkarbowy.tasklog.view.adapters.RecyclerAdapter;
import com.example.arkadiuszkarbowy.tasklog.view.adapters.TaskListAdapter;
import com.example.arkadiuszkarbowy.tasklog.view.interactors.OnTaskInteractionListener;
import com.squareup.otto.Subscribe;

import java.util.List;

import javax.inject.Inject;

public class TodoFragment extends TabFragment implements TodoView {

    @Inject
    NotesTodoPresenter mTodoPresenter;
    protected RecyclerAdapter mTodoAdapter;

    public TodoFragment(){
        mTodoAdapter = new RecyclerAdapter();
        BusProvider.getBus().register(this);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initialize();
    }

    private void initialize() {
        AndroidApplication.getComponent(getActivity()).inject(this);
        mTodoPresenter.setView(this);
        mTodoPresenter.loadNoteList();
        mTodoAdapter.setOnTaskInteractionListener(mTaskListener);
    }

    private OnTaskInteractionListener mTaskListener = new OnTaskInteractionListener() {
        @Override
        public void onChecked(long id) {
            mTodoPresenter.taskChecked(id);
        }

        @Override
        public void onUnchecked(long id) {
            mTodoPresenter.taskUnchecked(id);

        }
    };

    @Override
    public void setData(List<Note> notes) {
        mTodoAdapter.setNotes(notes);
        setUpRecyclerView();
    }

    @Subscribe
    public void noteInserted(NoteCreatedEvent event) {
        mTodoPresenter.noteInserted(event);
    }

    @Subscribe
    public void noteDeleted(NoteDeletedEvent event) {
        mTodoPresenter.noteDeleted(event);
    }

    @Override
    public void addNote() {
        mTodoAdapter.notifyDataSetChanged();
        mRecyclerView.scrollToPosition(0);
    }

    @Override
    public void remove(int position) {
        mTodoAdapter.notifyItemRemoved(position);
        mTodoAdapter.notifyDataSetChanged();
    }

    @Override
    public void showToastBlankNote() {
        Toast.makeText(getActivity() , getResources().getString(R.string.blank), Toast.LENGTH_SHORT).show();
    }

    @Override
    public RecyclerAdapter getAdapter() {
        return mTodoAdapter;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        BusProvider.getBus().unregister(this);
        mTodoPresenter.onDestroy();
    }

}