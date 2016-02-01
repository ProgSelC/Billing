package ua.falbi.eugene;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "Payments")
public class Payment
{
    @Id
    @GeneratedValue
    private long id;

    private Date date;

    private boolean type;

    private double sum;

    private long clientID;



    public Payment() {
    }

    public Payment(boolean type, double sum, long clientID) {
        this.date = new Date();
        this.type = type;
        this.sum = sum;
        this.clientID = clientID;
    }

    public long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public boolean isType() {
        return type;
    }

    public double getSum() {
        return sum;
    }

    public long getClientID() {
        return clientID;
    }
}

