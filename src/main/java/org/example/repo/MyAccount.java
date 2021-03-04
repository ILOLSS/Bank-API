package org.example.repo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashMap;

@Setter
@Getter
@NoArgsConstructor
public class MyAccount {
    private static HashMap<String, BigDecimal> account = new HashMap<>();

    public static void putMoney(String currency, BigDecimal quantity){
        if (account.containsKey(currency)) {
            BigDecimal temp = account.get(currency);
            BigDecimal result = temp.add(quantity);
            account.put(currency, result);
        } else {
            account.put(currency, quantity);
        }
    }

    public static BigDecimal getMoney(String currency){
        return account.get(currency);
    }
}
