package com.example.projetomobile;

import android.content.DialogInterface;
import android.app.AlertDialog;
import android.content.Intent;
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
import com.example.projetomobile.database.model.EntretenimentoModel;

import java.util.ArrayList;

public class Entretenimento extends AppCompatActivity {
    private TextView usuario;
    private TextView custoTotal;
    private ImageButton adicionar;
    private ImageView cancelar;
    private ImageView salvar;
    private ListView listaEntretenimento;
    SharedPreferences preferences;
    private ArrayList<Entretenimento_Modelo> listEn = new ArrayList<>();
    private ArrayList<EntretenimentoModel> listaE = new ArrayList<>();
    private ArrayList<Entretenimento_Modelo> listEnBanco = new ArrayList<>();
    private float total;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entretenimento);

        preferences = PreferenceManager.getDefaultSharedPreferences(Entretenimento.this);
        SharedPreferences.Editor edit = preferences.edit();

        usuario = findViewById(R.id.usuario_entreterimento);
        custoTotal = findViewById(R.id.txt_custo_total_entreterimento);
        adicionar = findViewById(R.id.btn_add_entreterimento);
        cancelar = findViewById(R.id.cancelar_entreterimento);
        salvar = findViewById(R.id.add_entreterimento);
        listaEntretenimento = findViewById(R.id.lista_entreterimento);

        usuario.setText(preferences.getString("KEY_NOME", null));

        Entretenimento_Adapter adapter = new Entretenimento_Adapter(listEn, this);

        Intent intent = getIntent();
        boolean edicao = intent.getBooleanExtra("EDICAO", false);
        if(edicao) {
            listaE = (ArrayList<EntretenimentoModel>) intent.getSerializableExtra("ENTRETENIMENTO");
        }
        if(listaE.size() > 0){
            for(int i = 0; i < listaE.size(); i++){
                Entretenimento_Modelo modelLista = new Entretenimento_Modelo();

                modelLista.setNome(listaE.get(i).getNome());
                modelLista.setPreço(listaE.get(i).getPreco());

                total += listaE.get(i).getPreco();

                listEn.add(modelLista);
            }
        }
        custoTotal.setText(String.format("%.2f", total));

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
                builder.setPositiveButton("ADICIONAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Entretenimento_Modelo model = new Entretenimento_Modelo();

                        if(nomeT.getText().toString().isEmpty()){
                        } else if(custoT.toString().isEmpty()) {
                        }else{
                            model.setNome(nomeT.getText().toString());
                            model.setPreço(Float.parseFloat(custoT.getText().toString()));

                            total = Float.parseFloat(custoTotal.getText().toString().replace(",", "."));
                            total += Float.parseFloat(custoT.getText().toString());

                            custoTotal.setText(String.format("%.2f", total));

                            listEn.add(model);
                            adapter.notifyDataSetChanged();
                        }
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
                ArrayList<EntretenimentoModel> listaEn = new ArrayList<>();
                if(listEn.size() > 0){
                    for(int i = 0; i < listEn.size(); i++){
                        EntretenimentoModel model = new EntretenimentoModel();

                        model.setNome(listEn.get(i).getNome());
                        model.setPreco(listEn.get(i).getPreço());

                        listaEn.add(model);
                        }
                    }
                Intent it = new Intent();

                it.putExtra("ENTRETENIMENTO", listaEn);

                setResult(1, it);
                finish();
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(9);
                finish();
            }
        });
    }
}
