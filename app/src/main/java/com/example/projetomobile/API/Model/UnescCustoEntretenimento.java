package com.example.projetomobile.API.Model;

import java.io.Serializable;

public class UnescCustoEntretenimento implements Serializable {

    private String entretenimento;
    private int valor;

    public String getEntretenimento() {
        return entretenimento;
    }

    public void setEntretenimento(String entretenimento) {
        this.entretenimento = entretenimento;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}