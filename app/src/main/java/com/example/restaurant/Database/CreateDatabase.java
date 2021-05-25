package com.example.restaurant.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CreateDatabase extends SQLiteOpenHelper {

    public final static String TB_STAFF = "STAFF";
    public final static String TB_DISH = "DISH";
    public final static String TB_CATEGORY = "CATEGORY";
    public final static String TB_TABLE = "DESK";
    public final static String TB_ORDER = "ORDERR";
    public final static String TB_ORDER_DETAIL = "ORDER_DETAIL";

    public final static String TB_STAFF_ID = "ID";
    public final static String TB_STAFF_USERNAME = "USERNAME";
    public final static String TB_STAFF_PASSWD = "PASSWD";
    public final static String TB_STAFF_SEX = "SEX";
    public final static String TB_STAFF_FULLNAME = "FULLNAME";
    public final static String TB_STAFF_NUMBER = "NUMBER";
    public final static String TB_STAFF_AVATAR = "AVATAR";
    public final static String TB_STAFF_BIRTH = "BIRTH";
    public final static String TB_STAFF_IDEN = "IDENTIFICATION";

    public final static String TB_DISH_ID = "ID";
    public final static String TB_DISH_NAME = "NAME";
    public final static String TB_DISH_CAT = "CATEGORY_ID";
    public final static String TB_DISH_PRICE = "PRICE";
    public final static String TB_DISH_IMG = "IMAGE_URL";


    public final static String TB_CATEGORY_ID = "ID";
    public final static String TB_CATEGORY_NAME = "NAME";

    public final static String TB_TABLE_ID = "ID";
    public final static String TB_TABLE_NAME = "NAME";
    public final static String TB_TABLE_STATUS = "STATUS";

    public final static String TB_ORDER_ID = "ID";
    public final static String TB_ORDER_TABLE = "TABLE_ID";
    public final static String TB_ORDER_STAFF = "STAFF_ID";
    public final static String TB_ORDER_STATUS = "STATUS";
    public final static String TB_ORDER_DATE = "DATE";

    public final static String TB_ORDER_DETAIL_ORDER = "ORDER_ID";
    public final static String TB_ORDER_DETAIL_DISH = "DISH_ID";
    public final static String TB_ORDER_DETAIL_AMOUNT = "AMOUNT";

    public CreateDatabase(Context context){
        super(context,"OrderFood",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String tbSTAFF = "CREATE TABLE " + TB_STAFF +" ( "
                + TB_STAFF_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_STAFF_USERNAME+" TEXT, "
                + TB_STAFF_PASSWD+" TEXT, "
                + TB_STAFF_FULLNAME+" TEXT, "
                + TB_STAFF_SEX +" TEXT, "
                + TB_STAFF_BIRTH +" TEXT, "
                + TB_STAFF_NUMBER+" TEXT, "
                + TB_STAFF_AVATAR+" TEXT, "
                + TB_STAFF_IDEN +" TEXT ) ";
        String tbCAT = "CREATE TABLE " + TB_CATEGORY +" ( "
                + TB_CATEGORY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_CATEGORY_NAME+" TEXT )";
        String tbTABLE = "CREATE TABLE " + TB_TABLE +" ( "
                + TB_TABLE_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_TABLE_NAME+" TEXT, "
                + TB_TABLE_STATUS+" TEXT )";
        String tbDISH = "CREATE TABLE " + TB_DISH +" ( "
                + TB_DISH_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_DISH_NAME+" TEXT, "
                + TB_DISH_CAT +" INTEGER,"
                + TB_DISH_PRICE + " INTEGER,"
                + TB_DISH_IMG +" TEXT, FOREIGN KEY ("
                + TB_DISH_CAT+" ) REFERENCES "+ TB_CATEGORY+"("+TB_CATEGORY_ID+")) ";
        String tbORDER = "CREATE TABLE " + TB_ORDER +" ( "
                + TB_ORDER_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_ORDER_TABLE+" INTEGER, "
                + TB_ORDER_STAFF +" INTEGER, "
                + TB_ORDER_STATUS +" TEXT, "
                + TB_ORDER_DATE +" TEXT, " +
                "FOREIGN KEY (" + TB_ORDER_TABLE+" ) REFERENCES "+ TB_TABLE+"("+TB_TABLE_ID+")," +
                "FOREIGN KEY (" + TB_ORDER_STAFF+" ) REFERENCES "+ TB_STAFF+"("+TB_STAFF_ID+"))";
        String tbORDER_DETAIL = "CREATE TABLE " + TB_ORDER_DETAIL +" ( "
                + TB_ORDER_DETAIL_ORDER +" INTEGER, "
                + TB_ORDER_DETAIL_DISH+" INTEGER, "
                + TB_ORDER_DETAIL_AMOUNT +" INTEGER, PRIMARY KEY (" +TB_ORDER_DETAIL_ORDER+","+TB_ORDER_DETAIL_DISH+")," +
                "FOREIGN KEY ("+TB_ORDER_DETAIL_ORDER+") REFERENCES "+ TB_ORDER+"("+TB_ORDER_ID+")," +
                "FOREIGN KEY ("+TB_ORDER_DETAIL_DISH+") REFERENCES "+ TB_DISH+"("+TB_DISH_ID+"))";
        db.execSQL(tbSTAFF);
        db.execSQL(tbCAT);
        db.execSQL(tbTABLE);
        db.execSQL(tbDISH);
        db.execSQL(tbORDER);
        db.execSQL(tbORDER_DETAIL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public SQLiteDatabase open(){
        return this.getWritableDatabase();
    }
}
