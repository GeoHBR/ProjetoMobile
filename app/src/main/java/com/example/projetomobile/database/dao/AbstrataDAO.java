package com.example.projetomobile.database.dao;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.projetomobile.database.DBOpenHalper;

public abstract class AbstrataDAO {
    protected SQLiteDatabase db;
    protected DBOpenHalper db_helper;

    protected  final void Open()  throws SQLException {
        db = db_helper.getWritableDatabase();
    }

    protected  final void Close() throws SQLException {
        db_helper.close();
    }
}
