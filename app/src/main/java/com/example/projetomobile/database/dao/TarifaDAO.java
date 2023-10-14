package com.example.projetomobile.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.projetomobile.database.DBOpenHalper;
import com.example.projetomobile.database.model.TarifaModel;

public class TarifaDAO extends AbstrataDAO{

    public TarifaDAO(Context context) {
        db_helper = new DBOpenHalper(context);
    }

    public int Insert(TarifaModel model){
        int id = 0;
        Open();

        ContentValues contentValues = new ContentValues();
        contentValues.put(TarifaModel.COLUNA_CUSTO_PESSOA, model.getCustoPessoa());
        contentValues.put(TarifaModel.COLUNA_CUSTO_ALUGUEL, model.getCustoAluguel());
        contentValues.put(TarifaModel.COLUNA_TOTAL, model.getTotal());

        long i = db.insert(TarifaModel.TABLE_NAME, null, contentValues);
        id = (int) i;

        Close();
        return id;
    }

    public TarifaModel Select(int id){
        TarifaModel tarifa = new TarifaModel();

        Open();

        Cursor c = db.rawQuery("SELECT * FROM "+TarifaModel.TABLE_NAME+" WHERE _id = "+id, null);

        if(c.getCount() > 0){
            c.moveToFirst();

            tarifa.set_id(c.getInt(0));
            tarifa.setCustoPessoa(c.getFloat(1));
            tarifa.setCustoAluguel(c.getFloat(2));
            tarifa.setTotal(c.getFloat(3));
        }

        Close();

        return tarifa;
    }


    public void Delete(int id){
        Open();

        db.delete(TarifaModel.TABLE_NAME, TarifaModel.COLUNA_ID+" = ?",
                new String[]{String.valueOf(id)});

        Close();
    }

    public void Update(int id, TarifaModel tarifa){
        Open();

        ContentValues contentValues = new ContentValues();
        contentValues.put(TarifaModel.COLUNA_CUSTO_PESSOA, tarifa.getCustoPessoa());
        contentValues.put(TarifaModel.COLUNA_CUSTO_ALUGUEL, tarifa.getCustoAluguel());
        contentValues.put(TarifaModel.COLUNA_TOTAL, tarifa.getTotal());

        db.update(
                TarifaModel.TABLE_NAME,
                contentValues,
                TarifaModel.COLUNA_ID +" = ?",
                new String[]{String.valueOf(id)});

        Close();
    }
}
