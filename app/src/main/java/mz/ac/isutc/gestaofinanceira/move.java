package mz.ac.isutc.gestaofinanceira;

public class move {
    private  double valor;
    private String type;

    public move(double valor, String type) {
        this.valor = valor;
        this.type = type;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
