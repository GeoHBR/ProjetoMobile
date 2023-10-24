package com.example.projetomobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projetomobile.database.dao.RefeicaoDAO;
import com.example.projetomobile.database.model.RefeicaoModel;

public class Refeicoes extends AppCompatActivity {

    private TextView usuario;
    private TextView custoTotal;
    private EditText custoRefeicao;
    private EditText quantRefeicao;
    private TextView txt_viajante;
    private ImageView cancelar;
    private ImageView salvar;
    SharedPreferences preferences;
    private float precoTotal;
    private int viajantes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refeicoes);

        preferences = PreferenceManager.getDefaultSharedPreferences(Refeicoes.this);
        SharedPreferences.Editor edit = preferences.edit();

        Intent intent = getIntent();
        viajantes = intent.getIntExtra("QUANT_VIAJANTES", 0);

        usuario = findViewById(R.id.usuarioRefeicao);
        custoTotal = findViewById(R.id.totalRefeicao);
        quantRefeicao = findViewById(R.id.quantRefeicao);
        custoRefeicao = findViewById(R.id.custoRefeicao);
        cancelar = findViewById(R.id.cancelarRefeicao);
        salvar = findViewById(R.id.salvarRefeicao);
        txt_viajante = findViewById(R.id.txt_refeicoes_viajantes);

        RefeicaoDAO dao = new RefeicaoDAO(Refeicoes.this);

        int refeicao = preferences.getInt("KEY_ID_REFEICAO", 0);
        if(refeicao > 0){
            RefeicaoModel aux;
            aux = dao.Select(refeicao);

            custoTotal.setText(String.format("%.2f",aux.getTotal()));
            quantRefeicao.setText(Integer.toString(aux.getQuantRefeicao()));
            custoRefeicao.setText(Float.toString(aux.getCustoRefeicao()));
        }

        usuario.setText(preferences.getString("KEY_NOME", null));
        txt_viajante.setText("Valor de "+viajantes+" viajantes por dia");
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(9);
                finish();
            }
        });
        quantRefeicao.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                float custoRefeicaoC = Float.parseFloat(custoRefeicao.getText().toString());
                int quantRefeicaoC = Integer.parseInt(quantRefeicao.getText().toString());

                float custoPessoa = (quantRefeicaoC * viajantes) * custoRefeicaoC;

                custoTotal.setText(String.format("%.2f",custoPessoa));
            }
        });
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(custoRefeicao.getText().toString().isEmpty()){
                    custoRefeicao.setError("Campo obrigatorio");
                }else if(quantRefeicao.getText().toString().isEmpty()){
                    quantRefeicao.setError("Campo obrigatorio");
                }else{
                    calcularTotal(intent);
                    RefeicaoModel model = new RefeicaoModel();

                    model.setCustoRefeicao(Float.parseFloat(custoRefeicao.getText().toString()));
                    model.setQuantRefeicao(Integer.parseInt(quantRefeicao.getText().toString()));
                    model.setTotal(precoTotal);

                    if(refeicao > 0){
                        dao.Update(refeicao, model);
                    }else{
                        int id = dao.Insert(model);
                        edit.putInt("KEY_ID_REFEICAO", id).apply();
                    }
                    setResult(1);
                    finish();
                }
            }
        });
    }
    private void calcularTotal(Intent intent){
        if(custoRefeicao.getText().toString().isEmpty()){
        }else if(quantRefeicao.getText().toString().isEmpty()){
        }else{
            float custoRefeicaoC = Float.parseFloat(custoRefeicao.getText().toString());
            int quantRefeicaoC = Integer.parseInt(quantRefeicao.getText().toString());
            int duracao = intent.getIntExtra("DURACAO", 0);

            precoTotal = ((quantRefeicaoC * viajantes) * custoRefeicaoC) * duracao;
        }
    }
}