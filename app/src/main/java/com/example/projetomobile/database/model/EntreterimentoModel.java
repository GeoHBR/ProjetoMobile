package com.example.projetomobile.database.model;

public class EntreterimentoModel {
    // Nome da tabela
    public static final String TABLE_NAME = "tb_entreterimento";

    //  Colunas da tabela
    public static final String
            COLUNA_ID = "_id",
            COLUNA_NOME = "nome",
            COLUNA_PRECO = "preco",
            COLUNA_ID_VIAGEM = "_id_viagem";


    //  SCript de criação da tabela
    public static final String
            CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
            + " ( "
            + COLUNA_ID + " integer primary key autoincrement, "
            + COLUNA_NOME + " text not null, "
            + COLUNA_PRECO + " real(10,2) not null, "
            + COLUNA_ID_VIAGEM + " integer not null, "
            + "FOREIGN KET("+COLUNA_ID_VIAGEM+") REFERENCES tb_viagem(_id), "
            + " );";

    public static final String
            DROP_TABLE =  "drop table if exist " + TABLE_NAME + ";";


//  Variaveis dos valores da tabela

    private int _id;
    private int nome;
    private float preco;
    private int id_viagem;



//
    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getNome() {
        return nome;
    }

    public void setNome(int nome) {
        this.nome = nome;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public int getId_viagem() {
        return id_viagem;
    }

    public void setId_viagem(int id_viagem) {
        this.id_viagem = id_viagem;
    }
}
