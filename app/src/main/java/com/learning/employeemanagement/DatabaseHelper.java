package com.learning.employeemanagement;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

class DatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Employee_system.db";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public static final String TABLE_NAME = "employee_info_table";
    public static final String ROW_ID = "id";
    public static final String NAME = "employee_name";
    public static final String PHONE = "employee_phone";
    public static final String EMAIL = "employee_email";


    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL, "
            + NAME + " TEXT, "
            + PHONE + " TEXT, "
            + EMAIL + " TEXT" + ")";




    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
