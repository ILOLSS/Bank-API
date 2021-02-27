package org.example.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
public class Account {

    private String name;
    private String surname;
    private String dateOfBirth;
    private Date date;
    private int seriesOfPassport;
    private int numerOfPassport;
    private HashMap<String, Double> account = new HashMap<>();
    public UUID uuid;

    public Account(String name, String surname, String dateOfBirth, int seriesOfPassport, int numerOfPassport) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.date = new Date();
        this.seriesOfPassport = seriesOfPassport;
        this.numerOfPassport = numerOfPassport;
        this.uuid = UUID.randomUUID();
    }

    public void putMoney(String currency, double quantity) {
        if (account.containsKey(currency)) {
            double temp = account.get(currency);
            account.put(currency, temp + quantity);
        } else {
            account.put(currency, quantity);
        }
    }

    public double getMoney(String currency) {
        if (account.containsKey(currency)) {
            return account.get(currency);
        } else {
            return 0;
        }
    }

    public String withdrawMoney(String currency, double quantity) {
        if (account.containsKey(currency)) {
            double temp = account.get(currency);
            if (temp >= quantity) {
                account.put(currency, temp - quantity);
                return "ok";
            }else{
                return "error";
            }
        } else {
            return "error";
        }
    }
}
