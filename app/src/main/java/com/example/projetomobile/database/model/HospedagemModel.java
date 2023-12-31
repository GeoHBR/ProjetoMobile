package com.example.projetomobile.database.model;

import java.io.Serializable;

public class HospedagemModel implements Serializable {
    // Nome da tabela
    public static final String TABLE_NAME = "tb_hospedagem";

    //  Colunas da tabela
    public static final String
            COLUNA_ID = "_id",
            COLUNA_MEDIO = "custo_medio",
            COLUNA_NOITES = "total_noites",
            COLUNA_QUARTOS = "total_quartos",
            COLUNA_TOTAL = "total";

    //  SCript de criação da tabela
    public static final String
            CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
            + " ( "
            + COLUNA_ID + " integer primary key autoincrement, "
            + COLUNA_MEDIO + " numeric(10,2) not null, "
            + COLUNA_NOITES + " int not null, "
            + COLUNA_QUARTOS + " int not null, "
            + COLUNA_TOTAL + " numeric(10,2) not null"
            + " );";

    public static final String
            DROP_TABLE =  "drop table if exist " + TABLE_NAME + ";";


//  Variaveis dos valores da tabela

    private int _id;
    private float custoMedio;
    private int totalNoites;
    private int totalQuartos;
    private float total;

//
    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public float getCustoMedio() {
        return custoMedio;
    }

    public void setCustoMedio(float custoMedio) {
        this.custoMedio = custoMedio;
    }

    public int getTotalNoites() {
        return totalNoites;
    }

    public void setTotalNoites(int totalNoites) {
        this.totalNoites = totalNoites;
    }

    public int getTotalQuartos() {
        return totalQuartos;
    }

    public void setTotalQuartos(int totalQuartos) {
        this.totalQuartos = totalQuartos;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
