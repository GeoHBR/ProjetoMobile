package com.example.projetomobile.database.dao;

import android.content.ContentValues;
import android.content.Context;

import com.example.projetomobile.database.DBOpenHalper;
import com.example.projetomobile.database.model.ViagemModel;

public class ViagemDAO extends AbstrataDAO{

    public ViagemDAO(Context context) {
        db_helper = new DBOpenHalper(context);
    }

    public long Insert(ViagemModel viagemModel){
        long isInsert = 0;

        Open();

        ContentValues contentValues = new ContentValues();

        contentValues.put(ViagemModel.COLUNA_NOME, viagemModel.getNome());
        contentValues.put(ViagemModel.COLUNA_DATA_FIM, viagemModel.getDataFim());
        contentValues.put(ViagemModel.COLUNA_DATA_INICIO, viagemModel.getDataInicio());
        contentValues.put(ViagemModel.COLUNA_DESTINO, viagemModel.getDestino());
        contentValues.put(ViagemModel.COLUNA_QUANT_PESSOAS, viagemModel.getQuantPessoas());

        isInsert = db.insert(ViagemModel.TABLE_NAME, null, contentValues);

        Close();

        return isInsert;

    }

}
