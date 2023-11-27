package com.example.projetomobile.API;

import com.example.projetomobile.API.Model.EnviarViagem;
import com.example.projetomobile.API.Model.Resposta;
import com.example.projetomobile.API.endpoint.ViagemEndpoint;

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

    public static void getViagem(int viagemId, final Callback<Resposta> callback){
        ViagemEndpoint endpoint = retrofit.create(ViagemEndpoint.class);
        Call<Resposta> call = endpoint.getViagemPath(viagemId);
        call.enqueue(callback);
    }


    public static void postViagem(EnviarViagem viagem, final Callback<Resposta> callback){
        ViagemEndpoint endpoint = retrofit.create(ViagemEndpoint.class);
        Call<Resposta> call = endpoint.postViagem(viagem);
        call.enqueue(callback);
    }
}
