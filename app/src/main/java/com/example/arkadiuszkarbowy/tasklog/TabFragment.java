package com.example.arkadiuszkarbowy.tasklog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.arkadiuszkarbowy.tasklog.data.Note;
import com.example.arkadiuszkarbowy.tasklog.data.TasksDataSource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TabFragment extends Fragment {
    private static final int GRID_COLUMNS = 2;
    public final static String TAB_TYPE_KEY = "TabFragment$TabType";
    public static final int TAB_TODO = 0;
    public static final int TAB_DONE = 1;

    @Inject
    TasksDataSource ds;
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @Bind(R.id.emptyView)
    TextView mEmptyView;


    public static TabFragment createInstance(int tabType) {
        TabFragment tabFragment = new TabFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(TAB_TYPE_KEY, tabType);
        tabFragment.setArguments(bundle);
        return tabFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        AndroidApplication.getComponent(getActivity()).inject(this);

//        createMockups();

        List<Note> data = createItemList();
        if (data.isEmpty()) showEmptyView();
        else setupRecyclerView(data);
    }

    private void setupRecyclerView(List<Note> data) {
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getActivity(), data);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(GRID_COLUMNS, StaggeredGridLayoutManager
                .VERTICAL);
//        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(recyclerAdapter);
    }

    private void showEmptyView() {
        mRecyclerView.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.VISIBLE);
    }

    private List<Note> createItemList() {
        ds.open();
        List<Note> itemList = new ArrayList<>();
        Bundle bundle = getArguments();
        if (bundle != null) {
            int type = bundle.getInt(TAB_TYPE_KEY);
            if (type == TAB_TODO)
                itemList = ds.getTodoNotes();
            else
                itemList = ds.getDoneTasks();
        }
        ds.close();
        return itemList;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void createMockups() {
        ds.open();
        ArrayList<String> tasks = new ArrayList<>();
        tasks.add("zadanie1sfsdfsdfsdfsdfsdfsdfs");
        tasks.add("zadanie11");
        ds.createNote(new Date(34534535), new Date(345342535), tasks);

        ArrayList<String> tasks2 = new ArrayList<>();
        tasks2.add("zadanie3");
        ds.createNote(new Date(34453535), new Date(344534255), tasks2);

        ArrayList<String> tasks3 = new ArrayList<>();
        tasks3.add
                ("zadanie4444444dfdsfsdfsdfsdfsdfsdfsdfsdfsdfsdfzadanie4444444dfdsfsdfsdfsdfsdfsdfsdfsdfsdfsdfzadanie4444444dfdsfsdfsdfsdfsdfsdfsdfsdfsdfsdf");
        ds.createNote(new Date(34453535), new Date(344534255), tasks3);
        ds.close();
    }
}
