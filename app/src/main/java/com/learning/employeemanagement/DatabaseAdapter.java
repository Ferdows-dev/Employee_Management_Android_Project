package com.learning.employeemanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

class DatabaseAdapter {
    private DatabaseHelper databaseHelper;

    public DatabaseAdapter(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }


    public void insertIntoDatabase(Employee employee) {

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.NAME, employee.getUserName());
        contentValues.put(DatabaseHelper.EMAIL, employee.getEmail());
        contentValues.put(DatabaseHelper.PHONE, employee.getPhoneNumber());


        sqLiteDatabase.insert(DatabaseHelper.TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();

    }
    public List<Employee> getAllEmployee() {
        List<Employee> employeeList = new ArrayList<>();

        String[] getColumn = {DatabaseHelper.ROW_ID,
                DatabaseHelper.NAME,
                DatabaseHelper.EMAIL,
                DatabaseHelper.PHONE};


                SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(DatabaseHelper.TABLE_NAME, getColumn, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {


                String userName= cursor.getString(cursor.getColumnIndex(DatabaseHelper.NAME));
                String email= cursor.getString(cursor.getColumnIndex(DatabaseHelper.EMAIL));
                String phone= cursor.getString(cursor.getColumnIndex(DatabaseHelper.PHONE));
                int id= cursor.getInt(cursor.getColumnIndex(DatabaseHelper.ROW_ID));

                Employee employee = new Employee(id,userName,email,phone);

                employeeList.add(employee);
            } while (cursor.moveToNext());
        }
        cursor.close();
        sqLiteDatabase.close();
        return employeeList;
    }
    public Boolean updateFromDatabase(Employee employee) {




        SQLiteDatabase database = databaseHelper.getWritableDatabase();




        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.ROW_ID, employee.getId());
        contentValues.put(DatabaseHelper.NAME, employee.getUserName());
        contentValues.put(DatabaseHelper.PHONE, employee.getPhoneNumber());
        contentValues.put(DatabaseHelper.EMAIL, employee.getEmail());


        database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper.ROW_ID + " =?",new String[]{String.valueOf(employee.getId())});
        database.close();
//        if (updatedRow >0)
//            return true;
//        else  return false;


        return null;
    }


}
