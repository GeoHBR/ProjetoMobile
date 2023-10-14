package com.example.projetomobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.projetomobile.adapter.Viagem_Modelo;
import com.example.projetomobile.adapter.Viagem_Adapter;

import java.util.ArrayList;

public class viagensActivity extends AppCompatActivity {

    private ImageButton btnAdicionar;
    private ImageButton btnLogout;
    private Viagem_Adapter adapter;
    private ListView listaViagens;
    private TextView userNome;
    private ImageButton btnAdd;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viagens);

        preferences = PreferenceManager.getDefaultSharedPreferences(viagensActivity.this);
        SharedPreferences.Editor edit = preferences.edit();

        if(preferences.contains("KEY_ID_GASOLINA")){
            edit.remove("KEY_ID_GASOLINA").apply();
        }
        if(preferences.contains("KEY_ID_HOSPEDAGEM")){
            edit.remove("KEY_ID_HOSPEDAGEM").apply();
        }
        if(preferences.contains("KEY_ID_TARIFA")){
            edit.remove("KEY_ID_TARIFA").apply();
        }


        userNome = findViewById(R.id.nomeUser);
        btnAdd = findViewById(R.id.btn_add);
        btnLogout = findViewById(R.id.btn_logout);

        userNome.setText(preferences.getString("KEY_NOME", null));

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(viagensActivity.this, AdicionarViagem.class));
            }
        });

//      Função Logout
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ButtonsSalve.showAlertDialog(viagensActivity.this, "Logout", "Deseja sair de sua conta?", new ButtonsSalve.OnDismissListener() {
                    @Override
                    public void onDismiss(boolean validacao) {
                        if (validacao) {
                            edit.putString("KEY_LOGIN_AUTOMATICO", "false");
                            edit.apply();
                            startActivity(new Intent(viagensActivity.this, MainActivity.class));
                        }
                    }
                });
            }
        });
    }
}