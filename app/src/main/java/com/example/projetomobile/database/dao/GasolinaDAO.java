package com.example.projetomobile.database.dao;

import android.content.ContentValues;
import android.content.Context;

import com.example.projetomobile.database.DBOpenHalper;
import com.example.projetomobile.database.model.GasolinaModel;

public class GasolinaDAO extends AbstrataDAO{

    public GasolinaDAO(Context context) {
        db_helper = new DBOpenHalper(context);
    }

    public long Insert(GasolinaModel model){

        long isInsert = 0;

        Open();

        ContentValues contentValues = new ContentValues();

        contentValues.put(GasolinaModel.COLUNA_CUSTO, model.getCustoMedio());
        contentValues.put(GasolinaModel.COLUNA_MEDIAKM, model.getMedialKM());
        contentValues.put(GasolinaModel.COLUNA_TOTALKM, model.getTotalKM());
        contentValues.put(GasolinaModel.COLUNA_TOTAL, model.getTotal());
        contentValues.put(GasolinaModel.COLUNA_TOTAL_VEICULO, model.getTotalVeiculo());

        isInsert = db.insert(GasolinaModel.TABLE_NAME,null, contentValues);

        Close();

        return isInsert;
    }

}
