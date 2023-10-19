package com.example.projetomobile.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.projetomobile.database.DBOpenHalper;
import com.example.projetomobile.database.model.GasolinaModel;
import com.example.projetomobile.database.model.RefeicaoModel;
import com.example.projetomobile.database.model.ViagemModel;

public class RefeicaoDAO extends AbstrataDAO{

    public RefeicaoDAO(Context context) {
        db_helper = new DBOpenHalper(context);
    }

    public int Insert(RefeicaoModel model){
        int id = 0;

        ContentValues contentValues = new ContentValues();
        contentValues.put(RefeicaoModel.COLUNA_CUSTO_REFEICAO, model.getCustoRefeicao());
        contentValues.put(RefeicaoModel.COLUNA_QUANT_REFEICAO, model.getQuantRefeicao());
        contentValues.put(RefeicaoModel.COLUNA_TOTAL, model.getTotal());

        Open();

        long is = db.insert(RefeicaoModel.TABLE_NAME, null, contentValues);
        id = (int) is;

        Close();

        return id;
    }

    public RefeicaoModel Select(int id){
        RefeicaoModel refeicao = new RefeicaoModel();

        Open();

        Cursor c = db.rawQuery("SELECT * FROM "+RefeicaoModel.TABLE_NAME+" WHERE _id = "+id, null);

        if(c.getCount() > 0){
            c.moveToFirst();

            refeicao.set_id(c.getInt(0));
            refeicao.setCustoRefeicao(c.getFloat(1));
            refeicao.setQuantRefeicao(c.getInt(2));
            refeicao.setTotal(c.getFloat(3));
        }

        Close();

        return refeicao;
    }

    public float SelectTotal(int id){
        float total = 0;

        Open();
        Cursor c = db.rawQuery("SELECT total FROM "+RefeicaoModel.TABLE_NAME+" WHERE "+RefeicaoModel.COLUNA_ID+" = "
                + "(SELECT "+ ViagemModel.COLUNA_ID_REFEICAO+" FROM "+ViagemModel.TABLE_NAME+" WHERE "
                + ViagemModel.COLUNA_ID+" = "+id+")" ,null);
        if(c.getCount() > 0){
            c.moveToFirst();

            total = c.getFloat(0);
        }
        return total;
    }

    public void Delete(int id){
        Open();

        db.delete(RefeicaoModel.TABLE_NAME, RefeicaoModel.COLUNA_ID+" = ?",
                new String[]{String.valueOf(id)});

        Close();
    }

    public void Update(int id, RefeicaoModel model){

        Open();

        ContentValues contentValues = new ContentValues();
        contentValues.put(RefeicaoModel.COLUNA_CUSTO_REFEICAO, model.getCustoRefeicao());
        contentValues.put(RefeicaoModel.COLUNA_QUANT_REFEICAO, model.getQuantRefeicao());
        contentValues.put(RefeicaoModel.COLUNA_TOTAL, model.getTotal());

        db.update(RefeicaoModel.TABLE_NAME, contentValues, RefeicaoModel.COLUNA_ID+" = ?",
                new String[]{String.valueOf(id)});

        Close();
    }

}
