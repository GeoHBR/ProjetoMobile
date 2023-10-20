package com.example.projetomobile.database.model;

public class EntretenimentoModel {
    // Nome da tabela
    public static final String TABLE_NAME = "tb_entreterimento";

    //  Colunas da tabela
    public static final String
            COLUNA_ID = "_id",
            COLUNA_NOME = "nome",
            COLUNA_PRECO = "preco",
            COLUNA_ID_VIAGEM = "id_viagem";


    //  SCript de criação da tabela
    public static final String
            CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
            + " ( "
            + COLUNA_ID + " integer primary key autoincrement, "
            + COLUNA_NOME + " text not null, "
            + COLUNA_PRECO + " real not null, "
            + COLUNA_ID_VIAGEM + " int not null, "
            + "FOREIGN KEY("+COLUNA_ID_VIAGEM+") REFERENCES tb_viagem(_id)"
            + " );";

    public static final String
            DROP_TABLE =  "drop table if exist " + TABLE_NAME + ";";


//  Variaveis dos valores da tabela

    private int _id;
    private String nome;
    private float preco;
    private int idViagem;


//
    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public int getIdViagem() {
        return idViagem;
    }

    public void setIdViagem(int idViagem) {
        this.idViagem = idViagem;
    }

}
