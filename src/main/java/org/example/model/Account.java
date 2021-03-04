package org.example.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
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
    private HashMap<String, BigDecimal> account = new HashMap<>();
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

    public void putMoney(String currency, BigDecimal quantity) {
        if (account.containsKey(currency)) {
            BigDecimal temp = account.get(currency);
            BigDecimal result = temp.add(quantity);
            account.put(currency, result);
        } else {
            account.put(currency, quantity);
        }
    }

    public BigDecimal getMoney(String val) {
        if (account.containsKey(val)) {
            return account.get(val);
        } else {
            boolean is_null = false;
            if (val.equals("AUD")) {
                is_null = true;
            }
            if (val.equals("AZN")) {
                is_null = true;
            }
            if (val.equals("GBP")) {
                is_null = true;
            }
            if (val.equals("AMD")) {
                is_null = true;
            }
            if (val.equals("BYN")) {
                is_null = true;
            }
            if (val.equals("BGN")) {
                is_null = true;
            }
            if (val.equals("BRL")) {
                is_null = true;
            }
            if (val.equals("HUF")) {
                is_null = true;
            }
            if (val.equals("HKD")) {
                is_null = true;
            }
            if (val.equals("DKK")) {
                is_null = true;
            }
            if (val.equals("USD")) {
                is_null = true;
            }
            if (val.equals("EUR")) {
                is_null = true;
            }
            if (val.equals("INR")) {
                is_null = true;
            }
            if (val.equals("KZT")) {
                is_null = true;
            }
            if (val.equals("CAD")) {
                is_null = true;
            }
            if (val.equals("KGS")) {
                is_null = true;
            }
            if (val.equals("CNY")) {
                is_null = true;
            }
            if (val.equals("MDL")) {
                is_null = true;
            }
            if (val.equals("NOK")) {
                is_null = true;
            }
            if (val.equals("PLN")) {
                is_null = true;
            }
            if (val.equals("RON")) {
                is_null = true;
            }
            if (val.equals("XDR")) {
                is_null = true;
            }
            if (val.equals("SGD")) {
                is_null = true;
            }
            if (val.equals("TJS")) {
                is_null = true;
            }
            if (val.equals("TRY")) {
                is_null = true;
            }
            if (val.equals("TMT")) {
                is_null = true;
            }
            if (val.equals("UZS")) {
                is_null = true;
            }
            if (val.equals("UAH")) {
                is_null = true;
            }
            if (val.equals("CZJ")) {
                is_null = true;
            }
            if (val.equals("SEK")) {
                is_null = true;
            }
            if (val.equals("CHF")) {
                is_null = true;
            }
            if (val.equals("ZAR")) {
                is_null = true;
            }
            if (val.equals("KRW")) {
                is_null = true;
            }
            if (val.equals("JPY")) {
                is_null = true;
            }
            if (val.equals("RUB")) {
                is_null = true;
            }
            if (is_null == true) {
                return new BigDecimal("0");
            }else{
                return new BigDecimal("-1");
            }
        }
    }

    public String withdrawMoney(String val, BigDecimal quantity) {
        if (account.containsKey(val)) {
            BigDecimal temp = account.get(val);
            if (temp.compareTo(quantity) == 1 || temp.compareTo(quantity) == 0) {//temp >= quantity
                BigDecimal result = temp.subtract(quantity);
                account.put(val, result);
                return "ok";
            } else {
                return "no money";
            }
        } else {
            boolean is_null = false;
            if (val.equals("AUD")) {
                is_null = true;
            }
            if (val.equals("AZN")) {
                is_null = true;
            }
            if (val.equals("GBP")) {
                is_null = true;
            }
            if (val.equals("AMD")) {
                is_null = true;
            }
            if (val.equals("BYN")) {
                is_null = true;
            }
            if (val.equals("BGN")) {
                is_null = true;
            }
            if (val.equals("BRL")) {
                is_null = true;
            }
            if (val.equals("HUF")) {
                is_null = true;
            }
            if (val.equals("HKD")) {
                is_null = true;
            }
            if (val.equals("DKK")) {
                is_null = true;
            }
            if (val.equals("USD")) {
                is_null = true;
            }
            if (val.equals("EUR")) {
                is_null = true;
            }
            if (val.equals("INR")) {
                is_null = true;
            }
            if (val.equals("KZT")) {
                is_null = true;
            }
            if (val.equals("CAD")) {
                is_null = true;
            }
            if (val.equals("KGS")) {
                is_null = true;
            }
            if (val.equals("CNY")) {
                is_null = true;
            }
            if (val.equals("MDL")) {
                is_null = true;
            }
            if (val.equals("NOK")) {
                is_null = true;
            }
            if (val.equals("PLN")) {
                is_null = true;
            }
            if (val.equals("RON")) {
                is_null = true;
            }
            if (val.equals("XDR")) {
                is_null = true;
            }
            if (val.equals("SGD")) {
                is_null = true;
            }
            if (val.equals("TJS")) {
                is_null = true;
            }
            if (val.equals("TRY")) {
                is_null = true;
            }
            if (val.equals("TMT")) {
                is_null = true;
            }
            if (val.equals("UZS")) {
                is_null = true;
            }
            if (val.equals("UAH")) {
                is_null = true;
            }
            if (val.equals("CZJ")) {
                is_null = true;
            }
            if (val.equals("SEK")) {
                is_null = true;
            }
            if (val.equals("CHF")) {
                is_null = true;
            }
            if (val.equals("ZAR")) {
                is_null = true;
            }
            if (val.equals("KRW")) {
                is_null = true;
            }
            if (val.equals("JPY")) {
                is_null = true;
            }
            if (val.equals("RUB")) {
                is_null = true;
            }
            if (is_null) {
                return "no money";
            }else{
                return "wrong currency";
            }
        }
    }
}
