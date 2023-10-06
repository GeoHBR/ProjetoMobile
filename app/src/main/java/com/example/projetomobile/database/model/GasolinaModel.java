package com.example.projetomobile.database.model;

public class GasolinaModel {
    // Nome da tabela
    public static final String TABLE_NAME = "tb_gasolina";

    //  Colunas da tabela
    public static final String
            COLUNA_ID = "_id",
            COLUNA_TOTALKM = "total_quilometros",
            COLUNA_MEDIAKM = "media_quilometro",
            COLUNA_CUSTO = "custo_medio",

            COLUNA_TOTAL_VEICULO = "total_veiculo",
            COLUNA_TOTAL = "total",
            COLUNA_ID_VIAGEM = "_id_viagem";;

    //  SCript de criação da tabela
    public static final String
            CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
            + " ( "
            + COLUNA_ID + " integer primary key autoincrement, "
            + COLUNA_TOTALKM + " text not null, "
            + COLUNA_MEDIAKM + " text not null, "
            + COLUNA_CUSTO + " text not null, "
            + COLUNA_TOTAL + " real(10,2) not null"
            + "FOREIGN KET("+COLUNA_ID_VIAGEM+") REFERENCES tb_viagem(_id), "
            + " );";

    public static final String
            DROP_TABLE =  "drop table if exist " + TABLE_NAME + ";";


//  Variaveis dos valores da tabela

    private int _id;
    private int id_viagem;
}
