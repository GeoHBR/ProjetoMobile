package com.example.projetomobile.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Path;

import com.example.projetomobile.database.DBOpenHalper;
import com.example.projetomobile.database.model.UsuarioModel;
import com.example.projetomobile.database.model.ViagemModel;

public class UsuarioDAO extends AbstrataDAO{

    public UsuarioDAO(Context context) {
        db_helper = new DBOpenHalper(context);
    }

    public void abrir(){
        Open();
    }


    public long Insert(UsuarioModel model){
        long isInsert = 0;

        Open();

        ContentValues contentValues = new ContentValues();

        contentValues.put(UsuarioModel.COLUNA_NOME, model.getNome());
        contentValues.put(UsuarioModel.COLUNA_EMAIL, model.getEmail());
        contentValues.put(UsuarioModel.COLUNA_SENHA, model.getSenha());

        isInsert = db.insert(ViagemModel.TABLE_NAME, null, contentValues);

        Close();

        return isInsert;

    }

}
