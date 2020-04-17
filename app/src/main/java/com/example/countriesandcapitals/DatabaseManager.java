package com.example.countriesandcapitals;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper {

    public DatabaseManager(Context context){
        super(context, "CountriesDB", null,1);}


    public void onCreate(SQLiteDatabase db){
        String sql = " create table CountryTable(";
        sql += "id integer primary key autoincrement, ";
        sql += "country text, capital text)";

        db.execSQL(sql);

    }

    public void insert(String countryName, String capitalName){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "insert into CountryTable values(";
        sql += "null, '"+countryName+"', '"+capitalName+"')";
        db.execSQL(sql);
        db.close();
    }

    public void updateByCountry(String country, String capital){
        //given a country and a capital it edits the name of the capital
        SQLiteDatabase db = getWritableDatabase();
        String sql = "update CountryTable set capital = '"+capital+"' ";
        sql+= "where country = '"+country+"'";
        db.execSQL(sql);
        db.close();

    }


    public ArrayList<String> getCountries(){
        ArrayList<String> list = new ArrayList<String>();
        SQLiteDatabase db = getWritableDatabase();
        String sql = "select * from CountryTable";
        Cursor cursor = db.rawQuery(sql, null);
        while(cursor.moveToNext()){
            String country = cursor.getString(1);
            list.add(country);
        }
        db.close();
        return list;
    }



    public String[] get(String country){

        SQLiteDatabase db = getWritableDatabase();
        String sql = "select * from CountryTable where country = '"+country+"'";
        Cursor cursor = db.rawQuery(sql, null);
        String[] entry = new String[2];
        if(cursor.moveToFirst()){
            String country1 = cursor.getString(1);
            String capital = cursor.getString(2);
            entry[0]=country1;
            entry[1]=capital;
        }
        db.close();
        return entry;
    }

    public void delete(String country){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "delete from CountryTable where country ='"+country+"'";
        db.execSQL(sql);
        db.close();

    }




    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }




}

