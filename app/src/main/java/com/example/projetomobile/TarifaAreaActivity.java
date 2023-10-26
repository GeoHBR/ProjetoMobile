package com.example.projetomobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projetomobile.database.dao.TarifaDAO;
import com.example.projetomobile.database.model.TarifaModel;

public class TarifaAreaActivity extends AppCompatActivity {

    private TextView usuario;
    private TextView custoTotal;
    private EditText custoPessoa;
    private EditText alugelVeiculo;
    private ImageView cancelar;
    private ImageView salvar;
    SharedPreferences preferences;
    private float precoTotal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarifa_aerea);

        preferences = PreferenceManager.getDefaultSharedPreferences(TarifaAreaActivity.this);

        Intent intent = getIntent();

        usuario = findViewById(R.id.usuarioTarifa);
        custoTotal = findViewById(R.id.totalTarifa);
        custoPessoa = findViewById(R.id.custoPessoaTarifa);
        alugelVeiculo = findViewById(R.id.aluguelTarifa);
        cancelar = findViewById(R.id.cancelarTarifa);
        salvar = findViewById(R.id.salvarTarifa);

        boolean edicao = intent.getBooleanExtra("EDICAO", false);

        if(edicao){
            TarifaModel aux = (TarifaModel) intent.getSerializableExtra("TARIFA");

            custoTotal.setText(Float.toString(aux.getTotal()));
            custoPessoa.setText(Float.toString(aux.getCustoPessoa()));
            alugelVeiculo.setText(Float.toString(aux.getCustoAluguel()));
        }

        usuario.setText(preferences.getString("KEY_NOME", null));

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(9);
                finish();
            }
        });

        alugelVeiculo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                calcularTotal(intent);
            }
        });
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(custoPessoa.getText().toString().isEmpty()){
                    custoPessoa.setError("Campo Obrigatorio");
                }else if(alugelVeiculo.getText().toString().isEmpty()){
                    alugelVeiculo.setError("Campo Obrigatorio");
                }else{
                    calcularTotal(intent);
                    TarifaModel model = new TarifaModel();

                    model.setCustoAluguel(Float.parseFloat(alugelVeiculo.getText().toString()));
                    model.setCustoPessoa(Float.parseFloat(custoPessoa.getText().toString()));
                    model.setTotal(precoTotal);

                    Intent it = new Intent();

                    it.putExtra("TARIFA", model);

                    setResult(1, it);

                    finish();
                }
            }
        });

        alugelVeiculo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b) {
                    if (alugelVeiculo.getText().toString().equals("")) {
                        alugelVeiculo.setText("0.0");
                    }
                } else if(alugelVeiculo.getText().toString().equals("")) {
                    alugelVeiculo.setText("0.0");
                } else if(Float.parseFloat(alugelVeiculo.getText().toString()) == 0) {
                    alugelVeiculo.setText("");
                }
            }
        });
        custoPessoa.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b) {
                    if (custoPessoa.getText().toString().equals("")) {
                        custoPessoa.setText("0.0");
                    }
                } else if(custoPessoa.getText().toString().equals("")) {
                    custoPessoa.setText("0.0");
                } else if(Float.parseFloat(custoPessoa.getText().toString()) == 0) {
                    custoPessoa.setText("");
                }
            }
        });
    }

    private void calcularTotal(Intent intent){
        if(custoPessoa.getText().toString().isEmpty()){
        }else if(alugelVeiculo.getText().toString().isEmpty()){
        }else{
            float custoPessoaC = Float.parseFloat(custoPessoa.getText().toString());
            float veiculo = Float.parseFloat(alugelVeiculo.getText().toString());
            int viajantes = intent.getIntExtra("QUANT_VIAJANTES", 0);
            precoTotal = (custoPessoaC * viajantes) + veiculo;

            custoTotal.setText(String.format("%.2f", precoTotal));

        }
    }
}