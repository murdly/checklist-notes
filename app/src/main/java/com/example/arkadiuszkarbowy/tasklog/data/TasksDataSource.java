package com.example.arkadiuszkarbowy.tasklog.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.arkadiuszkarbowy.tasklog.scopes.PerApp;
import com.example.arkadiuszkarbowy.tasklog.util.Conversion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by arkadiuszkarbowy on 28/10/15.
 */
@PerApp
public class TasksDataSource {

    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;


    @Inject
    public TasksDataSource(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Task createSimpleTask(Date deadline, Date reminder, String text) {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.COLUMN_LISTID, -1);
        values.put(SQLiteHelper.COLUMN_ISDONE, 0);
        values.put(SQLiteHelper.COLUMN_DEADLINE, Conversion.persistDate(deadline));
        values.put(SQLiteHelper.COLUMN_REMINDER, Conversion.persistDate(reminder));
        values.put(SQLiteHelper.COLUMN_TEXT, text);
        long insertId = database.insert(SQLiteHelper.TABLE_TASKS, null,
                values);
        Cursor cursor = database.query(SQLiteHelper.TABLE_TASKS,
                SQLiteHelper.allColumns, SQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Task newTask = cursorToTask(cursor);
        cursor.close();
        return newTask;
    }

    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<Task>();

        Cursor cursor = database.query(SQLiteHelper.TABLE_TASKS,
                SQLiteHelper.allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Task task = cursorToTask(cursor);
            tasks.add(task);
            cursor.moveToNext();
        }

        cursor.close();
        return tasks;
    }

    private Task cursorToTask(Cursor cursor) {
        Task task = new Task();
        task.setId(cursor.getInt(cursor.getColumnIndex(SQLiteHelper.COLUMN_ID)));
        task.setListId(cursor.getInt(cursor.getColumnIndex(SQLiteHelper.COLUMN_LISTID)));
        task.setIsDone(cursor.getInt(cursor.getColumnIndex(SQLiteHelper.COLUMN_ISDONE)));
        task.setDeadline(Conversion.loadDate(cursor, cursor.getColumnIndex(SQLiteHelper.COLUMN_DEADLINE)));
        task.setReminder(Conversion.loadDate(cursor, cursor.getColumnIndex(SQLiteHelper.COLUMN_REMINDER)));
        task.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_TEXT)));
        return task;
    }

    public List<TaskNote> getDoneTasks() {

        Log.d("TaskDatSource", "getDoneTasks");

        return new ArrayList<>();
    }

    public List<TaskNote> getTodoTasks() {
        Log.d("TaskDatSource", "GetTOdoTasks");
        List<TaskNote> notes = new ArrayList<>();
        List<Task> all = getAllTasks();
//        for (Task task : all) {
//            if(task.isSingle())
//                notes.add(new TaskNote(task));
//            else
//                notes.add(groupTasks());
//        }
        notes.add(new TaskNote(all.get(0)));
        notes.add(new TaskNote(all.subList(1, 3)));
        notes.add(new TaskNote(all.get(3)));
        notes.add(new TaskNote(all.subList(4, 7)));

        return notes;
    }


    //    public void deleteComment(TaskNote taskNote) {
//        long id = taskNote.getId();
//        System.out.println("Comment deleted with id: " + id);
//        database.delete(SQLiteHelper.TABLE_COMMENTS, SQLiteHelper.COLUMN_ID
//                + " = " + id, null);
//    }

//    public Comment getMyPlaceById(long id) {
//        System.out.println("Got MyPlace with id: " + id);
//        Cursor cursor = mDatabase.query(SQLiteHelper.TABLE_PLACES, allColumns, SQLiteHelper.COLUMN_ID
//                + " = " + id, null, null, null, null, null);
//        cursor.moveToFirst();
//        Comment place = cursorToPlace(cursor);
//        cursor.close();
//        return place;
//    }
//
//
//
//    public void deleteMyPlaceById(long id) {
//        System.out.println("MyPlace deleted with id: " + id);
//        mDatabase.delete(SQLiteHelper.TABLE_PLACES, SQLiteHelper.COLUMN_ID
//                + " = " + id, null);
//    }


}