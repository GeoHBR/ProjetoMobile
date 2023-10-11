package com.example.projetomobile.database.model;

public class RefeicaoModel {
    // Nome da tabela
    public static final String TABLE_NAME = "tb_refeicao";

    //  Colunas da tabela
    public static final String
            COLUNA_ID = "_id",
            COLUNA_CUSTO_REFEICAO = "custo_refeicao",
            COLUNA_QUANT_REFEICAO = "quant_refeicao";

    //  SCript de criação da tabela
    public static final String
            CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
            + " ( "
            + COLUNA_ID + " integer primary key autoincrement, "
            + COLUNA_CUSTO_REFEICAO + " real not null, "
            + COLUNA_QUANT_REFEICAO + " intereger not null "
            + " );";

    public static final String
            DROP_TABLE =  "drop table if exist " + TABLE_NAME + ";";


//  Variaveis dos valores da tabela

    private int _id;
    private float custoRefeicao;
    private int quantRefeicao;

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

}
