package com.example.projetomobile.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.projetomobile.R;

import java.util.ArrayList;
import java.util.IdentityHashMap;

public class Entretenimento_Adapter extends BaseAdapter {

    public ArrayList<Entretenimento_Modelo> listaEntretenimento;
    private Activity activity;

    public Entretenimento_Adapter(final ArrayList<Entretenimento_Modelo> entretenimento, final Activity activity){
        listaEntretenimento = entretenimento;
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
            view = activity.getLayoutInflater().inflate(R.layout.modelo_lista_entretenimento, viewGroup, false);
        }

        Entretenimento_Modelo entretenimento = listaEntretenimento.get(i);

        TextView nome = view.findViewById(R.id.nome_entreterimentoLista);
        TextView preço = view.findViewById(R.id.custo_entreterimentoLista);
        ImageButton remover = view.findViewById(R.id.remover_entreterimento);

        nome.setText(entretenimento.getNome());
        preço.setText(String.valueOf(entretenimento.getPreço()));

        remover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listaEntretenimento.remove(i);
                notifyDataSetChanged();
            }
        });

        return view;

    }
}
