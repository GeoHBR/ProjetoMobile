package com.example.projetomobile.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.projetomobile.database.DBOpenHalper;
import com.example.projetomobile.database.model.EntretenimentoModel;
import com.example.projetomobile.database.model.TarifaModel;
import com.example.projetomobile.database.model.ViagemModel;

import java.util.ArrayList;

public class EntretenimentoDAO extends AbstrataDAO{

    public EntretenimentoDAO(Context context) {
        db_helper = new DBOpenHalper(context);
    }

    public int Insert(EntretenimentoModel model){
        int id;

        ContentValues contentValues = new ContentValues();
        contentValues.put(EntretenimentoModel.COLUNA_NOME, model.getNome());
        contentValues.put(EntretenimentoModel.COLUNA_PRECO, model.getPreco());
        contentValues.put(EntretenimentoModel.COLUNA_ID_VIAGEM, model.getIdViagem());

        Open();

        long is = db.insert(EntretenimentoModel.TABLE_NAME, null, contentValues);
        id = (int) is;

        Close();

        return id;
    }

    public ArrayList<EntretenimentoModel> Select(int id){
        ArrayList<EntretenimentoModel> entreterimentos = new ArrayList<>();

        Open();

        Cursor c = db.rawQuery("SELECT * FROM "+ EntretenimentoModel.TABLE_NAME+" WHERE "
                + EntretenimentoModel.COLUNA_ID_VIAGEM + " = "+id, null);

        if(c.getCount() > 0){
            c.moveToFirst();
            do{
                EntretenimentoModel entretenimento = new EntretenimentoModel();

                entretenimento.set_id(c.getInt(0));
                entretenimento.setNome(c.getString(1));
                entretenimento.setPreco(c.getFloat(2));
                entretenimento.setIdViagem(c.getInt(3));

                entreterimentos.add(entretenimento);

            }while(c.moveToNext());
        }

        Close();

        return entreterimentos;
    }

    public float SelectTotal(int id){
        float total = 0;
        Open();

        Cursor c = db.rawQuery("SELECT "+EntretenimentoModel.COLUNA_PRECO+" FROM "+ EntretenimentoModel.TABLE_NAME+" WHERE "
                + EntretenimentoModel.COLUNA_ID_VIAGEM + " = "+id, null);

        if(c.getCount() > 0){
            c.moveToFirst();
            do{

                total = total + c.getFloat(0);

            }while(c.moveToNext());
        }

        Close();
        return total;
    }

    public void Delete(int id){
        Open();

        db.delete(EntretenimentoModel.TABLE_NAME, EntretenimentoModel.COLUNA_ID_VIAGEM + " = "+id, null);

        Close();
    }

}
