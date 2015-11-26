package com.example.arkadiuszkarbowy.tasklog.notifications;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

/**
 * Created by arkadiuszkarbowy on 26/11/15.
 */
public class ReminderTask implements Runnable{
    private final Calendar date;
    private final AlarmManager am;
    private final Context context;

    public ReminderTask(Context context, Calendar date) {
        this.context = context;
        this.am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        this.date = date;
    }

    @Override
    public void run() {
        Intent intent = new Intent(context, NotifyService.class);
        intent.putExtra(NotifyService.INTENT_NOTIFY, true);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, 0);

        //todo alarm lost on turn off->on
        am.set(AlarmManager.RTC, date.getTimeInMillis(), pendingIntent);
    }
}
