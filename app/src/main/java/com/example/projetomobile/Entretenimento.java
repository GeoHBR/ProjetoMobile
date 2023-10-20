package com.example.projetomobile;

import android.content.DialogInterface;
import android.app.AlertDialog;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projetomobile.adapter.Entretenimento_Adapter;
import com.example.projetomobile.adapter.Entretenimento_Modelo;
import com.example.projetomobile.database.dao.EntretenimentoDAO;
import com.example.projetomobile.database.dao.ViagemDAO;
import com.example.projetomobile.database.model.EntretenimentoModel;
import com.example.projetomobile.database.model.ViagemModel;

import java.util.ArrayList;

public class Entretenimento extends AppCompatActivity {
    private TextView usuario;
    private TextView custoTotal;
    private ImageButton adicionar;
    private ImageView cancelar;
    private ImageView salvar;
    private ListView listaEntretenimento;
    SharedPreferences preferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entretenimento);

        preferences = PreferenceManager.getDefaultSharedPreferences(Entretenimento.this);
        SharedPreferences.Editor edit = preferences.edit();

        Intent intent = getIntent();

        usuario = findViewById(R.id.usuario_entreterimento);
        custoTotal = findViewById(R.id.txt_custo_total_entreterimento);
        adicionar = findViewById(R.id.btn_add_entreterimento);
        cancelar = findViewById(R.id.cancelar_entreterimento);
        salvar = findViewById(R.id.add_entreterimento);
        listaEntretenimento = findViewById(R.id.lista_entreterimento);

        ArrayList<Entretenimento_Modelo> listEn = new ArrayList<>();
        Entretenimento_Adapter adapter = new Entretenimento_Adapter(listEn, this);

        listaEntretenimento.setAdapter(adapter);
        adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Entretenimento.this);
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.modal_entreterimento, null);

                final EditText nomeT  = dialogView.findViewById(R.id.nome_entreterimento);
                final EditText custoT = dialogView.findViewById(R.id.custo_entreterimento);
                builder.setView(dialogView);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Entretenimento_Modelo model = new Entretenimento_Modelo();

                        model.setNome(nomeT.getText().toString());
                        model.setPreço(Float.parseFloat(custoT.getText().toString()));

                        listEn.add(model);
                        adapter.notifyDataSetChanged();

                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EntretenimentoDAO dao = new EntretenimentoDAO(Entretenimento.this);
                EntretenimentoModel model = new EntretenimentoModel();

                model.setNome(listEn.get(0).getNome());
                model.setPreco(listEn.get(0).getPreço());

                edit.putInt("KEY_IDIDID", intent.getIntExtra("KEY_ID", 0)).apply();

                int idI = dao.Insert(model);
                edit.putInt("KEY_ID_ENTRETENIMENTO_INICIO", idI);
                for(int i = 0; i < listEn.size(); i++){
                    if(i>0){
                        model.setNome(listEn.get(i).getNome());
                        model.setPreco(listEn.get(i).getPreço());
                        model.setIdViagem(intent.getIntExtra("KEY_ID", 0));

                        int idF = dao.Insert(model);
                        edit.putInt("KEY_ID_ENTRETENIMENTO_FINAL", idF);
                    }
                }
                edit.apply();
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
