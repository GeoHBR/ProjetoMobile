package com.example.projetomobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.projetomobile.database.dao.UsuarioDAO;
import com.example.projetomobile.database.dao.ViagemDAO;
import com.example.projetomobile.database.model.ViagemModel;

import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;

public class AdicionarViagem extends AppCompatActivity {

    private EditText destino;
    private EditText dateInicio;
    private EditText dateFim;
    private EditText quantViajantes;
    private TextView gasolina;
    private TextView tarifaAerea;
    private TextView refeicao;
    private TextView hospedagem;
    private TextView entretenimento;
    private ImageView criar;
    private TextView userNome;
    private ImageButton btnAddViagem;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_viagem);

        preferences = PreferenceManager.getDefaultSharedPreferences(AdicionarViagem.this);
        SharedPreferences.Editor edit = preferences.edit();

        userNome = findViewById(R.id.nomeUser);
        destino = findViewById(R.id.destino);
        dateInicio = findViewById(R.id.dateEditText);
        dateFim = findViewById(R.id.dateEditText2);
        quantViajantes = findViewById(R.id.quantViajantes);
        gasolina = findViewById(R.id.btnGasolina);
        tarifaAerea = findViewById(R.id.btnTarifa);
        refeicao = findViewById(R.id.btnRefeicao);
        hospedagem = findViewById(R.id.btnHospedagem);
        entretenimento = findViewById(R.id.btnEntretenimento);
        criar = findViewById(R.id.btnSalvarViagem);

        userNome.setText(preferences.getString("KEY_NOME", null));

        // TextWatcher para o primeiro EditText
        dateInicio.addTextChangedListener(new TextWatcher() {
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
        dateFim.addTextChangedListener(new TextWatcher() {
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
                }else if(dateInicio.getText().toString().isEmpty()){
                    dateInicio.setError("Preencha este campo primeiro");
                }else if(dateFim.getText().toString().isEmpty()){
                    dateFim.setError("Preencha este campo primeiro");
                }else{
                    Intent intent = new Intent(AdicionarViagem.this, Refeicoes.class);
                    intent.putExtra("QUANT_VIAJANTES", Integer.parseInt(quantViajantes.getText().toString()));
                    intent.putExtra("DURACAO", diferencaData());
                    startActivity(intent);
                }
            }
        });

        entretenimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdicionarViagem.this, Entreterimento.class));
            }
        });

        criar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(destino.getText().toString().isEmpty()){
                    destino.setError("Campo Obrigatorio");
                }else if(dateInicio.getText().toString().isEmpty()){
                    dateInicio.setError("Campo Obrigatorio");
                }else if(dateFim.getText().toString().isEmpty()){
                    dateFim.setError("Campo Obrigatorio");
                }else if(quantViajantes.getText().toString().isEmpty()) {
                    quantViajantes.setError("Campo Obrigatorio");
                }else{

                    ViagemDAO dao = new ViagemDAO(AdicionarViagem.this);
                    ViagemModel model = new ViagemModel();

                    model.setDestino(destino.getText().toString());
                    model.setDataInicio(dateInicio.getText().toString());
                    model.setDataFim(dateFim.getText().toString());
                    model.set_idUsuario(preferences.getInt("KEY_ID", 0));
                    model.setQuantPessoas(Integer.parseInt(quantViajantes.getText().toString()));
                    if(preferences.contains("KEY_ID_GASOLINA")){
                        model.set_idGasolina(preferences.getInt("KEY_ID_GASOLINA", 0));
                        edit.remove("KEY_ID_GASOLINA").apply();
                    }
                    if(preferences.contains("KEY_ID_HOSPEDAGEM")){
                        model.set_idHospedagem(preferences.getInt("KEY_ID_HOSPEDAGEM", 0));
                        edit.remove("KEY_ID_HOSPEDAGEM").apply();
                    }
                    if(preferences.contains("KEY_ID_TARIFA")){
                        model.set_idTarifa(preferences.getInt("KEY_ID_TARIFA", 0));
                        edit.remove("KEY_ID_TARIFA").apply();
                    }
                    if(preferences.contains("KEY_ID_REFEICAO")){
                        model.set_idRefeicao(preferences.getInt("KEY_ID_REFEICAO", 0));
                        edit.remove("KEY_ID_REFEICAO").apply();
                    }
                    dao.Insert(model);
                    finish();
                }
            }
        });
    }

    public int diferencaData() {
        int dataInicio = conrveteData(dateInicio.getText().toString());
        int dataFim = conrveteData(dateFim.getText().toString());
        return dataFim - dataInicio;
    }

    public int conrveteData(String data) {
        Date date = converterStringParaDate(data);

        Calendar calendario = Calendar.getInstance();
        Calendar meses =  Calendar.getInstance();

        calendario.setTime(date);

        int dias=0;
        int ano = calendario.get(Calendar.YEAR);
        int mes = calendario.get(Calendar.MONTH);
        int dia = calendario.get(Calendar.DAY_OF_MONTH);
        int mesDia=0;

        for(int i=mes+1; i>0; i--) {
            meses.set(ano, i -1,1);
            mesDia = meses.getActualMaximum(Calendar.DAY_OF_MONTH);
            dias += mesDia;
        }

        meses.set(ano, 2 -1,1);

        int anosBis = ((ano-2000) / 4)+1;
        dias = anosBis * 366;
        dias += 365*(ano-2000-anosBis);
        dias +=dia;

        return dias;
    }

    public static Date converterStringParaDate(String dateString) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
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