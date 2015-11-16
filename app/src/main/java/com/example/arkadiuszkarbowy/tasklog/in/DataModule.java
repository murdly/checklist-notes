package com.example.arkadiuszkarbowy.tasklog.in;

import android.content.Context;

import com.example.arkadiuszkarbowy.tasklog.data.TasksDataSource;
import com.example.arkadiuszkarbowy.tasklog.scopes.PerApp;

import dagger.Module;
import dagger.Provides;

/**
 * Created by arkadiuszkarbowy on 28/10/15.
 */
@Module
public class DataModule {

    @Provides @PerApp
    TasksDataSource provideTasksDataSource(Context c){
        return new TasksDataSource(c);
    }

}
