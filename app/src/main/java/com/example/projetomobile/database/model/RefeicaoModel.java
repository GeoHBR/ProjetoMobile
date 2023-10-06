package com.example.projetomobile.database.model;

public class RefeicaoModel {
    // Nome da tabela
    public static final String TABLE_NAME = "";

    //  Colunas da tabela
    public static final String
            COLUNA_ID = "_id";

    //  SCript de criação da tabela
    public static final String
            CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
            + " ( "
            + COLUNA_ID + " integer primary key autoincrement, "
            + COLUNA_NOME + " text not null, "
            + COLUNA_CPF + " text not null, "
            + COLUNA_ENDERECO + " text not null, "
            + COLUNA_CEP + " text not null"
            + " );";

    public static final String
            DROP_TABLE =  "drop table if exist " + TABLE_NAME + ";";


//  Variaveis dos valores da tabela

    private int _id;
    private float total;
    private int id_viagem;
}
