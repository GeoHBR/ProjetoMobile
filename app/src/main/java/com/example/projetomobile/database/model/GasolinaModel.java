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
            COLUNA_ID_VIAGEM = "_id_viagem";

    //  SCript de criação da tabela
    public static final String
            CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
            + " ( "
            + COLUNA_ID + " integer primary key autoincrement, "
            + COLUNA_TOTALKM + " numeric(10,2) not null, "
            + COLUNA_MEDIAKM + " numeric(10,2) not null, "
            + COLUNA_CUSTO + " numeric(10,2) not null, "
            + COLUNA_TOTAL + " numeric(10,2) not null"
            + "FOREIGN KET("+COLUNA_ID_VIAGEM+") REFERENCES tb_viagem(_id), "
            + " );";

    public static final String
            DROP_TABLE =  "drop table if exist " + TABLE_NAME + ";";


//  Variaveis dos valores da tabela

    private int _id;
    private float totalKM;
    private float medialKM;
    private int totalVeiculo;
    private float total;
    private int id_viagem;

//
    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public float getTotalKM() {
        return totalKM;
    }

    public void setTotalKM(float totalKM) {
        this.totalKM = totalKM;
    }

    public float getMedialKM() {
        return medialKM;
    }

    public void setMedialKM(float medialKM) {
        this.medialKM = medialKM;
    }

    public int getTotalVeiculo() {
        return totalVeiculo;
    }

    public void setTotalVeiculo(int totalVeiculo) {
        this.totalVeiculo = totalVeiculo;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getId_viagem() {
        return id_viagem;
    }

    public void setId_viagem(int id_viagem) {
        this.id_viagem = id_viagem;
    }
}
