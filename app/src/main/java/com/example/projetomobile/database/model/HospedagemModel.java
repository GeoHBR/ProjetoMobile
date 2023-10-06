package com.example.projetomobile.database.model;

public class HospedagemModel {
    // Nome da tabela
    public static final String TABLE_NAME = "tb_hospedagem";

    //  Colunas da tabela
    public static final String
            COLUNA_ID = "_id",
            COLUNA_MEDIO = "custo_medio",
            COLUNA_NOITES = "total_noites",
            COLUNA_QUARTOS = "total_quartos",
            COLUNA_TOTAL = "total",
            COLUNA_ID_VIAGEM = "_id_viagem";

    //  SCript de criação da tabela
    public static final String
            CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
            + " ( "
            + COLUNA_ID + " integer primary key autoincrement, "
            + COLUNA_MEDIO + " numeric(10,2) not null, "
            + COLUNA_NOITES + " int not null, "
            + COLUNA_QUARTOS + " int not null, "
            + COLUNA_TOTAL + " numeric(10,2) not null"
            + "FOREIGN KEY("+COLUNA_ID_VIAGEM+") REFERENCES tb_viagem(_id) "
            + " );";

    public static final String
            DROP_TABLE =  "drop table if exist " + TABLE_NAME + ";";


//  Variaveis dos valores da tabela

    private int _id;
    private float custoMedio;
    private int totalNoites;
    private int totalQuartos;
    private int id_viagem;

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

    public int getId_viagem() {
        return id_viagem;
    }

    public void setId_viagem(int id_viagem) {
        this.id_viagem = id_viagem;
    }
}
