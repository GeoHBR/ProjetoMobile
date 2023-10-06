package com.example.projetomobile.database.model;

public class TarifaModel {
    // Nome da tabela
    public static final String TABLE_NAME = "";

    //  Colunas da tabela
    public static final String
            COLUNA_ID = "_id",
            COLUNA_CUSTO_PESSOA = "custo_pessoa",
            COLUNA_CUSTO_ALUGUEL = "custo_aluguel",
            COLUNA_ID_VIAGEM = "_id_viagem";

    //  SCript de criação da tabela
    public static final String
            CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
            + " ( "
            + COLUNA_ID + " integer primary key autoincrement, "
            + COLUNA_CUSTO_PESSOA + " real not null, "
            + COLUNA_CUSTO_ALUGUEL + " real not null,"
            + "FOREIGN KEY("+COLUNA_ID_VIAGEM+") REFERENCES tb_viagem(_id)"
            + " );";

    public static final String
            DROP_TABLE =  "drop table if exist " + TABLE_NAME + ";";


//  Variaveis dos valores da tabela

    private int _id;
    private float custoPessoa;
    private float custoAluguel;
    private int id_viagem;

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

    public int getId_viagem() {
        return id_viagem;
    }

    public void setId_viagem(int id_viagem) {
        this.id_viagem = id_viagem;
    }
}
