package mz.ac.isutc.gestaofinanceira;

import java.io.Serializable;

public class Conta implements Serializable {

    private long id;
    private String accountName, associatedBank, usuario;
    private double accountAmount;

    public Conta(long id, String accountName, String associatedBank, double accountAmount, String usuario) {
        this.id = id;
        this.accountName = accountName;
        this.associatedBank = associatedBank;
        this.accountAmount = accountAmount;
        this.usuario = usuario;
    }

    public Conta(long id, String accountName, double accountAmount, String usuario) {
        this.id = id;
        this.accountName = accountName;
        this.accountAmount = accountAmount;
        this.usuario = usuario;
    }

    public long getId() {
        return id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAssociatedBank() {
        return associatedBank;
    }

    public void setAssociatedBank(String associatedBank) {
        this.associatedBank = associatedBank;
    }

    public double getAccountAmount() {
        return accountAmount;
    }

    public void setAccountAmount(double accountAmount) {
        this.accountAmount = accountAmount;
    }

    public String getUsuario() {
        return usuario;
    }
}
