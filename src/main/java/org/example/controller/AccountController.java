package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.*;
import org.example.repo.DateBase;
import org.example.repo.MyAccount;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.UUID;

@RestController
public class AccountController {

    @PostMapping("/createAccount")
    public String createAccount(@RequestParam String name, String surname, String dateOfBirth, int seriesOfPassport, int numberOfPassport) {
        Account account = new Account(name, surname, dateOfBirth, seriesOfPassport, numberOfPassport);
        DateBase.putAccount(account.uuid, account);
        return account.uuid.toString();
    }

    @GetMapping("/getMyMoney")
    public BigDecimal getMyMoney(@RequestParam String val) {
        return MyAccount.getMoney(val);
    }

    @GetMapping("/balance")
    public BigDecimal balance(@RequestParam UUID uuid, String val) throws WrongCurrency, WrongUUID {
        Account account = DateBase.getAccount(uuid);
        if (account == null) {
            throw new WrongUUID();
        } else {
            BigDecimal temp = DateBase.getAccount(uuid).getMoney(val);
            BigDecimal dec_neg_1 = new BigDecimal("-1");
            if (temp.compareTo(dec_neg_1) == 0) {
                throw new WrongCurrency();
            } else {
                return temp;
            }
        }
    }

    @PostMapping("/put")
    public void put(@RequestParam UUID uuid, String val, BigDecimal quantity) throws WrongCurrency, WrongUUID {
        boolean is_null = IsGoodVal.isGoodVal(val);
        if (is_null) {
            Account account = DateBase.getAccount(uuid);
            if (account == null) {
                throw new WrongUUID();
            } else {
                BigDecimal decimal_100 = new BigDecimal("100");
                BigDecimal one_per = quantity.divide(decimal_100);
                account.putMoney(val, quantity.subtract(one_per));
                MyAccount.putMoney(val, one_per);
            }
        } else {
            throw new WrongCurrency();
        }

    }

    @PostMapping("/withdraw")
    public void withdraw(@RequestParam UUID uuid, String val, BigDecimal quantity) throws NoMoney, WrongCurrency, WrongUUID {
        Account account = DateBase.getAccount(uuid);
        if (account == null) {
            throw new WrongUUID();
        } else {
            BigDecimal decimal_100 = new BigDecimal("100");
            BigDecimal one_per = quantity.divide(decimal_100);
            String mes = account.withdrawMoney(val, quantity.add(one_per));
            MyAccount.putMoney(val, one_per);
            if (mes == "no money") {
                throw new NoMoney();
            }
            if (mes == "wrong currency") {
                throw new WrongCurrency();
            }
        }
    }

    @GetMapping("/getAccount")
    public Account get(@RequestParam UUID uuid) throws WrongUUID {
        Account account = DateBase.getAccount(uuid);
        if (account == null) {
            throw new WrongUUID();
        } else {
            return DateBase.getAccount(uuid);
        }
    }

    @PostMapping("/buy")
    public void buy(@RequestParam UUID uuid, String val, BigDecimal quantity) throws WrongCurrency, WrongUUID {
        RestTemplate restTemplate = new RestTemplate();
        String jsonString = restTemplate.getForObject("https://www.cbr-xml-daily.ru/latest.js", String.class);
        StringReader reader = new StringReader(jsonString);
        ObjectMapper mapper = new ObjectMapper();
        ValCurs valCurs = null;
        try {
            valCurs = mapper.readValue(reader, ValCurs.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //BigDecimal curs; //стоимость 1 валюты в рублях
        Account account = DateBase.getAccount(uuid);
        if (account == null) {
            throw new WrongUUID();
        } else {
            account.putMoney(val, quantity);
            boolean is_ex = true;
            if (val.equals("AUD")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getAud());
                account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("AZN")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getAzn());
                account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("GBP")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getGbp());
                account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("AMD")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getAmd());
                account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("BYN")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getByn());
                account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("BGN")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getBgn());
                account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("BRL")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getBrl());
                account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("HUF")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getHuf());
                account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("HKD")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getHkd());
                account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("DKK")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getDkk());
                account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("USD")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getUsd());
                account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("EUR")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getEur());
                account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("INR")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getInr());
                account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("KZT")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getKzt());
                account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("CAD")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getCad());
                account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("KGS")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getKgs());
                account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("CNY")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getCny());
                account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("MDL")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getMdl());
                account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("NOK")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getNok());
                account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("PLN")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getPln());
                account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("RON")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getRon());
                account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("XDR")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getXdr());
                account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("SGD")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getSgd());
                account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("TJS")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getTjs());
                account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("TRY")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getTry());
                account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("TMT")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getTmt());
                account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("UZS")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getUzs());
                account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("UAH")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getUah());
                account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("CZJ")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getCzj());
                account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("SEK")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getSek());
                account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("CHF")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getChf());
                account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("ZAR")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getZar());
                account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("KRW")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getKrw());
                account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("JPY")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getJpy());
                account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (is_ex) {
                throw new WrongCurrency();
            }
        }
    }

    @PostMapping("/sell")
    public void sell(@RequestParam UUID uuid, String val, BigDecimal quantity) throws WrongCurrency, WrongUUID {
        RestTemplate restTemplate = new RestTemplate();
        String jsonString = restTemplate.getForObject("https://www.cbr-xml-daily.ru/latest.js", String.class);
        StringReader reader = new StringReader(jsonString);
        ObjectMapper mapper = new ObjectMapper();
        ValCurs valCurs = null;
        try {
            valCurs = mapper.readValue(reader, ValCurs.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //BigDecimal curs; //стоимость 1 валюты в рублях
        Account account = DateBase.getAccount(uuid);
        if (account == null) {
            throw new WrongUUID();
        } else {
            account.withdrawMoney(val, quantity);
            boolean is_ex = true;
            if (val.equals("AUD")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getAud());
                account.putMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("AZN")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getAzn());
                account.putMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("GBP")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getGbp());
                account.putMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("AMD")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getAmd());
                account.putMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("BYN")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getByn());
                account.putMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("BGN")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getBgn());
                account.putMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("BRL")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getBrl());
                account.putMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("HUF")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getHuf());
                account.putMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("HKD")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getHkd());
                account.putMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("DKK")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getDkk());
                account.putMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("USD")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getUsd());
                account.putMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("EUR")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getEur());
                account.putMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("INR")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getInr());
                account.putMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("KZT")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getKzt());
                account.putMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("CAD")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getCad());
                account.putMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("KGS")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getKgs());
                account.putMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("CNY")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getCny());
                account.putMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("MDL")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getMdl());
                account.putMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("NOK")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getNok());
                account.putMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("PLN")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getPln());
                account.putMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("RON")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getRon());
                account.putMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("XDR")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getXdr());
                account.putMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("SGD")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getSgd());
                account.putMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("TJS")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getTjs());
                account.putMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("TRY")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getTry());
                account.putMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("TMT")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getTmt());
                account.putMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("UZS")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getUzs());
                account.putMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("UAH")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getUah());
                account.putMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("CZJ")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getCzj());
                account.putMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("SEK")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getSek());
                account.putMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("CHF")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getChf());
                account.putMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("ZAR")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getZar());
                account.putMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("KRW")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getKrw());
                account.putMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("JPY")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getJpy());
                account.putMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (is_ex) {
                throw new WrongCurrency();
            }
        }
    }
}
