package com.example.projetomobile;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetomobile.API.API;
import com.example.projetomobile.API.Model.UnescViagem;
import com.example.projetomobile.adapter.ViagemSync_Adapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class viagensSincronizadasActivity extends AppCompatActivity {

    private ListView listaViagens;
    private TextView userNome;
    private ImageView btnVoltar;
    SharedPreferences preferences;
    private SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viagenssync);

        preferences = PreferenceManager.getDefaultSharedPreferences(viagensSincronizadasActivity.this);
        edit = preferences.edit();

        listaViagens = findViewById(R.id.lista_viagens);
        userNome = findViewById(R.id.nomeUser);
        btnVoltar = findViewById(R.id.voltarSync);

        listarViagens();

        userNome.setText(preferences.getString("KEY_NOME", null));

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void listarViagens(){

        listaViagens = findViewById(R.id.lista_viagens);
        API.getViagens(123539, new Callback<ArrayList<UnescViagem>>(){
            @Override
            public void onResponse(Call<ArrayList<UnescViagem>> call, Response<ArrayList<UnescViagem>> response) {
                if (response != null && response.isSuccessful()) {
                    ArrayList<UnescViagem> resposta = response.body();
                    if (resposta != null) {
                        ArrayList<UnescViagem> viagensSync = new ArrayList<>();
                        for(int i = 0; i < resposta.size(); i++){
                            UnescViagem viagem = resposta.get(i);

                            viagensSync.add(viagem);
                        }
                        ViagemSync_Adapter adapter = new ViagemSync_Adapter(resposta, viagensSincronizadasActivity.this);
                        listaViagens.setAdapter(adapter);
                    }
                }
            }
            @Override
            public void onFailure(Call<ArrayList<UnescViagem>> call, Throwable t) {
                Toast.makeText(viagensSincronizadasActivity.this, "errro", Toast.LENGTH_LONG).show();
            }
        });
    }
}