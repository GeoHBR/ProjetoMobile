package com.example.projetomobile;

import androidx.appcompat.app.AppCompatActivity;

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
    private ImageView voltar;
    private ImageView cancelar;
    private ImageView salvar;
    SharedPreferences preferences;
    private float totalC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospedagem);

        preferences = PreferenceManager.getDefaultSharedPreferences(Hospedagem.this);
        SharedPreferences.Editor edit = preferences.edit();

        usuario = findViewById(R.id.usuarioHospedagem);
        custoNoite = findViewById(R.id.custoNoiteHospedagem);
        quantNoite = findViewById(R.id.totalNoitesHospedagem);
        quantQuarto = findViewById(R.id.totalQuartoHospedagem);
        total = findViewById(R.id.totalHospedagem);
        voltar = findViewById(R.id.voltarHospedagem);
        cancelar = findViewById(R.id.cancelarHospedagem);
        salvar = findViewById(R.id.salvarHospedagem);

        HospedagemDAO dao = new HospedagemDAO(Hospedagem.this);
        int hospedagem = preferences.getInt("KEY_ID_HOSPEDAGEM", 0);

        if(hospedagem > 0){
            HospedagemModel aux;
            aux = dao.Select(hospedagem);

            total.setText(Float.toString(aux.getTotal()));
            custoNoite.setText(Float.toString(aux.getCustoMedio()));
            quantNoite.setText(Integer.toString(aux.getTotalNoites()));
            quantQuarto.setText(Integer.toString(aux.getTotalQuartos()));
        }

        usuario.setText(preferences.getString("KEY_NOME", null));

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                    if(hospedagem > 0){

                        model.setCustoMedio(Float.parseFloat(custoNoite.getText().toString()));
                        model.setTotalQuartos(Integer.parseInt(quantQuarto.getText().toString()));
                        model.setTotalNoites(Integer.parseInt(quantNoite.getText().toString()));
                        model.setTotal(totalC);

                        dao.Update(hospedagem, model);
                    }else{

                        model.setCustoMedio(Float.parseFloat(custoNoite.getText().toString()));
                        model.setTotalQuartos(Integer.parseInt(quantQuarto.getText().toString()));
                        model.setTotalNoites(Integer.parseInt(quantNoite.getText().toString()));
                        model.setTotal(totalC);

                        int id = dao.Insert(model);

                        edit.putInt("KEY_ID_HOSPEDAGEM", id).apply();
                    }
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

            total.setText(Float.toString(totalC));
        }
    }
}