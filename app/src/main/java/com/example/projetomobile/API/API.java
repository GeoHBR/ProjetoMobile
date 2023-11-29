package com.example.projetomobile.API;

import com.example.projetomobile.API.Model.Resposta;
import com.example.projetomobile.API.Model.UnescViagem;
import com.example.projetomobile.API.endpoint.ViagemEndpoint;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API {

    public static final String URL_ROOT = "http://api.genialsaude.com.br";

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL_ROOT)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static void getViagens(int idConta, final Callback<ArrayList<UnescViagem>> callback){
        ViagemEndpoint endpoint = retrofit.create(ViagemEndpoint.class);
        Call<ArrayList<UnescViagem>> call = endpoint.getViagens(idConta);
        call.enqueue(callback);
    }


    public static void postViagem(UnescViagem viagem, final Callback<Resposta> callback){
        ViagemEndpoint endpoint = retrofit.create(ViagemEndpoint.class);
        Call<Resposta> call = endpoint.postViagem(viagem);
        call.enqueue(callback);
    }
}
