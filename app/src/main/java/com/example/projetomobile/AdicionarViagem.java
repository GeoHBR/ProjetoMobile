package com.example.projetomobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.projetomobile.database.dao.UsuarioDAO;

public class AdicionarViagem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_viagem);

        UsuarioDAO dao = new UsuarioDAO(AdicionarViagem.this);
        dao.mostra();
    }
}