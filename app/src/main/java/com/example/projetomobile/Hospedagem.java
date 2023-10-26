package com.example.projetomobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projetomobile.database.dao.HospedagemDAO;
import com.example.projetomobile.database.model.HospedagemModel;

public class Hospedagem extends AppCompatActivity {

    private TextView usuario;
    private TextView total;
    private EditText custoNoite;
    private EditText quantNoite;
    private EditText quantQuarto;
    private ImageView cancelar;
    private ImageView salvar;
    SharedPreferences preferences;
    private float totalC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospedagem);

        preferences = PreferenceManager.getDefaultSharedPreferences(Hospedagem.this);

        usuario = findViewById(R.id.usuarioHospedagem);
        custoNoite = findViewById(R.id.custoNoiteHospedagem);
        quantNoite = findViewById(R.id.totalNoitesHospedagem);
        quantQuarto = findViewById(R.id.totalQuartoHospedagem);
        total = findViewById(R.id.totalHospedagem);
        cancelar = findViewById(R.id.cancelarHospedagem);
        salvar = findViewById(R.id.salvarHospedagem);

        Intent intent = getIntent();
        boolean edicao = intent.getBooleanExtra("EDICAO", false);

        if(edicao){
            HospedagemModel aux = (HospedagemModel) intent.getSerializableExtra("HOSPEDAGEM");

            total.setText(Float.toString(aux.getTotal()));
            custoNoite.setText(Float.toString(aux.getCustoMedio()));
            quantNoite.setText(Integer.toString(aux.getTotalNoites()));
            quantQuarto.setText(Integer.toString(aux.getTotalQuartos()));
        }

        usuario.setText(preferences.getString("KEY_NOME", null));

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(9);
                finish();
            }
        });

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (custoNoite.getText().toString().isEmpty()) {
                    custoNoite.setError("Campo Obrigatorio");
                } else if (quantNoite.getText().toString().isEmpty()) {
                    quantNoite.setError("Campo Obrigatorio");
                }else if (quantQuarto.getText().toString().isEmpty()) {
                    quantQuarto.setError("Campo Obrigatorio");
                }else{
                    calcularTotal();
                    HospedagemModel model = new HospedagemModel();

                    model.setCustoMedio(Float.parseFloat(custoNoite.getText().toString()));
                    model.setTotalQuartos(Integer.parseInt(quantQuarto.getText().toString()));
                    model.setTotalNoites(Integer.parseInt(quantNoite.getText().toString()));
                    model.setTotal(totalC);

                    Intent it = new Intent();

                    it.putExtra("HOSPEDAGEM", model);

                    setResult(1, it);
                    finish();
                }
            }
        });

        quantQuarto.addTextChangedListener(new TextWatcher() {
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

        custoNoite.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b) {
                    if (custoNoite.getText().toString().equals("")) {
                        custoNoite.setText("0.0");
                    }
                } else if(custoNoite.getText().toString().equals("")) {
                    custoNoite.setText("0.0");
                } else if(Float.parseFloat(custoNoite.getText().toString()) == 0) {
                    custoNoite.setText("");
                }
            }
        });

        quantNoite.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b) {
                    if (quantNoite.getText().toString().equals("")) {
                        quantNoite.setText("0");
                    }
                } else if(quantNoite.getText().toString().equals("")) {
                    quantNoite.setText("0");
                } else if(Integer.parseInt(quantNoite.getText().toString()) == 0) {
                    quantNoite.setText("");
                }
            }
        });

        quantQuarto.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b) {
                    if (quantQuarto.getText().toString().equals("")) {
                        quantQuarto.setText("0");
                    }
                } else if(quantQuarto.getText().toString().equals("")) {
                    quantQuarto.setText("0");
                } else if(Integer.parseInt(quantQuarto.getText().toString()) == 0) {
                    quantQuarto.setText("");
                }
            }
        });

    }

    public void calcularTotal(){
        if (custoNoite.getText().toString().isEmpty()) {
        } else if (quantNoite.getText().toString().isEmpty()) {
        }else if (quantQuarto.getText().toString().isEmpty()) {
        }else{
            float custoNoiteC = Float.parseFloat(custoNoite.getText().toString());
            int quantQuartoC = Integer.parseInt(quantQuarto.getText().toString());
            int numNoite = Integer.parseInt(quantNoite.getText().toString());
            totalC = (custoNoiteC * numNoite) * quantQuartoC;

            total.setText(String.format("%.2f", totalC));
        }
    }
}