package com.example.projetomobile;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projetomobile.adapter.Entretenimento_Adapter;
import com.example.projetomobile.adapter.Entretenimento_Modelo;
import com.example.projetomobile.adapter.Viagem_Adapter;
import com.example.projetomobile.database.model.EntretenimentoModel;

import java.util.ArrayList;

public class Entretenimento extends AppCompatActivity {
    private TextView usuario;
    private TextView custoTotal;
    private ImageButton adicionar;
    private ImageView cancelar;
    private ImageView salvar;
    private ListView listaEntretenimento;
    SharedPreferences preferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entretenimento);

        preferences = PreferenceManager.getDefaultSharedPreferences(Entretenimento.this);
        SharedPreferences.Editor edit = preferences.edit();

        usuario = findViewById(R.id.usuario_entreterimento);
        custoTotal = findViewById(R.id.txt_custo_total_entreterimento);
        adicionar = findViewById(R.id.btn_add_entreterimento);
        cancelar = findViewById(R.id.cancelar_entreterimento);
        salvar = findViewById(R.id.add_entreterimento);
        listaEntretenimento = findViewById(R.id.lista_entreterimento);

        ArrayList<Entretenimento_Modelo> listEn = new ArrayList<>();
        Entretenimento_Adapter adapter = new Entretenimento_Adapter(listEn, this);
        adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Entretenimento_Modelo model = new Entretenimento_Modelo();

                model.setId(0);
                model.setNome("");
                model.setPreço(0);

                listEn.add(model);

                listaEntretenimento.setAdapter(adapter);
            }
        });
    }
}
