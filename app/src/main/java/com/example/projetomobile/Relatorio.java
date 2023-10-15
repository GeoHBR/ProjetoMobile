package com.example.projetomobile;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Relatorio extends AppCompatActivity {
    private TextView usuario;
    private TextView custoTotal;
    private EditText qtdViajantes;
    private EditText duracaoViajem;
    private EditText custoTotal2;
    private EditText custoViajante;
    private ImageView voltar;
    SharedPreferences preferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio);

        preferences = PreferenceManager.getDefaultSharedPreferences(Relatorio.this);
        SharedPreferences.Editor edit = preferences.edit();

        usuario = findViewById(R.id.usuarioRelatorio);
        custoTotal = findViewById(R.id.custoTotalRelatorio);
        qtdViajantes = findViewById(R.id.qtdViajantesRelatorio);
        duracaoViajem = findViewById(R.id.duracaoViagemRelatorio);
        custoTotal2 = findViewById(R.id.custoTotalRelatorio);
        custoViajante = findViewById(R.id.custoViajanteRelatorio);
        voltar = findViewById(R.id.voltarRelatorio);
    }
}
