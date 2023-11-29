package com.example.projetomobile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projetomobile.API.API;
import com.example.projetomobile.API.Model.Resposta;
import com.example.projetomobile.API.Model.UnescCustoEntretenimento;
import com.example.projetomobile.API.Model.UnescCustoGasolina;
import com.example.projetomobile.API.Model.UnescViagem;
import com.example.projetomobile.API.Model.UnescViagemCustoAereo;
import com.example.projetomobile.API.Model.UnescViagemCustoHospedagem;
import com.example.projetomobile.API.Model.UnescViagemCustoRefeicao;
import com.example.projetomobile.adapter.Viagem_Modelo;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Relatorio extends AppCompatActivity {
    private TextView usuario;
    private TextView destino;
    private TextView custoTotal;
    private TextView qtdViajantes;
    private TextView duracaoViajem;
    private TextView custoTotal2;
    private TextView custoViajante;
    private ImageView voltar;
    private ImageButton excluir;
    private ImageView editar;
    private Button btnSync;
    private int idViagem;
    public ArrayList<Viagem_Modelo> listaViagens;
    SharedPreferences preferences;
    private ViagemModel viagemB;
    private Intent intent;
    private static final int EDICAO = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio);

        usuario = findViewById(R.id.usuarioRelatorio);
        custoTotal = findViewById(R.id.txtCustoTotalRelatorio);
        qtdViajantes = findViewById(R.id.qtdViajantesRelatorio);
        duracaoViajem = findViewById(R.id.duracaoViagemRelatorio);
        custoTotal2 = findViewById(R.id.custoTotalRelatorio);
        custoViajante = findViewById(R.id.custoViajanteRelatorio);
        voltar = findViewById(R.id.voltarRelatorio);
        destino = findViewById(R.id.destinoRelatorio);
        excluir = findViewById(R.id.btn_excluir_relatorio);
        editar = findViewById(R.id.editar_relatorio);
        btnSync = findViewById(R.id.btnSincronizar);

        preferences = PreferenceManager.getDefaultSharedPreferences(Relatorio.this);
        usuario.setText(preferences.getString("KEY_NOME", null));

        intent = getIntent();
        idViagem = intent.getIntExtra("ID", 0);

        buscarViagem();

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        excluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ViagemDAO dao = new ViagemDAO(Relatorio.this);
                ArrayList<Integer> ids = dao.Delete(idViagem);
                GasolinaDAO daoG = new GasolinaDAO(Relatorio.this);
                daoG.Delete(ids.get(0));
                HospedagemDAO daoH = new HospedagemDAO(Relatorio.this);
                daoH.Delete(ids.get(1));
                RefeicaoDAO daoR = new RefeicaoDAO(Relatorio.this);
                daoR.Delete(ids.get(2));
                TarifaDAO daoT = new TarifaDAO(Relatorio.this);
                daoT.Delete(ids.get(3));
                EntretenimentoDAO daoE = new EntretenimentoDAO(Relatorio.this);
                daoE.DeleteAll(idViagem);

                finish();
            }
        });

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Relatorio.this, AdicionarViagem.class);
                intent.putExtra("ID_VIAGEM", idViagem);
                startActivityForResult(intent, EDICAO);
            }
        });

        btnSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UnescViagem viagem = new UnescViagem();

                float total = calcularTotal();

                viagem.setIdConta(123539);
                viagem.setDuracaoViagem(diferencaData(viagemB.getDataInicio(), viagemB.getDataFim()));
                viagem.setLocal(viagemB.getDestino());
                viagem.setCustoTotalViagem(total);
                viagem.setCustoPorPessoa(total/viagemB.getQuantPessoas());
                viagem.setTotalViajantes(viagemB.getQuantPessoas());

                UnescCustoGasolina gasolinaUnesc = new UnescCustoGasolina();
                GasolinaDAO gasoDAO = new GasolinaDAO(Relatorio.this);
                GasolinaModel gaso = gasoDAO.Select(viagemB.get_idGasolina());

                gasolinaUnesc.setCustoMedioLitro(gaso.getCustoMedio());
                gasolinaUnesc.setTotalEstimadoKM((int) gaso.getTotalKM());
                gasolinaUnesc.setTotalVeiculos(gaso.getTotalVeiculo());
                gasolinaUnesc.setMediaKMLitro(gaso.getMedialKM());

                viagem.setGasolina(gasolinaUnesc);

                UnescViagemCustoAereo aereoUnesc = new UnescViagemCustoAereo();
                TarifaDAO tarDAO = new TarifaDAO(Relatorio.this);
                TarifaModel tar = tarDAO.Select(viagemB.get_idTarifa());

                aereoUnesc.setCustoPessoa(tar.getCustoPessoa());
                aereoUnesc.setCustoAluguelVeiculo(tar.getCustoAluguel());

                viagem.setAereo(aereoUnesc);

                UnescViagemCustoHospedagem hospedagemUnesc = new UnescViagemCustoHospedagem();
                HospedagemDAO hosDAO = new HospedagemDAO(Relatorio.this);
                HospedagemModel hos = hosDAO.Select(viagemB.get_idHospedagem());

                hospedagemUnesc.setCustoMedioNoite(hos.getCustoMedio());
                hospedagemUnesc.setTotalNoite(hos.getTotalNoites());
                hospedagemUnesc.setTotalQuartos(hos.getTotalQuartos());

                viagem.setHospedagem(hospedagemUnesc);

                UnescViagemCustoRefeicao refeicaoUnesc = new UnescViagemCustoRefeicao();
                RefeicaoDAO refDAO = new RefeicaoDAO(Relatorio.this);
                RefeicaoModel ref = refDAO.Select(viagemB.get_idRefeicao());

                refeicaoUnesc.setCustoRefeicao(ref.getCustoRefeicao());
                refeicaoUnesc.setRefeicoesDia(ref.getQuantRefeicao());

                viagem.setRefeicao(refeicaoUnesc);

                EntretenimentoDAO entDAO = new EntretenimentoDAO(Relatorio.this);
                ArrayList<EntretenimentoModel> listE =entDAO.Select(viagemB.get_id());
                ArrayList<UnescCustoEntretenimento> listEUnesc = new ArrayList<>();

                for(int i = 0; i < listE.size(); i++){
                    UnescCustoEntretenimento aux = new UnescCustoEntretenimento();
                    aux.setEntretenimento(listE.get(i).getNome());
                    aux.setValor((int) listE.get(i).getPreco());

                    listEUnesc.add(aux);
                }

                viagem.setListaEntretenimento(listEUnesc);

                API.postViagem(viagem, new Callback<Resposta>() {
                    @Override
                    public void onResponse(Call<Resposta> call, Response<Resposta> response) {
                        if (response != null && response.isSuccessful()) {
                            Resposta resposta = response.body();
                            if (resposta != null) {
                                Toast.makeText(Relatorio.this, "Viagem Sincronizada com Sucesso", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<Resposta> call, Throwable t) {
                        Toast.makeText(Relatorio.this, "Ocorrou um Erro Durante a Sincronização", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == EDICAO){
            if(resultCode == 1){
                buscarViagem();
            }
        }
    }

    private void buscarViagem() {

        ViagemDAO daoV = new ViagemDAO(this);
        viagemB = daoV.SelectViagem(idViagem);
        float totalV = calcularTotal();

        destino.setText(viagemB.getDestino());
        qtdViajantes.setText(String.valueOf(viagemB.getQuantPessoas()));
        duracaoViajem.setText(String.valueOf(diferencaData(viagemB.getDataInicio(), viagemB.getDataFim()))+" dias");
        custoTotal2.setText("R$ " +String.format("%.2f",totalV));
        custoTotal.setText(String.format("%.2f",totalV));
        custoViajante.setText("R$ " + String.format("%.2f",totalV/viagemB.getQuantPessoas()));
    }

    private int diferencaData(String inicio, String fim) {
        int dataInicio = conrveteData(inicio);
        int dataFim = conrveteData(fim);
        return dataFim -dataInicio;
    }

    private int conrveteData(String data) {
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
        dias += anosBis * 366;
        dias += 365*(ano-2000-anosBis);
        dias +=dia+1;

        return dias;
    }

    private static Date converterStringParaDate(String dateString) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    private float calcularTotal(){

        GasolinaDAO daoG = new GasolinaDAO(Relatorio.this);
        HospedagemDAO daoH = new HospedagemDAO(Relatorio.this);
        RefeicaoDAO daoR = new RefeicaoDAO(Relatorio.this);
        TarifaDAO daoT = new TarifaDAO(Relatorio.this);
        EntretenimentoDAO daoE = new EntretenimentoDAO(Relatorio.this);

        float totalG = daoG.SelectTotal(idViagem);
        float totalH = daoH.SelectTotal(idViagem);
        float totalR = daoR.SelectTotal(idViagem);
        float totalT = daoT.SelectTotal(idViagem);
        float totalE = daoE.SelectTotal(idViagem);

        return totalG + totalH + totalR + totalT + totalE;

    }
}
