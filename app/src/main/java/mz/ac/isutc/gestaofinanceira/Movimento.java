package mz.ac.isutc.gestaofinanceira;

public class Movimento {

    long id;
    String tipo;
    double valor;
    String data, titulo, hora;
    long entidade;

    public Movimento(long id, String tipo, double valor, String data, String titulo, String hora, long entidade) {
        this.id = id;
        this.tipo = tipo;
        this.valor = valor;
        this.data = data;
        this.titulo = titulo;
        this.hora = hora;
        this.entidade = entidade;
    }

    public long getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public long getEntidade() {
        return entidade;
    }

    public void setEntidade(long entidade) {
        this.entidade = entidade;
    }
}
