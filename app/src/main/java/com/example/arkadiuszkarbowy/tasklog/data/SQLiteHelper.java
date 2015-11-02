package com.example.arkadiuszkarbowy.tasklog.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by arkadiuszkarbowy on 28/10/15.
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_NOTES = "notes";
    public static final String COLUMN_ID_NOTE = "_id";
    public static final String COLUMN_TYPE = "type";  //todoo, done
    public static final String COLUMN_DEADLINE = "deadline";
    public static final String COLUMN_REMINDER = "reminder";

    public static final String TABLE_TASKS = "tasks";
    public static final String COLUMN_ID_TASK = "_id";
    public static final String COLUMN_FRID_NOTE = "fidNote";
    public static final String COLUMN_TEXT = "text";
    public static final String COLUMN_ISDONE= "isdone";


    public static String[] allColumnsNotes = { COLUMN_ID_NOTE, COLUMN_TYPE, COLUMN_DEADLINE,
            COLUMN_REMINDER};

    public static String[] allColumnsTasks = { COLUMN_ID_TASK, COLUMN_FRID_NOTE, COLUMN_TEXT, COLUMN_ISDONE };

    private static final String DATABASE_NAME = "tasknotes.db";
    private static final int DATABASE_VERSION = 9;

    private static final String CREATE_TABLE_NOTES = "create table "
            + TABLE_NOTES + "(" +
            COLUMN_ID_NOTE + " integer primary key autoincrement, " +
            COLUMN_TYPE + " text, " +
            COLUMN_DEADLINE + " integer, " +
            COLUMN_REMINDER + " integer);";

    private static final String CREATE_TABLE_TASKS = "create table "
            + TABLE_TASKS + "(" +
            COLUMN_ID_TASK + " integer primary key autoincrement, " +
            COLUMN_TEXT + " text not null, " +
            COLUMN_ISDONE + " integer, " +
            COLUMN_FRID_NOTE + " integer," +
            " FOREIGN KEY ("+COLUMN_FRID_NOTE+") REFERENCES "+TABLE_TASKS+"("+COLUMN_ID_NOTE+"));";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_TABLE_NOTES);
        database.execSQL(CREATE_TABLE_TASKS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(SQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
        onCreate(db);
    }
}
/*
        _idNote....type.......Deadline.......reminder.....
        0            0          3453534/-1     4564336/-1
        1             1         3453534         4564336
        2             1         3453534         4564336
        3             0       3453534/-1     4564336/-1
        */

/* notes
        idTask...fNote.......text..........isDOne
        1          1         sdsdds.........1
        2           1        dosdsdsdne......0
        3           2        dosdsdsdne.....1


 */