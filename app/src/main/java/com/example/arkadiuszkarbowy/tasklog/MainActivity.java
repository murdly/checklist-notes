package com.example.arkadiuszkarbowy.tasklog;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.arkadiuszkarbowy.tasklog.note.CreateNoteDialogActivity;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;
/**
 * Created by arkadiuszkarbowy on 28/10/15.
 */
public class MainActivity extends AppCompatActivity {

    @Bind(R.id.viewPager) ViewPager mViewPager;
    @Bind(R.id.tabLayout) TabLayout mTabLayout;
    @BindString(R.string.todo) String mTodo;
    @BindString(R.string.done) String mDone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                new CreateNoteDialog().newInstance().show(getFragmentManager(), "create");
                Intent i = new Intent(MainActivity.this, CreateNoteDialogActivity.class);
                startActivity(i);
            }
        });

        initViewPagerAndTabs();
    }


    private void initViewPagerAndTabs() {
        MyPagerAdapter pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(TabFragment.createInstance(TabFragment.TAB_TODO), mTodo);
        pagerAdapter.addFragment(TabFragment.createInstance(TabFragment.TAB_DONE), mDone);
        mViewPager.setAdapter(pagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
