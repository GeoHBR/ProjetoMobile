package com.example.projetomobile.API.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class EnviarViagem implements Serializable {

    private UnescViagem unescViagem;
    private UnescCustoGasolina unescCustoGasolina;
    private UnescViagemCustoAereo unescViagemCustoAereo;
    private UnescViagemCustoHospedagem unescViagemCustoHospedagem;
    private UnescViagemCustoRefeicao unescViagemCustoRefeicao;
    private ArrayList <UnescCustoEntretenimento> listaUnescCustoEntretenimentos;

    public EnviarViagem() {


    }

    public UnescViagem getUnescViagem() {
        return unescViagem;
    }

    public void setUnescViagem(UnescViagem unescViagem) {
        this.unescViagem = unescViagem;
    }

    public UnescCustoGasolina getUnescCustoGasolina() {
        return unescCustoGasolina;
    }

    public void setUnescCustoGasolina(UnescCustoGasolina unescCustoGasolina) {
        this.unescCustoGasolina = unescCustoGasolina;
    }

    public UnescViagemCustoAereo getUnescViagemCustoAereo() {
        return unescViagemCustoAereo;
    }

    public void setUnescViagemCustoAereo(UnescViagemCustoAereo unescViagemCustoAereo) {
        this.unescViagemCustoAereo = unescViagemCustoAereo;
    }

    public UnescViagemCustoHospedagem getUnescViagemCustoHospedagem() {
        return unescViagemCustoHospedagem;
    }

    public void setUnescViagemCustoHospedagem(UnescViagemCustoHospedagem unescViagemCustoHospedagem) {
        this.unescViagemCustoHospedagem = unescViagemCustoHospedagem;
    }

    public UnescViagemCustoRefeicao getUnescViagemCustoRefeicao() {
        return unescViagemCustoRefeicao;
    }

    public void setUnescViagemCustoRefeicao(UnescViagemCustoRefeicao unescViagemCustoRefeicao) {
        this.unescViagemCustoRefeicao = unescViagemCustoRefeicao;
    }

    public ArrayList<UnescCustoEntretenimento> getListaUnescCustoEntretenimentos() {
        return listaUnescCustoEntretenimentos;
    }

    public void setListaUnescCustoEntretenimentos(ArrayList<UnescCustoEntretenimento> listaUnescCustoEntretenimentos) {
        this.listaUnescCustoEntretenimentos = listaUnescCustoEntretenimentos;
    }
}
