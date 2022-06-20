package mz.ac.isutc.gestaofinanceira;

import java.io.Serializable;

public class Subscricao implements Serializable {

    private long id;
    private String nome, dataDeRegisto, periodicidade;
    private double valor;
    private String tipo;
    private long entidade;

    public Subscricao(long id, String nome, String dataDeRegisto, String periodicidade,
                      double valor, String tipo, long entidade) {
        this.id = id;
        this.nome = nome;
        this.dataDeRegisto = dataDeRegisto;
        this.periodicidade = periodicidade;
        this.valor = valor;
        this.tipo = tipo;
        this.entidade = entidade;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataDeRegisto() {
        return dataDeRegisto;
    }

    public void setDataDeRegisto(String dataDeRegisto) {
        this.dataDeRegisto = dataDeRegisto;
    }

    public String getPeriodicidade() {
        return periodicidade;
    }

    public void setPeriodicidade(String periodicidade) {
        this.periodicidade = periodicidade;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public long getEntidade() {
        return entidade;
    }

    public void setEntidade(long entidade) {
        this.entidade = entidade;
    }
}
