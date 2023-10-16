package com.example.projetomobile;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Entreterimento extends AppCompatActivity {
    private TextView usuario;
    private TextView custoTotal;
    private ImageButton adicionar;
    private ImageView cancelar;
    private ImageView salvar;
    SharedPreferences preferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entreterimento);

        preferences = PreferenceManager.getDefaultSharedPreferences(Entreterimento.this);
        SharedPreferences.Editor edit = preferences.edit();

        usuario = findViewById(R.id.usuario_entreterimento);
        custoTotal = findViewById(R.id.txt_custo_total_entreterimento);
        adicionar = findViewById(R.id.btn_add_entreterimento);
        cancelar = findViewById(R.id.cancelar_entreterimento);
        salvar = findViewById(R.id.add_entreterimento);
    }
}
