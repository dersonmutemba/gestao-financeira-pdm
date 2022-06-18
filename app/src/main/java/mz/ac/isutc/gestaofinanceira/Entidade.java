package mz.ac.isutc.gestaofinanceira;

import java.io.Serializable;

public class Entidade implements Serializable {

    long id;
    String nome, categoria;
    String usuario;

    public Entidade(long id, String nome, String categoria, String usuario) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.usuario = usuario;
    }

    public Entidade(long id, String nome, String usuario) {
        this.id = id;
        this.nome = nome;
        this.usuario = usuario;
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
