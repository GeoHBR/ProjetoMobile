package com.example.projetomobile;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Entreterimento extends AppCompatActivity {
    private TextView usuario;
    private TextView custoTotal;
    private EditText nome1;
    private EditText custo1;
    private EditText nome2;
    private EditText custo2;
    private EditText nome3;
    private EditText custo3;
    private EditText nome4;
    private EditText custo4;
    private ImageView cancelar;
    private ImageView salvar;
    SharedPreferences preferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entreterimento);

        preferences = PreferenceManager.getDefaultSharedPreferences(Entreterimento.this);
        SharedPreferences.Editor edit = preferences.edit();

        usuario = findViewById(R.id.usuarioEntreterimento);
        custoTotal = findViewById(R.id.txtCustoTotalEntreterimento);
        nome1 = findViewById(R.id.nomeEntreterimento1);
        custo1 = findViewById(R.id.custoEntreterimento1);
        nome2 = findViewById(R.id.nomeEntreterimento2);
        custo2 = findViewById(R.id.custoEntreterimento2);
        nome3 = findViewById(R.id.nomeEntreterimento3);
        custo3 = findViewById(R.id.custoEntreterimento3);
        nome4 = findViewById(R.id.nomeEntreterimento4);
        custo4 = findViewById(R.id.custoEntreterimento4);
    }
}
