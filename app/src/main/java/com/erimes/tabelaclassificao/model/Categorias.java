package com.erimes.tabelaclassificao.model;

public class Categorias {

    private String nomeCategoria = "";
    private String Voltas = "";
    private String de = "";
    private String ate = "";

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public String getVoltas() {
        return Voltas;
    }

    public void setVoltas(String voltas) {
        Voltas = voltas;
    }

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }

    public String getAte() {
        return ate;
    }

    public void setAte(String ate) {
        this.ate = ate;
    }

    @Override
    public String toString() {
        return nomeCategoria;
    }
}
