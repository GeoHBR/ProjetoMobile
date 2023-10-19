package com.example.projetomobile.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.projetomobile.database.DBOpenHalper;
import com.example.projetomobile.database.model.GasolinaModel;
import com.example.projetomobile.database.model.UsuarioModel;
import com.example.projetomobile.database.model.ViagemModel;

public class GasolinaDAO extends AbstrataDAO{

    public GasolinaDAO(Context context) {
        db_helper = new DBOpenHalper(context);
    }

    public int Insert(GasolinaModel model){

        int insert = 0;

        Open();

        ContentValues contentValues = new ContentValues();

        contentValues.put(GasolinaModel.COLUNA_CUSTO, model.getCustoMedio());
        contentValues.put(GasolinaModel.COLUNA_MEDIAKM, model.getMedialKM());
        contentValues.put(GasolinaModel.COLUNA_TOTALKM, model.getTotalKM());
        contentValues.put(GasolinaModel.COLUNA_TOTAL, model.getTotal());
        contentValues.put(GasolinaModel.COLUNA_TOTAL_VEICULO, model.getTotalVeiculo());

        long isInsert = db.insert(GasolinaModel.TABLE_NAME,null, contentValues);

        insert = (int) isInsert;

        Close();

        return insert;
    }

    public GasolinaModel Select(int id){
        GasolinaModel gasolina = new GasolinaModel();

        Open();

        Cursor c = db.rawQuery("SELECT * FROM "+GasolinaModel.TABLE_NAME+" WHERE _id = "+id, null);

        if(c.getCount() > 0){
            c.moveToFirst();

            gasolina.set_id(c.getInt(0));
            gasolina.setTotalKM(c.getFloat(1));
            gasolina.setMedialKM(c.getFloat(2));
            gasolina.setCustoMedio(c.getFloat(3));
            gasolina.setTotal(c.getFloat(4));
            gasolina.setTotalVeiculo(c.getInt(5));
        }

        Close();

        return gasolina;
    }

    public float SelectTotal(int id){
        float total = 0;

        Open();
        Cursor c = db.rawQuery("SELECT total FROM "+GasolinaModel.TABLE_NAME+" WHERE "+GasolinaModel.COLUNA_ID+" = "
                                + "(SELECT "+ ViagemModel.COLUNA_ID_GASOLINA+" FROM "+ViagemModel.TABLE_NAME+" WHERE "
                                + ViagemModel.COLUNA_ID+" = "+id+")" ,null);
        if(c.getCount() > 0){
            c.moveToFirst();

            total = c.getFloat(0);
        }
        return total;
    }

    public void Delete(int id){
        Open();

        db.delete(GasolinaModel.TABLE_NAME, GasolinaModel.COLUNA_ID+" = ?",
                new String[]{String.valueOf(id)});

        Close();
    }

    public void Update(int id, GasolinaModel model) {
        Open();

        ContentValues contentValues = new ContentValues();
        contentValues.put(GasolinaModel.COLUNA_CUSTO, model.getCustoMedio());
        contentValues.put(GasolinaModel.COLUNA_MEDIAKM, model.getMedialKM());
        contentValues.put(GasolinaModel.COLUNA_TOTALKM, model.getTotalKM());
        contentValues.put(GasolinaModel.COLUNA_TOTAL, model.getTotal());
        contentValues.put(GasolinaModel.COLUNA_TOTAL_VEICULO, model.getTotalVeiculo());

        db.update(
                GasolinaModel.TABLE_NAME,
                contentValues,
                GasolinaModel.COLUNA_ID +" = ?",
                new String[]{String.valueOf(id)});

        Close();
    }


}
