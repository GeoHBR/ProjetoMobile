package com.example.projetomobile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
    private int idViagem;
    SharedPreferences preferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio);

        Intent intent = getIntent();

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

        idViagem = intent.getIntExtra("ID", 0);

        ViagemDAO daoV = new ViagemDAO(this);
        ViagemModel viagem = daoV.SelectViagem(idViagem);

        float totalV = intent.getFloatExtra("TOTAL", 0);

        destino.setText(viagem.getDestino());
        qtdViajantes.setText(String.valueOf(viagem.getQuantPessoas()));
        duracaoViajem.setText(String.valueOf(diferencaData(viagem.getDataInicio(), viagem.getDataFim()))+" dias");
        custoTotal2.setText(String.valueOf(totalV));
        custoTotal.setText(String.valueOf(totalV));
        custoViajante.setText(String.valueOf(totalV/viagem.getQuantPessoas()));

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

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
