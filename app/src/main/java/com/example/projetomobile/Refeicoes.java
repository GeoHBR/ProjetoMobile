package com.example.projetomobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projetomobile.database.dao.RefeicaoDAO;

public class Refeicoes extends AppCompatActivity {

    private TextView usuario;
    private TextView custoTotal;
    private EditText custoRefeicao;
    private EditText quantRefeicao;
    private ImageView cancelar;
    private ImageView voltar;
    private ImageView salvar;
    SharedPreferences preferences;
    private float precoTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refeicoes);

        preferences = PreferenceManager.getDefaultSharedPreferences(Refeicoes.this);
        SharedPreferences.Editor edit = preferences.edit();

        usuario = findViewById(R.id.usuarioRefeicao);
        custoTotal = findViewById(R.id.totalRefeicao);
        quantRefeicao = findViewById(R.id.quantRefeicao);
        custoRefeicao = findViewById(R.id.custoRefeicao);
        voltar = findViewById(R.id.voltarRefeicao);
        cancelar = findViewById(R.id.cancelarRefeicao);
        salvar = findViewById(R.id.salvarRefeicao);

        RefeicaoDAO dao = new RefeicaoDAO(Refeicoes.this);

        usuario.setText(preferences.getString("KEY_NOME", null));
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}