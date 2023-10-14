package com.example.projetomobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.projetomobile.database.dao.UsuarioDAO;

import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class AdicionarViagem extends AppCompatActivity {

    private EditText destino;
    private EditText dateEditText;
    private EditText dateEditText2;
    private EditText quantViajantes;
    private TextView gasolina;
    private TextView tarifaAerea;
    private TextView refeicao;
    private TextView hospedagem;
    private TextView entretenimento;
    private ImageView criar;
    private TextView userNome;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_viagem);

        preferences = PreferenceManager.getDefaultSharedPreferences(AdicionarViagem.this);

        userNome = findViewById(R.id.nomeUser);
        destino = findViewById(R.id.destino);
        dateEditText = findViewById(R.id.dateEditText);
        dateEditText2 = findViewById(R.id.dateEditText2);
        quantViajantes = findViewById(R.id.quantViajantes);
        gasolina = findViewById(R.id.btnGasolina);
        tarifaAerea = findViewById(R.id.btnTarifa);
        refeicao = findViewById(R.id.btnRefeicao);
        hospedagem = findViewById(R.id.btnHospedagem);
        entretenimento = findViewById(R.id.btnEntretenimento);
        criar = findViewById(R.id.btnSalvarViagem);

        userNome.setText(preferences.getString("KEY_NOME", null));

        // TextWatcher para o primeiro EditText
        dateEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                formatInput(s);
            }
        });

        // TextWatcher para o segundo EditText
        dateEditText2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                formatInput(s);
            }
        });

        gasolina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdicionarViagem.this, Gasolina.class));
            }
        });
        hospedagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    startActivity(new Intent(AdicionarViagem.this, Hospedagem.class));
            }
        });
        tarifaAerea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(quantViajantes.getText().toString().isEmpty()) {
                    quantViajantes.setError("Preencha este campo primeiro");
                }else{
                    Intent intent = new Intent(AdicionarViagem.this, TarifaAreaActivity.class);
                    intent.putExtra("QUANT_VIAJANTES", Integer.parseInt(quantViajantes.getText().toString()));

                    startActivity(intent);
                }
            }
        });
        refeicao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(quantViajantes.getText().toString().isEmpty()) {
                    quantViajantes.setError("Preencha este campo primeiro");
                }else if(dateEditText.getText().toString().isEmpty()){
                    dateEditText.setError("Preencha este campo primeiro");
                }else if(dateEditText2.getText().toString().isEmpty()){
                    dateEditText2.setError("Preencha este campo primeiro");
                }else{
                    Intent intent = new Intent(AdicionarViagem.this, TarifaAreaActivity.class);
                    intent.putExtra("QUANT_VIAJANTES", Integer.parseInt(quantViajantes.getText().toString()));

                    startActivity(intent);
                }
            }
        });
    }
    private void formatInput(Editable s) {
        String input = s.toString();
        if (input.length() == 2 && s.charAt(1) != '/') {
            s.insert(2, "/");
        } else if (input.length() == 5 && s.charAt(4) != '/') {
            s.insert(5, "/");
        } else if (input.length() > 10) {
            s.delete(10, s.length());
        }
    }
}