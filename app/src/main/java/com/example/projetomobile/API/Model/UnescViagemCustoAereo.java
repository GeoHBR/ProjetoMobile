package com.example.projetomobile.API.Model;

import java.io.Serializable;

public class UnescViagemCustoAereo implements Serializable {

    private double custoPessoa;
    private double custoAluguelVeiculo;

    public double getCustoPessoa() {
        return custoPessoa;
    }

    public void setCustoPessoa(double custoPessoa) {
        this.custoPessoa = custoPessoa;
    }

    public double getCustoAluguelVeiculo() {
        return custoAluguelVeiculo;
    }

    public void setCustoAluguelVeiculo(double custoAluguelVeiculo) {
        this.custoAluguelVeiculo = custoAluguelVeiculo;
    }
}
