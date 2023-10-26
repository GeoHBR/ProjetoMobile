package com.example.projetomobile.database.model;

import java.io.Serializable;

public class RefeicaoModel implements Serializable {
    // Nome da tabela
    public static final String TABLE_NAME = "tb_refeicao";

    //  Colunas da tabela
    public static final String
            COLUNA_ID = "_id",
            COLUNA_CUSTO_REFEICAO = "custo_refeicao",
            COLUNA_QUANT_REFEICAO = "quant_refeicao",
            COLUNA_TOTAL = "total";

    //  SCript de criação da tabela
    public static final String
            CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
            + " ( "
            + COLUNA_ID + " integer primary key autoincrement, "
            + COLUNA_CUSTO_REFEICAO + " real not null, "
            + COLUNA_QUANT_REFEICAO + " intereger not null, "
            + COLUNA_TOTAL + " real not null"
            + " );";

    public static final String
            DROP_TABLE =  "drop table if exist " + TABLE_NAME + ";";


//  Variaveis dos valores da tabela

    private int _id;
    private float custoRefeicao;
    private int quantRefeicao;
    private float total;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public float getCustoRefeicao() {
        return custoRefeicao;
    }

    public void setCustoRefeicao(float custoRefeicao) {
        this.custoRefeicao = custoRefeicao;
    }

    public int getQuantRefeicao() {
        return quantRefeicao;
    }

    public void setQuantRefeicao(int quantRefeicao) {
        this.quantRefeicao = quantRefeicao;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
