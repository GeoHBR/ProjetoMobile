package com.example.projetomobile.API.Model;

import java.io.Serializable;

public class UnescViagemCustoRefeicao implements Serializable {

    private double custoRefeicao;
    private int refeicoesDia;

    public double getCustoRefeicao() {
        return custoRefeicao;
    }

    public void setCustoRefeicao(double custoRefeicao) {
        this.custoRefeicao = custoRefeicao;
    }

    public int getRefeicoesDia() {
        return refeicoesDia;
    }

    public void setRefeicoesDia(int refeicoesDia) {
        this.refeicoesDia = refeicoesDia;
    }
}
