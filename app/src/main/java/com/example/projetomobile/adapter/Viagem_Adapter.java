package com.example.projetomobile.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;


import com.example.projetomobile.AdicionarViagem;
import com.example.projetomobile.Gasolina;
import com.example.projetomobile.R;
import com.example.projetomobile.Relatorio;
import com.example.projetomobile.database.dao.EntretenimentoDAO;
import com.example.projetomobile.database.dao.GasolinaDAO;
import com.example.projetomobile.database.dao.HospedagemDAO;
import com.example.projetomobile.database.dao.RefeicaoDAO;
import com.example.projetomobile.database.dao.TarifaDAO;
import com.example.projetomobile.database.dao.ViagemDAO;
import com.example.projetomobile.viagensActivity;

import java.util.ArrayList;

public class Viagem_Adapter extends BaseAdapter {

    public ArrayList<Viagem_Modelo> listaViagens;
    private Activity activity;


    public Viagem_Adapter(final ArrayList<Viagem_Modelo>viagens, final Activity activity){
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
        return listaViagens.get(i).getId();
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

        TextView total = view.findViewById(R.id.totalLista);
        total.setText("R$ " + String.valueOf(viagem.getTotal()));

        ImageButton btnRelatorio = view.findViewById(R.id.btnRelatorio);
        btnRelatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, Relatorio.class);
                intent.putExtra("ID", viagem.getId());
                intent.putExtra("TOTAL", viagem.getTotal());
                activity.startActivity(intent);
            }
        });

        return view;
    }
}
