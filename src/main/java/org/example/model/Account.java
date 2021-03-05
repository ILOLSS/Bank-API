package org.example.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.repo.MyAccount;

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
        BigDecimal decimal_100 = new BigDecimal("100");
        BigDecimal one_per = quantity.divide(decimal_100, BigDecimal.ROUND_HALF_UP);
        BigDecimal amount = quantity.subtract(one_per);
        MyAccount.putMoney(currency, one_per);
        if (account.containsKey(currency)) {
            BigDecimal temp = account.get(currency);
            BigDecimal result = temp.add(amount);
            account.put(currency, result);
        } else {
            account.put(currency, amount);
        }
    }

    public BigDecimal getMoney(String val) {
        if (account.containsKey(val)) {
            return account.get(val);
        } else {
            boolean is_null = IsGoodVal.isGoodVal(val);
            if (is_null == true) {
                return new BigDecimal("0");
            } else {
                return new BigDecimal("-1");
            }
        }
    }

    public String withdrawMoney(String val, BigDecimal quantity) {
        BigDecimal decimal_100 = new BigDecimal("100");
        BigDecimal one_per = quantity.divide(decimal_100, BigDecimal.ROUND_HALF_UP);
        BigDecimal amount = quantity.add(one_per);
        if (account.containsKey(val)) {
            BigDecimal temp = account.get(val);
            if (temp.compareTo(amount) == 1 || temp.compareTo(amount) == 0) {//temp >= amount
                BigDecimal result = temp.subtract(amount);
                account.put(val, result);
                MyAccount.putMoney(val, one_per);
                return "ok";
            } else {
                return "no money";
            }
        } else {
            boolean is_null = IsGoodVal.isGoodVal(val);
            if (is_null) {
                return "no money";
            } else {
                return "wrong currency";
            }
        }
    }
}
