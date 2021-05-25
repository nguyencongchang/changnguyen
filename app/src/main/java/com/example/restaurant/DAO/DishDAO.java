package com.example.restaurant.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.restaurant.DTO.DeskDTO;
import com.example.restaurant.DTO.DishDTO;
import com.example.restaurant.Database.CreateDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DishDAO {
    SQLiteDatabase sqLiteDatabase;

    public DishDAO(Context context) {
        CreateDatabase createDatabase = new CreateDatabase(context);
        sqLiteDatabase = createDatabase.open();
    }
    public boolean addDish(DishDTO dish){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_DISH_NAME, dish.getName());
        contentValues.put(CreateDatabase.TB_DISH_CAT, dish.getCategory());
        contentValues.put(CreateDatabase.TB_DISH_PRICE, dish.getPrice());
        contentValues.put(CreateDatabase.TB_DISH_IMG, dish.getImageUrl());
        long check = sqLiteDatabase.insert(CreateDatabase.TB_DISH,null, contentValues);
        if(check>0)
            return true;
        return false;
    }
    public List<DishDTO> getAllDish(){
        List<DishDTO> listDish = new ArrayList<DishDTO>();
        String sql = "SELECT  * from "+ CreateDatabase.TB_DISH;
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            DishDTO dish = new DishDTO();
            dish.setId(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_DISH_ID)));
            dish.setName(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_DISH_NAME)));
            dish.setCategory(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_DISH_CAT)));
            dish.setImageUrl(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_DISH_IMG)));
            listDish.add(dish);
            cursor.moveToNext();
        }
        return listDish;
    }

    public List<DishDTO> getDishByCat(int catId){
        List<DishDTO> listDish = new ArrayList<DishDTO>();
        String sql = "SELECT  * from "+ CreateDatabase.TB_DISH + " WHERE "+  CreateDatabase.TB_DISH_CAT+ " = '"+catId+"' ";
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            DishDTO dish = new DishDTO();
            dish.setId(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_DISH_ID)));
            dish.setName(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_DISH_NAME)));
            dish.setCategory(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_DISH_CAT)));
            dish.setPrice(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_DISH_PRICE)));
            dish.setImageUrl(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_DISH_IMG)));
            listDish.add(dish);
            cursor.moveToNext();
        }
        return listDish;
    }

    public DishDTO getDishById(int id){
        String query = "SELECT * FROM "+ CreateDatabase.TB_DISH + " WHERE "+ CreateDatabase.TB_DISH_ID +" = " +id;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        DishDTO dish = new DishDTO();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            dish.setId(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_DISH_ID)));
            dish.setName(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_DISH_NAME)));
            dish.setCategory(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_DISH_CAT)));
            dish.setImageUrl(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_DISH_IMG)));
            cursor.moveToNext();
        }
        return dish;

    }
}
