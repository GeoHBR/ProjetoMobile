package com.example.projetomobile.database.dao;

import android.content.ContentValues;
import android.content.Context;

import com.example.projetomobile.database.DBOpenHalper;
import com.example.projetomobile.database.model.HospedagemModel;

public class HospedagemDAO extends AbstrataDAO{

    public HospedagemDAO(Context context) {
        db_helper = new DBOpenHalper(context);
    }

    public long Insert(HospedagemModel hospedagemModel){
        long i;

        ContentValues contentValues = new ContentValues();

        contentValues.put(HospedagemModel.COLUNA_NOITES, hospedagemModel.getTotalNoites());
        contentValues.put(HospedagemModel.COLUNA_MEDIO, hospedagemModel.getCustoMedio());
        contentValues.put(HospedagemModel.COLUNA_QUARTOS, hospedagemModel.getTotalQuartos());
        contentValues.put(HospedagemModel.COLUNA_TOTAL, hospedagemModel.getTotal());

        Open();

        i = db.insert(HospedagemModel.TABLE_NAME, null, contentValues);

        Close();
        return i;
    }
}
