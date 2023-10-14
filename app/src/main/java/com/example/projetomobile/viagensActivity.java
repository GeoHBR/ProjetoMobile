package com.example.projetomobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.projetomobile.adapter.Viagem_Modelo;
import com.example.projetomobile.adapter.Viagem_Adapter;
import com.example.projetomobile.database.dao.ViagemDAO;
import com.example.projetomobile.database.model.ViagemModel;

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
        if(preferences.contains("KEY_ID_REFEICAO")){
            edit.remove("KEY_ID_REFEICAO").apply();
        }

        listaViagens = findViewById(R.id.lista_viagens);

        ViagemDAO dao = new ViagemDAO(viagensActivity.this);
        ArrayList<ViagemModel> viagens = dao.Select(preferences.getInt("KEY_ID", 0));
        ArrayList<Viagem_Modelo> viagemModel = new ArrayList<>();

        for(int i = 0; i<viagens.size(); i++){
            Viagem_Modelo viagem = new Viagem_Modelo();
            ViagemModel viagemC = viagens.get(i);
            viagem.setId(viagemC.get_id());

            viagem.setNomeViagem(viagemC.getDestino());
            viagem.setData1(viagemC.getDataInicio());
            viagem.setData2(viagemC.getDataFim());

            viagemModel.add(viagem);
        }

        Viagem_Adapter adapter = new Viagem_Adapter(viagemModel, this);
        listaViagens.setAdapter(adapter);

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
                            edit.putString("KEY_LOGIN_AUTOMATICO", "false").apply();
                            finish();
                        }
                    }
                });
            }
        });
    }
}