package com.example.reminderapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    private static final int VERSION = 2;
    private static final String NAME = "reminder_List_Database";
    private static final String REMINDER_TABLE = "reminder";
    private static final String ID = "id";
    private static final String REMINDER_NAME = "name";
    private static final String DATE = "date";
    private static final String CREATE_REMINDER_TABLE = "CREATE TABLE " + REMINDER_TABLE + "(" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + REMINDER_NAME + " TEXT, " + DATE + " TEXT)";
    private SQLiteDatabase db;

    public Database(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_REMINDER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + REMINDER_TABLE);
        onCreate(db);
    }

    public void openDatabase() {
        db = this.getWritableDatabase();
    }

    public void deleteReminder(int id) {
        db.delete(REMINDER_TABLE, ID +"= ?", new String[] {String.valueOf(id)});
    }

    public void addReminder(Reminder reminder) {
        ContentValues cv = new ContentValues();
        cv.put(REMINDER_NAME, reminder.getName());
        cv.put(DATE, reminder.getDate());
        db.insert(REMINDER_TABLE, null, cv);
    }

    public List<Reminder> getReminderList() {
        List<Reminder> reminderList = new ArrayList<>();
        Cursor cursor = null;
        db.beginTransaction();
        try {
            cursor = db.query(REMINDER_TABLE, null, null, null, null, null, null, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        Reminder newReminder = new Reminder();
                        newReminder.setName(cursor.getString(cursor.getColumnIndexOrThrow(REMINDER_NAME)));
                        newReminder.setDate(cursor.getString(cursor.getColumnIndexOrThrow(DATE)));
                        newReminder.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ID)));
                    }
                    while (cursor.moveToNext());
                }
            }
        }
        finally {
            db.endTransaction();
            assert (cursor != null);
            cursor.close();
        }
        return reminderList;
    }
}
