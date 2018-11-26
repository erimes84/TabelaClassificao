package com.erimes.tabelaclassificao.model;

import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class Atleta {

    private String Voltas;
    private ProgressBar progressoAtleta;
    private String fotoUrl;
    private String Nome;
    private String numeroVoltasAtleta;
    private String TempoTotal;
    private int colocacaoAtleta;
    private String diferecaAtleta;
    private String TempoExibicao;
    private String VoltasDaCategoria;

    public String getVoltasDaCategoria() {
        return VoltasDaCategoria;
    }

    public void setVoltasDaCategoria(String voltasDaCategoria) {
        VoltasDaCategoria = voltasDaCategoria;
    }

    public String getTempoExibicao() {
        return TempoExibicao;
    }

    public void setTempoExibicao(String tempoExibicao) {
        TempoExibicao = tempoExibicao;
    }

    public String getVoltas() {
        return Voltas;
    }

    public void setVoltas(String voltas) {
        Voltas = voltas;
    }

    public ProgressBar getProgressoAtleta() {
        return progressoAtleta;
    }

    public void setProgressoAtleta(ProgressBar progressoAtleta) {
        this.progressoAtleta = progressoAtleta;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getNumeroVoltasAtleta() {
        return numeroVoltasAtleta;
    }

    public void setNumeroVoltasAtleta(String numeroVoltasAtleta) {
        this.numeroVoltasAtleta = numeroVoltasAtleta;
    }

    public String getTempoTotal() {
        return TempoTotal;
    }

    public void setTempoTotal(String tempoTotal) {
        TempoTotal = tempoTotal;
    }

    public int getColocacaoAtleta() {
        return colocacaoAtleta;
    }

    public void setColocacaoAtleta(int colocacaoAtleta) {
        this.colocacaoAtleta = colocacaoAtleta;
    }

    public String getDiferecaAtleta() {
        return diferecaAtleta;
    }

    public void setDiferecaAtleta(String diferecaAtleta) {
        this.diferecaAtleta = diferecaAtleta;
    }


}
