package mz.ac.isutc.gestaofinanceira;

import java.util.Calendar;

public class Transaction {
    private long id;
    private String type;
    private double amount;
    private String dateTime;
    private String title;
    private long entidade;

    public Transaction(long id, String type, double amount, String dateTime, String title, long entidade) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.dateTime = dateTime;
        this.title = title;
        this.entidade = entidade;
    }

    public Transaction(String title,String dateTime, long entidade, String type,double amount){
        this.title = title;
        this.type = type;
        this.amount = amount;
        this.dateTime = dateTime;
        this.entidade = entidade;
    }

    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getEntidade() {
        return entidade;
    }

    public void setEntidade(long entidade) {
        this.entidade = entidade;
    }
}
