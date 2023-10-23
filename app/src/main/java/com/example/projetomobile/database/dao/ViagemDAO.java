package com.example.projetomobile.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Path;

import com.example.projetomobile.database.DBOpenHalper;
import com.example.projetomobile.database.model.TarifaModel;
import com.example.projetomobile.database.model.UsuarioModel;
import com.example.projetomobile.database.model.ViagemModel;

import java.util.ArrayList;

public class ViagemDAO extends AbstrataDAO{

    public ViagemDAO(Context context) {
        db_helper = new DBOpenHalper(context);
    }

    public int Insert(ViagemModel viagemModel){
        int id = 0;

        Open();

        ContentValues contentValues = new ContentValues();

        contentValues.put(ViagemModel.COLUNA_DATA_FIM, viagemModel.getDataFim());
        contentValues.put(ViagemModel.COLUNA_DATA_INICIO, viagemModel.getDataInicio());
        contentValues.put(ViagemModel.COLUNA_DESTINO, viagemModel.getDestino());
        contentValues.put(ViagemModel.COLUNA_QUANT_PESSOAS, viagemModel.getQuantPessoas());
        contentValues.put(ViagemModel.COLUNA_ID_USUARIO, viagemModel.get_idUsuario());
        contentValues.put(ViagemModel.COLUNA_ID_GASOLINA, viagemModel.get_idGasolina());
        contentValues.put(ViagemModel.COLUNA_ID_HOSPEDAGEM, viagemModel.get_idHospedagem());
        contentValues.put(ViagemModel.COLUNA_ID_REFEICAO, viagemModel.get_idRefeicao());
        contentValues.put(ViagemModel.COLUNA_ID_TARIFA, viagemModel.get_idTarifa());

        long isInsert = db.insert(ViagemModel.TABLE_NAME, null, contentValues);
        id = (int) isInsert;

        Close();

        return id;
    }

    public void Update(ViagemModel viagemModel) {
        Open();

        ContentValues contentValues = new ContentValues();

        contentValues.put(ViagemModel.COLUNA_DATA_FIM, viagemModel.getDataFim());
        contentValues.put(ViagemModel.COLUNA_DATA_INICIO, viagemModel.getDataInicio());
        contentValues.put(ViagemModel.COLUNA_DESTINO, viagemModel.getDestino());
        contentValues.put(ViagemModel.COLUNA_QUANT_PESSOAS, viagemModel.getQuantPessoas());
        contentValues.put(ViagemModel.COLUNA_ID_USUARIO, viagemModel.get_idUsuario());
        contentValues.put(ViagemModel.COLUNA_ID_GASOLINA, viagemModel.get_idGasolina());
        contentValues.put(ViagemModel.COLUNA_ID_HOSPEDAGEM, viagemModel.get_idHospedagem());
        contentValues.put(ViagemModel.COLUNA_ID_REFEICAO, viagemModel.get_idRefeicao());
        contentValues.put(ViagemModel.COLUNA_ID_TARIFA, viagemModel.get_idTarifa());

        db.update(
                ViagemModel.TABLE_NAME,
                contentValues,
                ViagemModel.COLUNA_ID +" = ?",
                new String[]{String.valueOf(viagemModel.get_id())});

        Close();
    }

    public ArrayList<ViagemModel> SelectAll(int id){

        ArrayList<ViagemModel> viagens = new ArrayList<>();

        Open();

         Cursor c = db.rawQuery("SELECT * FROM "+ ViagemModel.TABLE_NAME+" WHERE _id_usuario = "+id, null);

        if(c.getCount() > 0){
            c.moveToFirst();
            do{
                ViagemModel viagem = new ViagemModel();

                viagem.set_id(c.getInt(0));
                viagem.setDataInicio(c.getString(1));
                viagem.setDataFim(c.getString(2));
                viagem.setQuantPessoas(c.getInt(3));
                viagem.setDestino(c.getString(4));
                viagem.set_idUsuario(c.getInt(5));
                viagem.set_idTarifa(c.getInt(6));
                viagem.set_idGasolina(c.getInt(7));
                viagem.set_idRefeicao(c.getInt(8));
                viagem.set_idRefeicao(c.getInt(9));

                viagens.add(viagem);

            }while(c.moveToNext());
        }

        Close();

        return viagens;
    }

    public ViagemModel SelectViagem(int id){

        ViagemModel viagem = new ViagemModel();

        Open();

        Cursor c = db.rawQuery("SELECT * FROM "+ ViagemModel.TABLE_NAME+" WHERE " +ViagemModel.COLUNA_ID+" = "+id, null);

        if(c.getCount() > 0){
            c.moveToFirst();

            viagem.set_id(c.getInt(0));
            viagem.setDataInicio(c.getString(1));
            viagem.setDataFim(c.getString(2));
            viagem.setQuantPessoas(c.getInt(3));
            viagem.setDestino(c.getString(4));
            viagem.set_idUsuario(c.getInt(5));
            viagem.set_idTarifa(c.getInt(6));
            viagem.set_idGasolina(c.getInt(7));
            viagem.set_idRefeicao(c.getInt(8));
            viagem.set_idHospedagem(c.getInt(9));
        }

        Close();

        return viagem;
    }

    public int SelectTotal(int id){
        int total = 0;
        Open();

        Cursor c = db.rawQuery("SELECT _id FROM "+ViagemModel.TABLE_NAME + " WHERE "
                + ViagemModel.COLUNA_ID_USUARIO+" = "+id, null);

        if(c.getCount() > 0){
            total = c.getCount();
        }

        Close();

        return total;
    }


    public ArrayList<Integer> Delete(int id){
        ViagemModel model = SelectViagem(id);

        ArrayList<Integer> ids = new ArrayList<>();

        ids.add(model.get_idGasolina());
        ids.add(model.get_idHospedagem());
        ids.add(model.get_idRefeicao());
        ids.add(model.get_idTarifa());

        Open();

        int d = db.delete(ViagemModel.TABLE_NAME, ViagemModel.COLUNA_ID + " = "+id, null);

        Close();

        return  ids;
    }
}
