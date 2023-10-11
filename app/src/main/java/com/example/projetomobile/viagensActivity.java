package com.example.projetomobile;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.projetomobile.adapter.Viagem_Modelo;
import com.example.projetomobile.adapter.Viagem_Adapter;

import java.util.ArrayList;

public class viagensActivity extends AppCompatActivity {

    private ImageButton btnAdicionar;
    private Viagem_Adapter adapter;
    private ListView listaViagens;
    private TextView userNome;
    private ImageButton btnAdd;

    private ArrayList<Viagem_Modelo> arl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viagens);

        Intent i = getIntent();

        userNome = findViewById(R.id.nomeUser);

        userNome.setText(i.getStringExtra("nome"));

        btnAdd = findViewById(R.id.btn_add);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(viagensActivity.this, AdicionarViagem.class));
            }
        });
    }
}