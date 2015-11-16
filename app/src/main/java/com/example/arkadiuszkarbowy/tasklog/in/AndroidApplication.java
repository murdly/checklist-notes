package com.example.arkadiuszkarbowy.tasklog.in;

import android.app.Application;
import android.content.Context;


/**
 * Created by arkadiuszkarbowy on 28/10/15.
 */
public class AndroidApplication extends Application {
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
    }

    private void initializeInjector() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .dataModule(new DataModule())
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public static ApplicationComponent getComponent(Context context) {
        return ((AndroidApplication) context.getApplicationContext()).applicationComponent;
    }
}
