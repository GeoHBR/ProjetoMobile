package com.example.projetomobile.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.projetomobile.database.model.EntreterimentoModel;
import com.example.projetomobile.database.model.GasolinaModel;
import com.example.projetomobile.database.model.HospedagemModel;
import com.example.projetomobile.database.model.RefeicaoModel;
import com.example.projetomobile.database.model.TarifaModel;
import com.example.projetomobile.database.model.UsuarioModel;
import com.example.projetomobile.database.model.ViagemModel;

public class DBOpenHalper extends SQLiteOpenHelper {
    private static final String DATABASE_NOME="viagem.db";
    private static final int DATABASE_VERSION = 1;


    public DBOpenHalper(Context context) {
        super(context, DATABASE_NOME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(UsuarioModel.CREATE_TABLE);
        sqLiteDatabase.execSQL(GasolinaModel.CREATE_TABLE);
        sqLiteDatabase.execSQL(TarifaModel.CREATE_TABLE);
        sqLiteDatabase.execSQL(EntreterimentoModel.CREATE_TABLE);
        sqLiteDatabase.execSQL(HospedagemModel.CREATE_TABLE);
        sqLiteDatabase.execSQL(RefeicaoModel.CREATE_TABLE);
        sqLiteDatabase.execSQL(ViagemModel.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
