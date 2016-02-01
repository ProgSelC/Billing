package ua.falbi.eugene;

import javax.persistence.*;


@Entity
@Table(name = "Clients")
public class Client
{
    @Id
    @GeneratedValue
    private long id;

    private String name;

    private double tarif;

    private double balance;

    private boolean deleted;


    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "logo_id")
    private Logo logo;



    public Client() {
    }

    public Client(String name, double tarif, double balance, Logo logo) {
        this.name = name;
        this.tarif = tarif;
        this.balance = balance;
        this.logo = logo;
        this.deleted = false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTarif() {
        return tarif;
    }

    public void setTarif(double tarif) {
        this.tarif = tarif;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Logo getLogo() {
        return logo;
    }

    public void setLogo(Logo logo) {
        this.logo = logo;
    }
}

