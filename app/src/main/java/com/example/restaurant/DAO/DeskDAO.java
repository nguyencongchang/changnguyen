package com.example.restaurant.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.restaurant.DTO.DeskDTO;
import com.example.restaurant.Database.CreateDatabase;

import java.util.ArrayList;
import java.util.List;

public class DeskDAO {
    SQLiteDatabase database;
    public DeskDAO(Context context){
        CreateDatabase createDatabase = new CreateDatabase(context);
        database = createDatabase.open();
    }
    public String getStatusById(int deskId){
        String status = "";
        String query = "SELECT * FROM "+CreateDatabase.TB_TABLE+" WHERE "+CreateDatabase.TB_TABLE_ID+" ='"+deskId+"'";
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            status = cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_TABLE_STATUS));
            cursor.moveToNext();
        }
        return status;

    }
    public boolean deleteDesk(int id){
        long check= database.delete(CreateDatabase.TB_TABLE,CreateDatabase.TB_TABLE_ID+" = "+id, null);
        if(check>0){
            return true;
        }
        return false;
    }
    public boolean AddDesk(String name){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_TABLE_NAME,name);
        contentValues.put(CreateDatabase.TB_TABLE_STATUS,"false");
        long check = database.insert(CreateDatabase.TB_TABLE,null, contentValues);
        if(check>0) {
            return true;
        }
        else {
            return false;
        }
    }
    public List<DeskDTO> getAllDesk(){
        List<DeskDTO> listDesk = new ArrayList<DeskDTO>();
        String sql = "SELECT * FROM "+CreateDatabase.TB_TABLE;
        Cursor cursor = database.rawQuery(sql,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            DeskDTO desk = new DeskDTO();
            desk.setId(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_TABLE_ID)));
            desk.setName(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_TABLE_NAME)));
//            desk.setSelected(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_TABLE_STATUS)));
            listDesk.add(desk);
            cursor.moveToNext();
        }
        return listDesk;
    }
    public void updateStatus(int deskId, String status){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_TABLE_STATUS,status);
        database.update(CreateDatabase.TB_TABLE,contentValues, CreateDatabase.TB_TABLE_ID+ " = '"+deskId+"'",null );
    }
    public void modifyName(int id, String name){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_TABLE_NAME,name);
        database.update(CreateDatabase.TB_TABLE,contentValues, CreateDatabase.TB_TABLE_ID+ " = '"+id+"'",null );
    }
}
