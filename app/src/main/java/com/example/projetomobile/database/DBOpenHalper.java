package com.example.projetomobile.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHalper extends SQLiteOpenHelper {
    private static final String DATABASE_NOME="viagem.db";
    private static final int DATABASE_VERSION = 1;


    public DBOpenHalper(Context context) {
        super(context, DATABASE_NOME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
