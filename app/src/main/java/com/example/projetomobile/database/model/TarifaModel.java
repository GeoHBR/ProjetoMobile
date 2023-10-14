package com.example.projetomobile.database.model;

public class TarifaModel {
    // Nome da tabela
    public static final String TABLE_NAME = "tb_tarifa";

    //  Colunas da tabela
    public static final String
            COLUNA_ID = "_id",
            COLUNA_CUSTO_PESSOA = "custo_pessoa",
            COLUNA_CUSTO_ALUGUEL = "custo_aluguel",
            COLUNA_TOTAL = "total";

    //  SCript de criação da tabela
    public static final String
            CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
            + " ( "
            + COLUNA_ID + " integer primary key autoincrement, "
            + COLUNA_CUSTO_PESSOA + " real not null, "
            + COLUNA_CUSTO_ALUGUEL + " real not null, "
            + COLUNA_TOTAL + " real not null);";

    public static final String
            DROP_TABLE =  "drop table if exist " + TABLE_NAME + ";";


//  Variaveis dos valores da tabela

    private int _id;
    private float custoPessoa;
    private float custoAluguel;
    private float total;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public float getCustoPessoa() {
        return custoPessoa;
    }

    public void setCustoPessoa(float custoPessoa) {
        this.custoPessoa = custoPessoa;
    }

    public float getCustoAluguel() {
        return custoAluguel;
    }

    public void setCustoAluguel(float custoAluguel) {
        this.custoAluguel = custoAluguel;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
