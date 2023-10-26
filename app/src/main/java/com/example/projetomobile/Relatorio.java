package com.example.projetomobile;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projetomobile.adapter.Viagem_Modelo;
import com.example.projetomobile.database.dao.EntretenimentoDAO;
import com.example.projetomobile.database.dao.GasolinaDAO;
import com.example.projetomobile.database.dao.HospedagemDAO;
import com.example.projetomobile.database.dao.RefeicaoDAO;
import com.example.projetomobile.database.dao.TarifaDAO;
import com.example.projetomobile.database.dao.ViagemDAO;
import com.example.projetomobile.database.model.HospedagemModel;
import com.example.projetomobile.database.model.RefeicaoModel;
import com.example.projetomobile.database.model.ViagemModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
    private int idViagem;
    public ArrayList<Viagem_Modelo> listaViagens;
    SharedPreferences preferences;
    private ViagemModel viagem;
    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio);

        intent = getIntent();

        preferences = PreferenceManager.getDefaultSharedPreferences(Relatorio.this);
        idViagem = intent.getIntExtra("ID", 0);

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

        usuario.setText(preferences.getString("KEY_NOME", null));
        idViagem = intent.getIntExtra("ID", 0);

        ViagemDAO daoV = new ViagemDAO(this);
        viagem = daoV.SelectViagem(idViagem);

        float totalV = intent.getFloatExtra("TOTAL", 0);

        destino.setText(viagem.getDestino());
        qtdViajantes.setText(String.valueOf(viagem.getQuantPessoas()));
        duracaoViajem.setText(String.valueOf(diferencaData(viagem.getDataInicio(), viagem.getDataFim()))+" dias");
        custoTotal2.setText("R$ " +String.format("%.2f",totalV));
        custoTotal.setText(String.format("%.2f",totalV));
        custoViajante.setText("R$ " + String.format("%.2f",totalV/viagem.getQuantPessoas()));


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
                daoE.Delete(idViagem);

                finish();
            }
        });

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Relatorio.this, AdicionarViagem.class);
                intent.putExtra("ID_VIAGEM", idViagem);
                startActivity(intent);
            }
        });
    }

    protected void onResume() {

        ViagemDAO daoV = new ViagemDAO(this);
        viagem = daoV.SelectViagem(idViagem);
        float totalV = intent.getFloatExtra("TOTAL", 0);

        destino.setText(viagem.getDestino());
        qtdViajantes.setText(String.valueOf(viagem.getQuantPessoas()));
        duracaoViajem.setText(String.valueOf(diferencaData(viagem.getDataInicio(), viagem.getDataFim()))+" dias");
        custoTotal2.setText("R$ " +String.format("%.2f",totalV));
        custoTotal.setText(String.format("%.2f",totalV));
        custoViajante.setText("R$ " + String.format("%.2f",totalV/viagem.getQuantPessoas()));

        super.onResume();
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
        dias = anosBis * 366;
        dias += 365*(ano-2000-anosBis);
        dias +=dia;

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
}
