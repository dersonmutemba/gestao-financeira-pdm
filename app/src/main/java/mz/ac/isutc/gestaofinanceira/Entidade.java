package mz.ac.isutc.gestaofinanceira;

public class Entidade {

    long id;
    String nome, categoria;
    long usuario;

    public Entidade(long id, String nome, String categoria, long usuario) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.usuario = usuario;
    }

    public Entidade(long id, String nome, long usuario) {
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

    public long getUsuario() {
        return usuario;
    }

    public void setUsuario(long usuario) {
        this.usuario = usuario;
    }
}
