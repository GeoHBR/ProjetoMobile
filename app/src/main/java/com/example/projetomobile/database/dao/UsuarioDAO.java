package com.example.projetomobile.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Path;

import com.example.projetomobile.database.DBOpenHalper;
import com.example.projetomobile.database.model.UsuarioModel;
import com.example.projetomobile.database.model.ViagemModel;

import java.util.ArrayList;

public class UsuarioDAO extends AbstrataDAO{

    public UsuarioDAO(Context context) {
        db_helper = new DBOpenHalper(context);
    }

    public int Insert(UsuarioModel model){
        int id = 0;

        Open();

        ContentValues contentValues = new ContentValues();

        contentValues.put(UsuarioModel.COLUNA_NOME, model.getNome());
        contentValues.put(UsuarioModel.COLUNA_EMAIL, model.getEmail());
        contentValues.put(UsuarioModel.COLUNA_SENHA, model.getSenha());

        long isInsert = db.insert(UsuarioModel.TABLE_NAME, null, contentValues);
        id = (int) isInsert;

        Close();

        return id;
    }

    public UsuarioModel SelectLogin(String email, String senha){

        UsuarioModel user = new UsuarioModel();

        Open();

        Cursor c = db.rawQuery("SELECT * FROM "+UsuarioModel.TABLE_NAME+" WHERE email = '"+email+"'"
                + " AND senha ='"+senha+"'", null);

        if(c.getCount() > 0){
            c.moveToFirst();

            user.setId(c.getInt(0));
            user.setNome(c.getString(1));
            user.setEmail(c.getString(2));
            user.setSenha(c.getString(3));
        }

        Close();

        return user;

    }

}
