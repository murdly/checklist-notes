package com.example.arkadiuszkarbowy.tasklog.util;

import android.database.Cursor;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by arkadiuszkarbowy on 29/10/15.
 */
public class Conversion {

    public static Long persistDate(Calendar calendar) {
        if (calendar != null) {
            return calendar.getTime().getTime();
        }
        return -1L;
    }

    public static Date loadDate(Cursor cursor, int index) {
        if (cursor.isNull(index)) {
            return null;
        }
        long ms = cursor.getLong(index);
        return ms != -1L ? new Date(ms) : null;
    }
}
