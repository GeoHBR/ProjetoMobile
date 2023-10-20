package com.example.projetomobile;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.projetomobile.database.dao.EntretenimentoDAO;
import com.example.projetomobile.database.dao.GasolinaDAO;
import com.example.projetomobile.database.dao.HospedagemDAO;
import com.example.projetomobile.database.dao.RefeicaoDAO;
import com.example.projetomobile.database.dao.TarifaDAO;
import com.example.projetomobile.database.dao.ViagemDAO;
import com.example.projetomobile.database.model.TarifaModel;
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
    private ImageView cancelar;
    SharedPreferences preferences;
    private SharedPreferences.Editor edit;
    private ViagemDAO dao;
    private boolean update;
    private int idViagem;

    private static final int TELA_GASOLINA = 1;
    private static final int TELA_TARIFA = 2;
    private static final int TELA_REFEICOES = 3;
    private static final int TELA_HOSPEDAGEM = 4;
    private static final int TELA_ENTRETERIMENTO = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_viagem);

        preferences = PreferenceManager.getDefaultSharedPreferences(AdicionarViagem.this);
        edit = preferences.edit();

        Intent intent = getIntent();

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
        cancelar = findViewById(R.id.btnCancelarViagem);

        userNome.setText(preferences.getString("KEY_NOME", null));

        idViagem = intent.getIntExtra("ID_VIAGEM", 0);
        if(idViagem == 0){
            dao = new ViagemDAO(AdicionarViagem.this);
            ViagemModel model = new ViagemModel();

            model.setDestino("");
            model.setDataInicio("");
            model.setDataFim("");
            model.setQuantPessoas(0);

            idViagem = dao.Insert(model);
            update = false;

        }else{
            ViagemModel viagem = new ViagemModel();
            dao = new ViagemDAO(AdicionarViagem.this);
            viagem = dao.SelectViagem(idViagem);

            destino.setText(viagem.getDestino());
            dateInicio.setText(viagem.getDataInicio());
            dateFim.setText(viagem.getDataInicio());
            quantViajantes.setText(Integer.toString(viagem.getQuantPessoas()));

            update = true;
        }

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
                //startActivity(new Intent(AdicionarViagem.this, Gasolina.class));
                startActivityForResult(new Intent(AdicionarViagem.this, Gasolina.class), TELA_GASOLINA);
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
                    startActivityForResult(new Intent(AdicionarViagem.this, TarifaAreaActivity.class), TELA_TARIFA);
                    Intent intent = new Intent();
                    intent.putExtra("QUANT_VIAJANTES", Integer.parseInt(quantViajantes.getText().toString()));
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
                    startActivityForResult(new Intent(AdicionarViagem.this, Refeicoes.class), TELA_REFEICOES);
                    Intent intent = new Intent();
                    intent.putExtra("QUANT_VIAJANTES", Integer.parseInt(quantViajantes.getText().toString()));
                    intent.putExtra("DURACAO", diferencaData());
                }
            }
        });

        entretenimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(AdicionarViagem.this, Entretenimento.class);
                intent1.putExtra("KEY_ID", idViagem);
                startActivity(intent1);
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

                    dao = new ViagemDAO(AdicionarViagem.this);
                    ViagemModel model = new ViagemModel();

                    model.set_id(idViagem);
                    model.setDestino(destino.getText().toString());
                    model.setDataInicio(dateInicio.getText().toString());
                    model.setDataFim(dateFim.getText().toString());
                    model.set_idUsuario(preferences.getInt("KEY_ID", 0));
                    model.setQuantPessoas(Integer.parseInt(quantViajantes.getText().toString()));
                    model.set_idGasolina(preferences.getInt("KEY_ID_GASOLINA", 0));
                    model.set_idHospedagem(preferences.getInt("KEY_ID_HOSPEDAGEM", 0));
                    model.set_idTarifa(preferences.getInt("KEY_ID_TARIFA", 0));
                    model.set_idRefeicao(preferences.getInt("KEY_ID_REFEICAO", 0));

                    removerPreferences();

                    dao.Update(model);
                    finish();
                }
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(update){

                }else{
                    if(preferences.getInt("KEY_ID_GASOLINA", 0) > 0){
                        GasolinaDAO dao = new GasolinaDAO(AdicionarViagem.this);
                        dao.Delete(preferences.getInt("KEY_ID_GASOLINA", 0));
                    }
                    if(preferences.getInt("KEY_ID_HOSPEDAGEM", 0) > 0){
                        HospedagemDAO dao = new HospedagemDAO(AdicionarViagem.this);
                        dao.Delete(preferences.getInt("KEY_ID_HOSPEDAGEM", 0));
                    }
                    if(preferences.getInt("KEY_ID_TARIFA", 0) > 0){
                        TarifaDAO dao = new TarifaDAO(AdicionarViagem.this);
                        dao.Delete(preferences.getInt("KEY_ID_TARIFA", 0));
                    }
                    if(preferences.getInt("KEY_ID_REFEICAO", 0) > 0){
                        RefeicaoDAO dao = new RefeicaoDAO(AdicionarViagem.this);
                        dao.Delete(preferences.getInt("KEY_ID_REFEICAO", 0));
                    }
                    EntretenimentoDAO daoE = new EntretenimentoDAO(AdicionarViagem.this);
                    dao = new ViagemDAO(AdicionarViagem.this);
                    daoE.Delete(dao.SelectTotal(preferences.getInt("KEY_ID", 0)) + 1);
                }

                removerPreferences();
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        /*int i= preferences.getInt("KEY_ID_GASOLINA",0);

        if(i>0){
            gasolina.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.icone_adicionado,0);
        }
        int x= preferences.getInt("KEY_ID_TARIFA",0);

        if(x>0){
            tarifaAerea.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.icone_adicionado,0);
        }*/

        super.onResume();
    }

    private void removerPreferences(){
        edit.remove("KEY_ID_GASOLINA").apply();
        edit.remove("KEY_ID_HOSPEDAGEM").apply();
        edit.remove("KEY_ID_TARIFA").apply();
        edit.remove("KEY_ID_REFEICAO").apply();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TELA_GASOLINA) {
            if (resultCode == 1) {
                gasolina.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.icone_adicionado,0);
            }
        }
        if (requestCode == TELA_TARIFA) {
            if (resultCode == 1) {
                tarifaAerea.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.icone_adicionado,0);
            }
        }
        if (requestCode == TELA_REFEICOES) {
            if (resultCode == 1) {
                refeicao.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.icone_adicionado,0);
            }
        }
        if (requestCode == TELA_HOSPEDAGEM) {
            if (resultCode == 1) {
                hospedagem.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.icone_adicionado,0);
            }
        }
        if (requestCode == TELA_ENTRETERIMENTO) {
            if (resultCode == 1) {
                entretenimento.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.icone_adicionado,0);
            }
        }
    }
}