package app;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Date;

public class Transaction
{
    private SimpleDoubleProperty amount;
    private SimpleObjectProperty<Date> date;
    private SimpleStringProperty type;
    private SimpleStringProperty description;
    private SimpleDoubleProperty balance;
    private SimpleStringProperty social;

    public Transaction(double amount, Date date, double balance)
    {
        this.amount = new SimpleDoubleProperty(amount);
        this.date = new SimpleObjectProperty<>(date);
        this.balance = new SimpleDoubleProperty(balance);
        
    }

    public double getAmount()
    {
        return amount.get();
    }


    public Date getDate()
    {
        return date.get();
    }


    public double getBalance()
    {
        return balance.get();
    }
}