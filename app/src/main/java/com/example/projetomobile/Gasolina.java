package com.example.projetomobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projetomobile.database.dao.GasolinaDAO;
import com.example.projetomobile.database.model.GasolinaModel;

import java.io.Serializable;

public class Gasolina extends AppCompatActivity {

    private TextView usuario;
    private TextView custoTotal;
    private EditText totalKM;
    private EditText mediaKMLitro;
    private EditText precoGasolina;
    private EditText quantVeiculos;
    private ImageView cancelar;
    private ImageView salvar;
    SharedPreferences preferences;
    private float precoTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gasolina);

        usuario = findViewById(R.id.usuarioGasolina);
        custoTotal = findViewById(R.id.txtCustoTotalGasolina);
        totalKM = findViewById(R.id.totalKMGasolina);
        mediaKMLitro = findViewById(R.id.mediaKMGasolina);
        precoGasolina = findViewById(R.id.custoGasolina);
        quantVeiculos = findViewById(R.id.totalVeiculoGasolina);
        cancelar = findViewById(R.id.cancelarGasolina);
        salvar = findViewById(R.id.addGasolina);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        Intent intent = getIntent();

        boolean edicao = intent.getBooleanExtra("EDICAO", false);

        if(edicao){
            GasolinaModel aux = (GasolinaModel) intent.getSerializableExtra("GASOLINA");

            totalKM.setText(Float.toString(aux.getTotalKM()));
            mediaKMLitro.setText(Float.toString(aux.getMedialKM()));
            precoGasolina.setText(Float.toString(aux.getCustoMedio()));
            quantVeiculos.setText(Integer.toString(aux.getTotalVeiculo()));
            custoTotal.setText(Float.toString(aux.getTotal()));

        }

        usuario.setText(preferences.getString("KEY_NOME", null));

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(9);
                finish();
            }
        });

        quantVeiculos.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void afterTextChanged(Editable editable) {
                calcularTotal();
            }
        });

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(totalKM.getText().toString().isEmpty()){
                    totalKM.setError("Campo Obrigatorio");
                } else if (mediaKMLitro.getText().toString().isEmpty()) {
                    mediaKMLitro.setError("Campo Obrigatorio");
                }else if (precoGasolina.getText().toString().isEmpty()) {
                    precoGasolina.setError("Campo Obrigatorio");
                }else if (quantVeiculos.getText().toString().isEmpty()) {
                    quantVeiculos.setError("Campo Obrigatorio");
                }else{
                    calcularTotal();

                    GasolinaModel model = new GasolinaModel();

                    model.setTotalKM(Float.parseFloat(totalKM.getText().toString()));
                    model.setMedialKM(Float.parseFloat(mediaKMLitro.getText().toString()));
                    model.setCustoMedio(Float.parseFloat(precoGasolina.getText().toString()));
                    model.setTotalVeiculo(Integer.parseInt(quantVeiculos.getText().toString()));
                    model.setTotal(precoTotal);

                    Intent it = new Intent();

                    it.putExtra("GASOLINA", model);

                    setResult(1, it);
                    finish();
                }
            }
        });
    }
    private void calcularTotal(){
        if(totalKM.getText().toString().isEmpty() ){
        } else if (mediaKMLitro.getText().toString().isEmpty()) {
        }else if (precoGasolina.getText().toString().isEmpty()) {
        }else if (quantVeiculos.getText().toString().isEmpty()) {
        }else {
            float totalKm = Float.parseFloat(totalKM.getText().toString());
            float media = Float.parseFloat(mediaKMLitro.getText().toString());
            float gasolina = Float.parseFloat(precoGasolina.getText().toString());
            int totalVeiculos = Integer.parseInt(quantVeiculos.getText().toString());
            precoTotal = ((totalKm / media) * gasolina) / totalVeiculos;
            custoTotal.setText(String.format("%.2f", precoTotal));
        }
    }
}