package com.example.projetomobile.database.model;

public class ViagemModel {
    // Nome da tabela
    public static final String TABLE_NAME = "tb_viagem";

    //  Colunas da tabela
    public static final String
            COLUNA_ID = "_id",
            COLUNA_DATA_INICIO = "data_inicio",
            COLUNA_DATA_FIM = "data_fim",
            COLUNA_QUANT_PESSOAS = "quant_pessoas",
            COLUNA_DESTINO = "destino",
            COLUNA_ID_USUARIO = "_id_usuario",
            COLUNA_ID_GASOLINA = "_id_gasolina",
            COLUNA_ID_TARIFA = "_id_tarifa",
            COLUNA_ID_REFEICAO = "_id_refeicao",
            COLUNA_ID_HOSPEDAGEM = "_id_hospedagem";


    //  SCript de criação da tabela
    public static final String
            CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
            + " ( "
            + COLUNA_ID + " integer primary key autoincrement, "
            + COLUNA_DATA_INICIO + " text not null, "
            + COLUNA_DATA_FIM + " text not null, "
            + COLUNA_QUANT_PESSOAS + " integer not null, "
            + COLUNA_DESTINO + " text not null, "
            + COLUNA_ID_USUARIO + " int, "
            + COLUNA_ID_TARIFA + " int, "
            + COLUNA_ID_GASOLINA + " int, "
            + COLUNA_ID_REFEICAO + " int, "
            + COLUNA_ID_HOSPEDAGEM + " int, "
            + "FOREIGN KEY("+COLUNA_ID_USUARIO+") REFERENCES tb_usuario(_id), "
            + "FOREIGN KEY("+COLUNA_ID_GASOLINA+") REFERENCES tb_gasolina(_id), "
            + "FOREIGN KEY("+COLUNA_ID_HOSPEDAGEM+") REFERENCES tb_hospedagem(_id), "
            + "FOREIGN KEY("+COLUNA_ID_REFEICAO+") REFERENCES tb_refeicao(_id), "
            + "FOREIGN KEY("+COLUNA_ID_TARIFA+") REFERENCES tb_tarifa(_id) "
            + ");";

    public static final String
            DROP_TABLE =  "drop table if exist " + TABLE_NAME + ";";


//  Variaveis dos valores da tabela

    private int _id;
    private String dataInicio;
    private String dataFim;
    private int quantPessoas;
    private String destino;
    private int _idUsuario;
    private int _idGasolina;
    private int _idHospedagem;
    private int _idTarifa;
    private int _idRefeicao;

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

    public int get_idGasolina() {
        return _idGasolina;
    }

    public void set_idGasolina(int _idGasolina) {
        this._idGasolina = _idGasolina;
    }

    public int get_idHospedagem() {
        return _idHospedagem;
    }

    public void set_idHospedagem(int _idHospedagem) {
        this._idHospedagem = _idHospedagem;
    }

    public int get_idTarifa() {
        return _idTarifa;
    }

    public void set_idTarifa(int _idTarifa) {
        this._idTarifa = _idTarifa;
    }

    public int get_idRefeicao() {
        return _idRefeicao;
    }

    public void set_idRefeicao(int _idRefeicao) {
        this._idRefeicao = _idRefeicao;
    }

    public int get_idUsuario() {
        return _idUsuario;
    }

    public void set_idUsuario(int _idUsuario) {
        this._idUsuario = _idUsuario;
    }
}
