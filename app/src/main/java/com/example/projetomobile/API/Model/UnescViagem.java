package com.example.projetomobile.API.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class UnescViagem implements Serializable {
    public static final String TABLE_NAME = "tb_entreterimento";

    private int totalViajantes;
    private int duracaoViagem;
    private double custoTotalViagem;
    private double custoPorPessoa;
    private String local;
    private int idConta;
    private UnescCustoGasolina gasolina;
    private UnescViagemCustoAereo aereo;
    private UnescViagemCustoHospedagem hospedagem;
    private UnescViagemCustoRefeicao refeicao;
    private ArrayList<UnescCustoEntretenimento> listaEntretenimento;

    public int getTotalViajantes() {
        return totalViajantes;
    }

    public void setTotalViajantes(int totalViajantes) {
        this.totalViajantes = totalViajantes;
    }

    public int getDuracaoViagem() {
        return duracaoViagem;
    }

    public void setDuracaoViagem(int duracaoViagem) {
        this.duracaoViagem = duracaoViagem;
    }

    public double getCustoTotalViagem() {
        return custoTotalViagem;
    }

    public void setCustoTotalViagem(double custoTotalViagem) {
        this.custoTotalViagem = custoTotalViagem;
    }

    public double getCustoPorPessoa() {
        return custoPorPessoa;
    }

    public void setCustoPorPessoa(double custoPorPessoa) {
        this.custoPorPessoa = custoPorPessoa;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public int getIdConta() {
        return idConta;
    }

    public void setIdConta(int idConta) {
        this.idConta = idConta;
    }

    public UnescCustoGasolina getGasolina() {
        return gasolina;
    }

    public void setGasolina(UnescCustoGasolina gasolina) {
        this.gasolina = gasolina;
    }

    public UnescViagemCustoAereo getAereo() {
        return aereo;
    }

    public void setAereo(UnescViagemCustoAereo aereo) {
        this.aereo = aereo;
    }

    public UnescViagemCustoHospedagem getHospedagem() {
        return hospedagem;
    }

    public void setHospedagem(UnescViagemCustoHospedagem hospedagem) {
        this.hospedagem = hospedagem;
    }

    public UnescViagemCustoRefeicao getRefeicao() {
        return refeicao;
    }

    public void setRefeicao(UnescViagemCustoRefeicao refeicao) {
        this.refeicao = refeicao;
    }

    public ArrayList<UnescCustoEntretenimento> getListaEntretenimento() {
        return listaEntretenimento;
    }

    public void setListaEntretenimento(ArrayList<UnescCustoEntretenimento> listaEntretenimento) {
        this.listaEntretenimento = listaEntretenimento;
    }
}
