package com.example.projetomobile.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.projetomobile.R;

import java.util.ArrayList;

public class Viagem_Adapter extends BaseAdapter {

    public ArrayList<Viagem_Modelo> listaViagens;
    private Activity activity;


    public Viagem_Adapter(final Activity activity){
        this.activity = activity;
    }

    public void setlistaViagens(final ArrayList<Viagem_Modelo>viagens){
        listaViagens = viagens;
    }

    @Override
    public int getCount() {
        return listaViagens != null ? listaViagens.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return listaViagens != null ? listaViagens.get(i) : null;
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

        Viagem_Modelo viagem = listaViagens.get(i);

        TextView nomeViagem = view.findViewById(R.id.text_nome_viagem);
        nomeViagem.setText(viagem.getNomeViagem());

        TextView data1 = view.findViewById(R.id.text_data1);
        data1.setText(viagem.getData1());

        TextView data2 = view.findViewById(R.id.text_data2);
        data2.setText(viagem.getData2());

        Button btnDetalhes = view.findViewById((R.id.btn_add));
        btnDetalhes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(
                        activity, "Clicou no produto"+listaViagens.get(i).getNomeViagem(), Toast.LENGTH_LONG
                ).show();


            }
        });

        return view;
    }
}
