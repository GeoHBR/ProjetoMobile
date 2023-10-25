package com.example.projetomobile;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.projetomobile.database.dao.EntretenimentoDAO;
import com.example.projetomobile.database.dao.GasolinaDAO;
import com.example.projetomobile.database.dao.HospedagemDAO;
import com.example.projetomobile.database.dao.RefeicaoDAO;
import com.example.projetomobile.database.dao.TarifaDAO;
import com.example.projetomobile.database.dao.ViagemDAO;
import com.example.projetomobile.database.model.EntretenimentoModel;
import com.example.projetomobile.database.model.GasolinaModel;
import com.example.projetomobile.database.model.HospedagemModel;
import com.example.projetomobile.database.model.RefeicaoModel;
import com.example.projetomobile.database.model.TarifaModel;
import com.example.projetomobile.database.model.ViagemModel;

import android.preference.PreferenceManager;
import android.text.Editable;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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
    private TextView TxtTotalViagem;
    SharedPreferences preferences;
    private SharedPreferences.Editor edit;
    private ViagemDAO dao;
    private boolean update;
    private int idViagem;

    private static final int TELA_GASOLINA = 1;
    private static final int TELA_TARIFA = 2;
    private static final int TELA_REFEICOES = 3;
    private static final int TELA_HOSPEDAGEM = 4;
    private static final int TELA_ENTRETENIMENTO = 5;
    private ViagemModel viagem = new ViagemModel();
    private GasolinaModel gasoModel;
    private HospedagemModel hopModel;
    private RefeicaoModel refModel;
    private TarifaModel tarModel;
    private ArrayList<EntretenimentoModel> listaE;
    private float totalViagem;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        GasolinaDAO gasoDAO = new GasolinaDAO(AdicionarViagem.this);
        gasoModel= new GasolinaModel();

        HospedagemDAO hopDAO = new HospedagemDAO(AdicionarViagem.this);
        hopModel = new HospedagemModel();

        RefeicaoDAO refDAO = new RefeicaoDAO(AdicionarViagem.this);
        refModel = new RefeicaoModel();

        TarifaDAO tarDAO = new TarifaDAO(AdicionarViagem.this);
        tarModel = new TarifaModel();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_viagem);

        preferences = PreferenceManager.getDefaultSharedPreferences(AdicionarViagem.this);
        edit = preferences.edit();

        Intent intent = getIntent();

        calendar = Calendar.getInstance();
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
        TxtTotalViagem = findViewById(R.id.txtCustoTotalViagens);

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

            dao = new ViagemDAO(AdicionarViagem.this);
            viagem = dao.SelectViagem(idViagem);

            destino.setText(viagem.getDestino());
            dateInicio.setText(viagem.getDataInicio());
            dateFim.setText(viagem.getDataFim());
            quantViajantes.setText(Integer.toString(viagem.getQuantPessoas()));

            edit.putInt("KEY_ID_GASOLINA", viagem.get_idGasolina());
            edit.putInt("KEY_ID_HOSPEDAGEM", viagem.get_idHospedagem());
            edit.putInt("KEY_ID_REFEICAO", viagem.get_idRefeicao());
            edit.putInt("KEY_ID_TARIFA", viagem.get_idTarifa());
            edit.apply();

            gasoModel = gasoDAO.Select(viagem.get_idGasolina());
            hopModel = hopDAO.Select(viagem.get_idHospedagem());
            refModel = refDAO.Select(viagem.get_idRefeicao());
            tarModel = tarDAO.Select(viagem.get_idTarifa());

            EntretenimentoDAO dao = new EntretenimentoDAO(AdicionarViagem.this);
            listaE = dao.Select(viagem.get_id());

            update = true;
        }

//        // TextWatcher para o primeiro EditText
//        dateInicio.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                formatInput(s);
//            }
//        });
//
//        // TextWatcher para o segundo EditText
//        dateFim.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                formatInput(s);
//            }
//        });

        //Calendario pra dateInicio
        dateInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
            private void showDatePicker() {
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(AdicionarViagem.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, monthOfYear);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                        String selectedDate = dateFormat.format(calendar.getTime());
                        dateInicio.setText(selectedDate);
                    }
                }, year, month, day);

                datePickerDialog.show();
            }
        });

        //Calendario pro dateFim
        dateFim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
            private void showDatePicker() {
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(AdicionarViagem.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, monthOfYear);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                        String selectedDate = dateFormat.format(calendar.getTime());
                        dateFim.setText(selectedDate);
                    }
                }, year, month, day);

                datePickerDialog.show();
            }
        });

        gasolina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(AdicionarViagem.this, Gasolina.class), TELA_GASOLINA);
            }
        });
        hospedagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(AdicionarViagem.this, Hospedagem.class), TELA_HOSPEDAGEM);
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
                    startActivityForResult( intent, TELA_TARIFA);

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
                    startActivityForResult( intent, TELA_REFEICOES);

                }
            }
        });

        entretenimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(AdicionarViagem.this, Entretenimento.class);
                intent1.putExtra("KEY_ID", idViagem);
                startActivityForResult( intent1, TELA_ENTRETENIMENTO);
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
                    gasoDAO.Update(viagem.get_idGasolina(), gasoModel);
                    hopDAO.Update(viagem.get_idHospedagem(), hopModel);
                    refDAO.Update(viagem.get_idRefeicao(), refModel);
                    tarDAO.Update(viagem.get_idHospedagem(), tarModel);

                    EntretenimentoDAO entDAO = new EntretenimentoDAO(AdicionarViagem.this);
                    ArrayList<EntretenimentoModel> listaENew;
                    ArrayList<EntretenimentoModel> listaCancelarDEL;
                    ArrayList<EntretenimentoModel> listaCancelarINS;

                    listaCancelarDEL = entDAO.Select(viagem.get_id());
                    listaCancelarINS = listaE;


                    for(int i = 0; i < listaCancelarDEL.size(); i++){
                        for(int j=0; j < listaCancelarINS.size(); j++) {
                            EntretenimentoModel modelo;
                            modelo = listaCancelarINS.get(j);

                            if(listaCancelarDEL.get(i).get_id() == modelo.get_id()) {
                                listaCancelarINS.remove(j);
                                listaCancelarDEL.remove(i);
                                j=0;
                                i=0;
                            }
                        }
                    }

                    for(int i = 0; i < listaCancelarINS.size(); i++){
                        EntretenimentoModel model = new EntretenimentoModel();

                        model.setNome(listaCancelarINS.get(i).getNome());
                        model.setPreco(listaCancelarINS.get(i).getPreco());
                        model.setIdViagem(viagem.get_id());

                        entDAO.Insert(model);
                    }

                    for(int i = 0; i < listaCancelarDEL.size(); i++){
                        EntretenimentoModel model = new EntretenimentoModel();

                        int id = listaCancelarDEL.get(i).get_id();

                        entDAO.Delete(id);
                    }


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
                calcularTotal(idViagem);
            }
        }
        if (requestCode == TELA_TARIFA) {
            if (resultCode == 1) {
                tarifaAerea.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.icone_adicionado,0);
                calcularTotal(idViagem);
            }
        }
        if (requestCode == TELA_REFEICOES) {
            if (resultCode == 1) {
                refeicao.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.icone_adicionado,0);
                calcularTotal(idViagem);
            }
        }
        if (requestCode == TELA_HOSPEDAGEM) {
            if (resultCode == 1) {
                hospedagem.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.icone_adicionado,0);
                calcularTotal(idViagem);
            }
        }
        if (requestCode == TELA_ENTRETENIMENTO) {
            if (resultCode == 1) {
                entretenimento.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.icone_adicionado,0);
                calcularTotal(idViagem);
            }
        }
    }
    private void calcularTotal(int idViagem){

        GasolinaDAO daoG = new GasolinaDAO(this);
        HospedagemDAO daoH = new HospedagemDAO(this);
        RefeicaoDAO daoR = new RefeicaoDAO(this);
        TarifaDAO daoT = new TarifaDAO(this);
        EntretenimentoDAO daoE = new EntretenimentoDAO(this);

        float totalG = daoG.SelectTotal(idViagem);
        float totalH = daoH.SelectTotal(idViagem);
        float totalR = daoR.SelectTotal(idViagem);
        float totalT = daoT.SelectTotal(idViagem);
        float totalE = daoE.SelectTotal(idViagem);

        totalViagem = totalG + totalH + totalR + totalT + totalE;

        TxtTotalViagem.setText(String.format("%.2f", totalViagem));
    }
}