package com.example.projetomobile.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.projetomobile.API.Model.UnescViagem;
import com.example.projetomobile.R;
import com.example.projetomobile.RelatorioSync;

import java.util.ArrayList;

public class ViagemSync_Adapter extends BaseAdapter {

    public ArrayList<UnescViagem> listaViagens;
    private Activity activity;


    public ViagemSync_Adapter(final ArrayList<UnescViagem> viagens, final Activity activity){
        listaViagens = viagens;
        this.activity = activity;
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
            view = activity.getLayoutInflater().inflate(R.layout.modelo_viagemsync_lista, viewGroup, false);
        }

        UnescViagem viagem = listaViagens.get(i);

        TextView nomeViagem = view.findViewById(R.id.text_nome_viagem);
        nomeViagem.setText(viagem.getLocal());

        TextView duracao = view.findViewById(R.id.duracao);
        duracao.setText(String.valueOf(viagem.getDuracaoViagem())+" Dias");

        TextView total = view.findViewById(R.id.totalLista);
        total.setText("R$ " + String.format("%.2f", viagem.getCustoTotalViagem()));

        ImageButton btnRelatorio = view.findViewById(R.id.btnRelatorio);
        btnRelatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, RelatorioSync.class);
                intent.putExtra("VIAGEM", viagem);
                activity.startActivity(intent);
            }
        });

        return view;
    }
}
