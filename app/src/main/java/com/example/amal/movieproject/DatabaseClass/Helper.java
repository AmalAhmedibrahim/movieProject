package com.example.amal.movieproject.DatabaseClass;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import com.example.amal.movieproject.model.Movie;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.id;
import static com.example.amal.movieproject.DatabaseClass.Contractor.table1.TABLE_NAME;

/**
 * Created by emyahmed_96 on 7/24/2017.
 */

public class Helper extends SQLiteOpenHelper
{
    public static final int Version = 2;
    public static final String Database_Name="movieDAta.db";

    public Helper(Context context) {
        super(context, Database_Name, null, Version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + " ( " + Contractor.table1.COLUMN_TITLE + " Text);");

        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + Contractor.table1.COLUMN_TITLE + " TEXT," + Contractor.table1.COLUMN_Poster + " TEXT,"
                + Contractor.table1.COLUMN_Desc + " TEXT" + ")";


        sqLiteDatabase.execSQL(CREATE_TABLE);

    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public void insert(Movie movie) {
        SQLiteDatabase DB=getWritableDatabase();
        ContentValues row=new ContentValues();


        row.put(Contractor.table1.COLUMN_TITLE,movie.getName());
        row.put(Contractor.table1.COLUMN_Poster,movie.getPosterPath());
        row.put(Contractor.table1.COLUMN_Desc,movie.getOverView());


        DB.insert(Contractor.table1.TABLE_NAME,null,row);
        DB.close();

        //DB.execSQL("delete from "+ TABLE_NAME);



    }
    public Cursor select(Movie m)
    {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c;
         //c= db.query(Contractor.table1.TABLE_NAME,new String[]{Contractor.table1.COLUMN_TITLE},null,null,null,null,null);
        c =  db.rawQuery("select * from " + Contractor.table1.TABLE_NAME + " where " + Contractor.table1.COLUMN_TITLE + "='" + m.getName() + "'" , null);
        return c;
    }

    public List<Movie> getAllContacts() {
        List<Movie> contactList = new ArrayList <Movie>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + Contractor.table1.TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor != null)
            cursor.moveToFirst();

        if (cursor.moveToFirst()) {
            do {
                Movie movie = new Movie();
                movie.setName(cursor.getString(0));
                movie.setPosterPath(cursor.getString(1));
                movie.setOverView(cursor.getString(2));

                contactList.add(movie);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }
    public void RemoveMovie(Movie m)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Contractor.table1.TABLE_NAME, Contractor.table1.COLUMN_TITLE + " = ?",
                new String[] { String.valueOf(m.getName()) });
        db.close();
    }



}

