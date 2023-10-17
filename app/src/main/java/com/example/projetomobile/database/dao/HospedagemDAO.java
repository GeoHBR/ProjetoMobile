package com.example.projetomobile.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.projetomobile.database.DBOpenHalper;
import com.example.projetomobile.database.model.GasolinaModel;
import com.example.projetomobile.database.model.HospedagemModel;
import com.example.projetomobile.database.model.ViagemModel;

public class HospedagemDAO extends AbstrataDAO{

    public HospedagemDAO(Context context) {
        db_helper = new DBOpenHalper(context);
    }

    public int Insert(HospedagemModel hospedagemModel){
        int id;

        ContentValues contentValues = new ContentValues();

        contentValues.put(HospedagemModel.COLUNA_NOITES, hospedagemModel.getTotalNoites());
        contentValues.put(HospedagemModel.COLUNA_MEDIO, hospedagemModel.getCustoMedio());
        contentValues.put(HospedagemModel.COLUNA_QUARTOS, hospedagemModel.getTotalQuartos());
        contentValues.put(HospedagemModel.COLUNA_TOTAL, hospedagemModel.getTotal());

        Open();

        long isInsert = db.insert(HospedagemModel.TABLE_NAME, null, contentValues);
        id = (int) isInsert;

        Close();

        return id;
    }

    public HospedagemModel Select(int id){
        HospedagemModel hospedagem = new HospedagemModel();

        Open();

        Cursor c = db.rawQuery("SELECT * FROM "+HospedagemModel.TABLE_NAME+" WHERE _id = "+id, null);

        if(c.getCount() > 0){
            c.moveToFirst();

            hospedagem.set_id(c.getInt(0));
            hospedagem.setCustoMedio(c.getFloat(1));
            hospedagem.setTotalNoites(c.getInt(2));
            hospedagem.setTotalQuartos(c.getInt(3));
            hospedagem.setTotal(c.getFloat(4));
        }

        Close();

        return hospedagem;
    }

    public float SelectTotal(int id){
        float total = 0;

        Open();
        Cursor c = db.rawQuery("SELECT total FROM "+HospedagemModel.TABLE_NAME+" WHERE "+HospedagemModel.COLUNA_ID+" = "
                + "(SELECT "+ ViagemModel.COLUNA_ID_HOSPEDAGEM+" FROM "+ViagemModel.TABLE_NAME+" WHERE "
                + ViagemModel.COLUNA_ID+" = "+id+")" ,null);
        if(c.getCount() > 0){
            c.moveToFirst();

            total = c.getFloat(0);
        }
        return total;
    }

    public void Delete(int id){
        Open();

        db.delete(HospedagemModel.TABLE_NAME, HospedagemModel.COLUNA_ID+" = ?",
                new String[]{String.valueOf(id)});

        Close();
    }

    public void Update(int id, HospedagemModel model){
        Open();

        ContentValues contentValues = new ContentValues();
        contentValues.put(HospedagemModel.COLUNA_TOTAL, model.getTotal());
        contentValues.put(HospedagemModel.COLUNA_QUARTOS, model.getTotalQuartos());
        contentValues.put(HospedagemModel.COLUNA_MEDIO, model.getCustoMedio());
        contentValues.put(HospedagemModel.COLUNA_NOITES, model.getTotalNoites());

        db.update(
                HospedagemModel.TABLE_NAME,
                contentValues,
                HospedagemModel.COLUNA_ID +" = ?",
                new String[]{String.valueOf(id)});

        Close();
    }
}
