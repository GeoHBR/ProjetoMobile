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
import com.example.projetomobile.database.dao.EntretenimentoDAO;
import com.example.projetomobile.database.dao.GasolinaDAO;
import com.example.projetomobile.database.dao.HospedagemDAO;
import com.example.projetomobile.database.dao.RefeicaoDAO;
import com.example.projetomobile.database.dao.TarifaDAO;
import com.example.projetomobile.database.dao.ViagemDAO;
import com.example.projetomobile.database.model.ViagemModel;

import java.util.ArrayList;

public class viagensActivity extends AppCompatActivity {

    private ImageButton btnLogout;
    private ListView listaViagens;
    private TextView userNome;
    private ImageButton btnAdd;
    SharedPreferences preferences;
    private SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viagens);

        preferences = PreferenceManager.getDefaultSharedPreferences(viagensActivity.this);
        edit = preferences.edit();

        removerPreferences();

        listaViagens = findViewById(R.id.lista_viagens);

        ViagemDAO dao = new ViagemDAO(viagensActivity.this);
        ArrayList<ViagemModel> viagens = dao.SelectAll(preferences.getInt("KEY_ID", 0));
        if(viagens.size() > 0){
            ArrayList<Viagem_Modelo> viagemModel = new ArrayList<>();

            for(int i = 0; i<viagens.size(); i++){
                Viagem_Modelo viagem = new Viagem_Modelo();
                ViagemModel viagemC = viagens.get(i);

                viagem.setId(viagemC.get_id());
                viagem.setNomeViagem(viagemC.getDestino());
                viagem.setData1(viagemC.getDataInicio());
                viagem.setData2(viagemC.getDataFim());
                viagem.setTotal(calcularTotal(viagemC.get_id()));

                viagemModel.add(viagem);
            }

            Viagem_Adapter adapter = new Viagem_Adapter(viagemModel, this);
            listaViagens.setAdapter(adapter);
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
                            edit.putString("KEY_LOGIN_AUTOMATICO", "false").apply();
                            finish();
                        }
                    }
                });
            }
        });
    }

    @Override
    protected void onResume() {
        ViagemDAO dao = new ViagemDAO(viagensActivity.this);
        ArrayList<ViagemModel> viagens = dao.SelectAll(preferences.getInt("KEY_ID", 0));
        if(viagens.size() > 0){
            ArrayList<Viagem_Modelo> viagemModel = new ArrayList<>();

            for(int i = 0; i<viagens.size(); i++){
                Viagem_Modelo viagem = new Viagem_Modelo();
                ViagemModel viagemC = viagens.get(i);

                viagem.setId(viagemC.get_id());
                viagem.setNomeViagem(viagemC.getDestino());
                viagem.setData1(viagemC.getDataInicio());
                viagem.setData2(viagemC.getDataFim());
                viagem.setTotal(calcularTotal(viagemC.get_id()));

                viagemModel.add(viagem);
            }

            Viagem_Adapter adapter = new Viagem_Adapter(viagemModel, this);
            listaViagens.setAdapter(adapter);
        }
        super.onResume();
    }

    private void removerPreferences(){
        edit.remove("KEY_ID_GASOLINA").apply();
        edit.remove("KEY_ID_HOSPEDAGEM").apply();
        edit.remove("KEY_ID_TARIFA").apply();
        edit.remove("KEY_ID_REFEICAO").apply();
    }

    private float calcularTotal(int idViagem){

        GasolinaDAO daoG = new GasolinaDAO(this);
        HospedagemDAO daoH = new HospedagemDAO(this);
        RefeicaoDAO daoR = new RefeicaoDAO(this);
        TarifaDAO daoT = new TarifaDAO(this);
        EntretenimentoDAO daoE = new EntretenimentoDAO(this);

        float totalG = daoG.SelectTotal(idViagem);
        float totalH = daoH.SelectTotal(idViagem);
        float totalR = daoR.SelectTotal(idViagem);
        float totalT = daoT.SelectTotal(idViagem);
        float totalE = daoE.SelectTotal(idViagem);

        return totalG + totalH + totalR + totalT + totalE;
    }
}