package com.example.arkadiuszkarbowy.tasklog.in;

import android.content.Context;

import com.example.arkadiuszkarbowy.tasklog.view.activities.CreateNoteDialogActivity;
import com.example.arkadiuszkarbowy.tasklog.scopes.PerApp;
import com.example.arkadiuszkarbowy.tasklog.view.fragments.DoneFragment;
import com.example.arkadiuszkarbowy.tasklog.view.fragments.TodoFragment;
import com.squareup.otto.Bus;

import dagger.Component;

/**
 * Created by arkadiuszkarbowy on 28/10/15.
 */
@PerApp
@Component(
        modules = {
                ApplicationModule.class,
                DataModule.class
        })
public interface ApplicationComponent {
    void inject(TodoFragment todo);
    void inject(DoneFragment done);
    void inject(CreateNoteDialogActivity activity);

//    Application application();
    Context context();
    Bus bus();
}
