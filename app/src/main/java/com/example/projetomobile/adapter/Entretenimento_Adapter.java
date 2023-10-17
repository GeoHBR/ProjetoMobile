package com.example.projetomobile.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.projetomobile.R;

import java.util.ArrayList;

public class Entretenimento_Adapter extends BaseAdapter {

    public ArrayList<Entretenimento_Modelo> listaEntretenimento;
    private Activity activity;

    public Entretenimento_Adapter(final ArrayList<Entretenimento_Modelo> viagens, final Activity activity){
        listaEntretenimento = viagens;
        this.activity = activity;
    }
    @Override
    public int getCount() {
        return listaEntretenimento != null ? listaEntretenimento.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return listaEntretenimento != null ? listaEntretenimento.get(i) : null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = activity.getLayoutInflater().inflate(R.layout.modelo_viagem_lista, viewGroup, false);
        }

        Entretenimento_Modelo modelo = listaEntretenimento.get(i);


        return view;

    }
}
