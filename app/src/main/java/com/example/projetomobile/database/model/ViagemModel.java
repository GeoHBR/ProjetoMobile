package com.example.projetomobile.database.model;

public class ViagemModel {
    // Nome da tabela
    public static final String TABLE_NAME = "";

    //  Colunas da tabela
    public static final String
            COLUNA_ID = "_id",
            COLUNA_NOME = "nome",
            COLUNA_DATA_INICIO = "data_inicio",
            COLUNA_DATA_FIM = "data_fim",
            COLUNA_QUANT_PESSOAS = "quant_pessoas",
            COLUNA_DESTINO = "destino";

    //  SCript de criação da tabela
    public static final String
            CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
            + " ( "
            + COLUNA_ID + " integer primary key autoincrement, "
            + COLUNA_NOME + " text not null, "
            + COLUNA_DATA_INICIO + " text not null, "
            + COLUNA_DATA_FIM + " text not null, "
            + COLUNA_QUANT_PESSOAS + " integer not null, "
            + COLUNA_DESTINO + " text not null "
            + " );";

    public static final String
            DROP_TABLE =  "drop table if exist " + TABLE_NAME + ";";


//  Variaveis dos valores da tabela

    private int _id;
    private String dataInicio;
    private String dataFim;
    private String nome;
    private int quantPessoas;
    private String destino;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantPessoas() {
        return quantPessoas;
    }

    public void setQuantPessoas(int quantPessoas) {
        this.quantPessoas = quantPessoas;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }
}
