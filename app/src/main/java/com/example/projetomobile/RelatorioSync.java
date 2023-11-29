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

public class RelatorioSync extends AppCompatActivity {
    private TextView usuario;
    private TextView destino;
    private TextView custoTotal;
    private TextView qtdViajantes;
    private TextView duracaoViajem;
    private TextView custoTotal2;
    private TextView custoViajante;
    private ImageView voltar;
    SharedPreferences preferences;
    private Intent intent;

    private UnescViagem viagem;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatoriosync);

        usuario = findViewById(R.id.usuarioRelatorio);
        custoTotal = findViewById(R.id.txtCustoTotalRelatorio);
        qtdViajantes = findViewById(R.id.qtdViajantesRelatorio);
        duracaoViajem = findViewById(R.id.duracaoViagemRelatorio);
        custoTotal2 = findViewById(R.id.custoTotalRelatorio);
        custoViajante = findViewById(R.id.custoViajanteRelatorio);
        voltar = findViewById(R.id.voltarRelatorio);
        destino = findViewById(R.id.destinoRelatorio);

        preferences = PreferenceManager.getDefaultSharedPreferences(RelatorioSync.this);
        usuario.setText(preferences.getString("KEY_NOME", null));

        intent = getIntent();
        viagem = (UnescViagem) intent.getSerializableExtra("VIAGEM");

        buscarViagem();

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
    private void buscarViagem() {
        destino.setText(viagem.getLocal());
        qtdViajantes.setText(String.valueOf(viagem.getTotalViajantes()));
        duracaoViajem.setText(String.valueOf(viagem.getDuracaoViagem())+" dias");
        custoTotal2.setText("R$ " +String.format("%.2f", viagem.getCustoTotalViagem()));
        custoTotal.setText(String.format("%.2f", viagem.getCustoTotalViagem()));
        custoViajante.setText("R$ " + String.format("%.2f", viagem.getCustoPorPessoa()));
    }
}
