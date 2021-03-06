package com.wieland.www.scheduletest.schedule;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.ResultSet;

/**
 * Created by wulka on 20.06.2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "schedule.db";
    public static final String TABLE_NAME = "day";
    public String COL_1 = "kl";
    public String COL_2 = "std";
    public String COL_3 = "fach";
    public String COL_4 = "raum";
    public String COL_5 = "vlehrer";
    public String COL_6 = "vfach";
    public String COL_7 = "vraum";
    public String COL_8 = "info";

    private boolean teacherMode = true;

    /**
     * creates a database
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

        if (teacherMode) {
            COL_1 = "kl";
            COL_2 = "std";
            COL_3 = "fach";
            COL_4 = "raum";
            COL_5 = "vlehrer";
            COL_6 = "vfach";
            COL_7 = "vraum";
            COL_8 = "info";
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists " + TABLE_NAME + 1 + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_1 + " TEXT,"
                + COL_2 + " TEXT,"
                + COL_3 + " TEXT,"
                + COL_4 + " TEXT,"
                + COL_5 + " TEXT,"
                + COL_6 + " TEXT,"
                + COL_7 + " TEXT,"
                + COL_8 + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(int tablenumber, String kl, String std, String fach, String raum, String vlehrer, String vfach, String vraum, String info) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, kl);
        contentValues.put(COL_2, std);
        contentValues.put(COL_3, fach);
        contentValues.put(COL_4, raum);
        contentValues.put(COL_5, vlehrer);
        contentValues.put(COL_6, vfach);
        contentValues.put(COL_7, vraum);
        contentValues.put(COL_8, info);

        long result;

        if (teacherMode) {
            db.execSQL("create table if not exists " + TABLE_NAME + tablenumber + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COL_1 + " TEXT,"
                    + COL_2 + " TEXT,"
                    + COL_3 + " TEXT,"
                    + COL_4 + " TEXT,"
                    + COL_5 + " TEXT,"
                    + COL_6 + " TEXT,"
                    + COL_7 + " TEXT,"
                    + COL_8 + " TEXT)");
        } else {
            //change collumn names

            db.execSQL("create table if not exists " + TABLE_NAME + tablenumber + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COL_1 + " TEXT,"
                    + COL_2 + " TEXT,"
                    + COL_3 + " TEXT,"
                    + COL_4 + " TEXT,"
                    + COL_5 + " TEXT,"
                    + COL_6 + " TEXT,"
                    + COL_7 + " TEXT,"
                    + COL_8 + " TEXT)");
        }

        result = db.insert(TABLE_NAME + tablenumber, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }
}
