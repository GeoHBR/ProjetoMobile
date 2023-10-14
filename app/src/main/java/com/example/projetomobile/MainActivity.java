package com.example.projetomobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView btnEntrar;
    TextView btnCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEntrar = findViewById(R.id.btnLogin);
        btnCadastro = findViewById(R.id.btnCadastro);
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

        if(preferences.getString("KEY_LOGIN_AUTOMATICO", "").equals("true")) {
            startActivity(new Intent(MainActivity.this, viagensActivity.class));
        }

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, CadastroActivity.class));
            }
        });

    }
}