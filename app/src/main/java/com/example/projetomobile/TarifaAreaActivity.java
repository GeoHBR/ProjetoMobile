package com.example.projetomobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class TarifaAreaActivity extends AppCompatActivity {

    private TextView usuario;
    private TextView total;
    private EditText custoPessoa;
    private EditText alugelVeiculo;
    private ImageView voltar;
    private ImageView cancelar;
    private ImageView salvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarifa_aerea);

        usuario = findViewById(R.id.usuarioTarifa);
        total = findViewById(R.id.totalTarifa);
        custoPessoa = findViewById(R.id.custoPessoaTarifa);
        alugelVeiculo = findViewById(R.id.aluguelTarifa);
        voltar = findViewById(R.id.voltarTarifa);
        cancelar = findViewById(R.id.cancelarTarifa);
        salvar = findViewById(R.id.salvarTarifa);
    }
}