package com.example.projetomobile.API.endpoint;

import com.example.projetomobile.API.Model.Resposta;
import com.example.projetomobile.API.Model.UnescViagem;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ViagemEndpoint {

    @GET("api/listar/viagem{viagemId}")
    Call<Resposta> getViagem(@Query("viagemId")int viagemId);

    @GET("api/listar/viagem")
    Call<Resposta> getViagemPath(@Path("viagemId") int viagemId);


    @POST("api/cadastro/viagem")
    Call<Resposta> postViagem(@Body UnescViagem enviarViagem);
}
