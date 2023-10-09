package com.example.projetomobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.projetomobile.adapter.Viagem_Modelo;
import com.example.projetomobile.adapter.Viagem_Adapter;

import java.util.ArrayList;

public class viagensActivity extends AppCompatActivity {

    private ImageButton btnAdicionar;
    private Viagem_Adapter adapter;
    private ListView listaViagens;

    private ArrayList<Viagem_Modelo> arl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viagens);

    }
}