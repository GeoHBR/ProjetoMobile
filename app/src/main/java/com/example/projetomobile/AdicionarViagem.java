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
import android.text.TextWatcher;
import android.util.Log;
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
    private ViagemDAO dao = new ViagemDAO(AdicionarViagem.this);
    private boolean update;
    private int idViagem;
    private static final int TELA_GASOLINA = 1;
    private static final int TELA_TARIFA = 2;
    private static final int TELA_REFEICOES = 3;
    private static final int TELA_HOSPEDAGEM = 4;
    private static final int TELA_ENTRETENIMENTO = 5;
    private ViagemModel viagem = new ViagemModel();
    private GasolinaModel gasoModel = new GasolinaModel();
    private boolean gasoAdd = false;
    private HospedagemModel hosModel = new HospedagemModel();
    private boolean hosAdd = false;
    private RefeicaoModel refModel = new RefeicaoModel();
    private boolean refAdd = false;
    private TarifaModel tarModel = new TarifaModel();
    private boolean tarAdd = false;
    private ArrayList<EntretenimentoModel> listaE = new ArrayList<>();
    private boolean entAdd = false;
    private float totalViagem;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        GasolinaDAO gasoDAO = new GasolinaDAO(this);
        HospedagemDAO hopDAO = new HospedagemDAO(this);
        RefeicaoDAO refDAO = new RefeicaoDAO(this);
        TarifaDAO tarDAO = new TarifaDAO(this);
        EntretenimentoDAO entDAO = new EntretenimentoDAO(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_viagem);

        preferences = PreferenceManager.getDefaultSharedPreferences(AdicionarViagem.this);

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

        if(idViagem > 0){

            viagem = dao.SelectViagem(idViagem);

            destino.setText(viagem.getDestino());
            dateInicio.setText(viagem.getDataInicio());
            dateFim.setText(viagem.getDataFim());
            quantViajantes.setText(Integer.toString(viagem.getQuantPessoas()));

            gasoAdd = true;
            hosAdd = true;
            refAdd = true;
            tarAdd = true;
            entAdd = true;

            gasoModel = gasoDAO.Select(viagem.get_idGasolina());
            hosModel = hopDAO.Select(viagem.get_idHospedagem());
            refModel = refDAO.Select(viagem.get_idRefeicao());
            tarModel = tarDAO.Select(viagem.get_idTarifa());
            listaE = entDAO.Select(idViagem);

            calcularTotal();
            update = true;
        }

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
                        recalcularTotal();
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
                        recalcularTotal();
                    }
                }, year, month, day);

                datePickerDialog.show();
            }
        });

        quantViajantes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                recalcularTotal();
            }
        });

        gasolina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(AdicionarViagem.this, Gasolina.class);
                if(gasoAdd){
                    it.putExtra("GASOLINA", gasoModel);
                    it.putExtra("EDICAO", true);
                }
                startActivityForResult(it, TELA_GASOLINA);
            }
        });
        hospedagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(AdicionarViagem.this, Hospedagem.class);
                if(hosAdd){
                    it.putExtra("HOSPEDAGEM", hosModel);
                    it.putExtra("EDICAO", true);
                }
                startActivityForResult(it, TELA_HOSPEDAGEM);
            }
        });
        tarifaAerea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(quantViajantes.getText().toString().isEmpty()) {
                    quantViajantes.setError("Preencha este campo primeiro");
                }else{
                    Intent it = new Intent(AdicionarViagem.this, TarifaAreaActivity.class);
                    if(tarAdd){
                        it.putExtra("TARIFA", tarModel);
                        it.putExtra("EDICAO", true);
                    }
                    it.putExtra("QUANT_VIAJANTES", Integer.parseInt(quantViajantes.getText().toString()));
                    startActivityForResult( it, TELA_TARIFA);

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
                    if(refAdd){
                        intent.putExtra("REFEICAO", refModel);
                        intent.putExtra("EDICAO", true);
                    }
                    intent.putExtra("QUANT_VIAJANTES", Integer.parseInt(quantViajantes.getText().toString()));
                    intent.putExtra("DURACAO", diferencaData(dateInicio.getText().toString(), dateFim.getText().toString()));
                    startActivityForResult( intent, TELA_REFEICOES);

                }
            }
        });

        entretenimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(AdicionarViagem.this, Entretenimento.class);
                if(entAdd){
                    in.putExtra("ENTRETENIMENTO", listaE);
                    in.putExtra("EDICAO", true);
                }
                startActivityForResult(in, TELA_ENTRETENIMENTO);
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

                    recalcularTotal();

                    model.setDestino(destino.getText().toString());
                    model.setDataInicio(dateInicio.getText().toString());
                    model.setDataFim(dateFim.getText().toString());
                    model.set_idUsuario(preferences.getInt("KEY_ID", 0));
                    model.setQuantPessoas(Integer.parseInt(quantViajantes.getText().toString()));

                    if(gasoAdd){
                        boolean novo = true;
                        if(update){
                            if(viagem.get_idGasolina() > 0){
                                gasoDAO.Update(viagem.get_idGasolina(), gasoModel);
                                model.set_idGasolina(viagem.get_idGasolina());
                                novo = false;
                            }
                        }
                        if(novo){
                            int idGasolina = gasoDAO.Insert(gasoModel);
                            model.set_idGasolina(idGasolina);
                        }
                    }
                    if(hosAdd){
                        boolean novo = true;
                        if(update){
                            if(viagem.get_idHospedagem() > 0){
                                hopDAO.Update(viagem.get_idHospedagem(), hosModel);
                                model.set_idHospedagem(viagem.get_idHospedagem());
                                novo = false;
                            }
                        }
                        if(novo){
                            int idHospedagem = hopDAO.Insert(hosModel);
                            model.set_idHospedagem(idHospedagem);
                        }
                    }
                    if(tarAdd){
                        boolean novo = true;
                        if(update){
                            if(viagem.get_idTarifa() > 0){
                                tarDAO.Update(viagem.get_idTarifa(), tarModel);
                                model.set_idTarifa(viagem.get_idTarifa());
                                novo = false;
                            }

                        }
                        if(novo){
                            int idTarifa = tarDAO.Insert(tarModel);
                            model.set_idTarifa(idTarifa);
                        }
                    }
                    if(refAdd){
                        boolean novo = true;
                        if(update){
                            if(viagem.get_idRefeicao() > 0){
                                refDAO.Update(viagem.get_idRefeicao(), refModel);
                                model.set_idRefeicao(viagem.get_idRefeicao());
                                novo = false;
                            }

                        }
                        if(novo){
                            int idRefeicao = refDAO.Insert(refModel);
                            model.set_idRefeicao(idRefeicao);
                        }
                    }
                    if(update){
                        model.set_id(idViagem);
                        dao.Update(model);
                    }else{
                        idViagem = dao.Insert(model);
                    }
                    if(update){
                        entDAO.DeleteAll(idViagem);
                    }
                    for(int i = 0; i < listaE.size(); i++){
                        EntretenimentoModel modelE = new EntretenimentoModel();

                        modelE.setNome(listaE.get(i).getNome());
                        modelE.setPreco(listaE.get(i).getPreco());
                        modelE.setIdViagem(idViagem);

                        entDAO.Insert(modelE);
                    }
                    setResult(1);
                    finish();
                }
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public int diferencaData(String dataI, String dataF) {
        int dataInicio = conrveteData(dataI);
        int dataFim = conrveteData(dataF);
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
//        Log.e("erroha", Integer.toString(dias));

        meses.set(ano, 2 -1,1);

        int anosBis = ((ano-2000) / 4)+1;
        dias += anosBis * 366;
        dias += 365*(ano-2000-anosBis);
        dias +=dias+1;

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TELA_GASOLINA) {
            if (resultCode == 1) {
                gasolina.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.icone_adicionado,0);
                gasoModel = (GasolinaModel) data.getSerializableExtra("GASOLINA");
                gasoAdd = true;
            }
        }
        if (requestCode == TELA_TARIFA) {
            if (resultCode == 1) {
                tarifaAerea.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.icone_adicionado,0);
                tarModel = (TarifaModel) data.getSerializableExtra("TARIFA");
                tarAdd = true;
            }
        }
        if (requestCode == TELA_REFEICOES) {
            if (resultCode == 1) {
                refeicao.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.icone_adicionado,0);
                refModel = (RefeicaoModel) data.getSerializableExtra("REFEICAO");
                refAdd = true;
            }
        }
        if (requestCode == TELA_HOSPEDAGEM) {
            if (resultCode == 1) {
                hospedagem.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.icone_adicionado,0);
                hosModel = (HospedagemModel) data.getSerializableExtra("HOSPEDAGEM");
                hosAdd = true;
            }
        }
        if (requestCode == TELA_ENTRETENIMENTO) {
            if (resultCode == 1) {
                entretenimento.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.icone_adicionado,0);
                listaE = (ArrayList<EntretenimentoModel>) data.getSerializableExtra("ENTRETENIMENTO");
                entAdd = true;
            }
        }
        calcularTotal();

    }
    private void calcularTotal(){

        float gas = gasoModel.getTotal();
        float hos = hosModel.getTotal();
        float ref = refModel.getTotal();
        float tar = tarModel.getTotal();
        float ent = 0;
        if(entAdd){
            for(int i = 0; i <listaE.size(); i++){
                ent += listaE.get(i).getPreco();
            }
        }

        totalViagem = gas + hos + ref + tar + ent;

        TxtTotalViagem.setText(String.format("%.2f", totalViagem));
    }
    private void recalcularTotal(){
        int dataBanco = 0;
        int viajantesBanco = 0;
        int dataNova = 0;

        if(!dateInicio.getText().toString().isEmpty() && !dateFim.getText().toString().isEmpty()){
            dataNova = diferencaData(dateInicio.getText().toString(), dateFim.getText().toString());
        }
        if(update){
            dataBanco = diferencaData(viagem.getDataInicio(), viagem.getDataFim());
            viajantesBanco = viagem.getQuantPessoas();
        }
        if(!quantViajantes.getText().toString().isEmpty()){
            int viajantes = Integer.parseInt(quantViajantes.getText().toString());
            if (dataBanco != dataNova || viajantesBanco != viajantes) {
                //refaz total da tarifa area
                if(tarAdd){
                    float custoPessoaC = tarModel.getCustoPessoa();
                    float veiculo = tarModel.getCustoAluguel();
                    float precoTotalT = (custoPessoaC * viajantes) + veiculo;

                    tarModel.setTotal(precoTotalT);
                }
                //refaz total da refeicao
                if(refAdd){
                    float custoRefeicaoC = refModel.getCustoRefeicao();
                    int quantRefeicaoC = refModel.getQuantRefeicao();
                    int duracao = dataNova;
                    float precoTotalR = ((quantRefeicaoC * viajantes) * custoRefeicaoC) * duracao;

                    refModel.setTotal(precoTotalR);
                }
                calcularTotal();
            }
        }
    }
}