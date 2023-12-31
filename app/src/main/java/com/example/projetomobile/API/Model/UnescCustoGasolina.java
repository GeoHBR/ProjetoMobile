package com.example.projetomobile.API.Model;

import java.io.Serializable;

public class UnescCustoGasolina implements Serializable {

    private int totalEstimadoKM;
    private double mediaKMLitro;
    private double custoMedioLitro;
    private double totalVeiculos;

    public int getTotalEstimadoKM() {
        return totalEstimadoKM;
    }

    public void setTotalEstimadoKM(int totalEstimadoKM) {
        this.totalEstimadoKM = totalEstimadoKM;
    }

    public double getMediaKMLitro() {
        return mediaKMLitro;
    }

    public void setMediaKMLitro(double mediaKMLitro) {
        this.mediaKMLitro = mediaKMLitro;
    }

    public double getCustoMedioLitro() {
        return custoMedioLitro;
    }

    public void setCustoMedioLitro(double custoMedioLitro) {
        this.custoMedioLitro = custoMedioLitro;
    }

    public double getTotalVeiculos() {
        return totalVeiculos;
    }

    public void setTotalVeiculos(double custoMedioLitro) {
        this.totalVeiculos = custoMedioLitro;
    }
}