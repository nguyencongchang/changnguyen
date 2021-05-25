package com.example.restaurant.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.restaurant.DTO.StaffDTO;
import com.example.restaurant.Database.CreateDatabase;

import java.util.ArrayList;
import java.util.List;


public class StaffDAO {
    SQLiteDatabase database ;
    public StaffDAO(Context context) {

        CreateDatabase createDatabase = new CreateDatabase( context);
        database = createDatabase.open();
    }
    public long addStuff(StaffDTO stuff){

        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_STAFF_USERNAME, stuff.getUsername());
        contentValues.put(CreateDatabase.TB_STAFF_PASSWD, stuff.getPassword());
        contentValues.put(CreateDatabase.TB_STAFF_SEX, stuff.getSex());
        contentValues.put(CreateDatabase.TB_STAFF_BIRTH, stuff.getDateOfBirth());
        contentValues.put(CreateDatabase.TB_STAFF_IDEN, stuff.getIden());

        long check = database.insert(CreateDatabase.TB_STAFF, null, contentValues);
        System.out.println(check);
        return check;

    }
    public List<StaffDTO> getAllStaff(){
        List<StaffDTO> list = new ArrayList<StaffDTO>();

        String q = "SELECT * FROM " + CreateDatabase.TB_STAFF;
        Cursor cursor = database.rawQuery(q, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int id = cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_STAFF_ID));
            String username = cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_STAFF_USERNAME));
            String password = cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_STAFF_PASSWD));
            String sex= cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_STAFF_SEX));
            String dateOfBirth =cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_STAFF_BIRTH));
            String iden =cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_STAFF_IDEN));
            String number= cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_STAFF_NUMBER));
            String fullName = cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_STAFF_FULLNAME));
            String avatar = cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_STAFF_AVATAR));
            StaffDTO staff = new StaffDTO(username,password, sex,dateOfBirth,iden,number,fullName,avatar);
            list.add(staff);
            cursor.moveToNext();
        }
        return list;
    }
    public boolean hasStuff(){
        String query = "SELECT * FROM "+ CreateDatabase.TB_STAFF;
        Cursor cursor = database.rawQuery(query, null);
        if(cursor.getCount() !=0)
            return true;
        else return false;
    }
    public int checkStuff(String u, String p){
        int staffId = 0;
        String query = " SELECT * FROM "+ CreateDatabase.TB_STAFF+" WHERE "+ CreateDatabase.TB_STAFF_USERNAME+" = '"+ u +"' AND " + CreateDatabase.TB_STAFF_PASSWD + " = '"+ p+"'";
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            staffId = cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_STAFF_ID));
            cursor.moveToNext();
        }
        return staffId;
    }

}
