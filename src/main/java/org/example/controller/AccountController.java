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
                account.putMoney(val, quantity);
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
            String mes = account.withdrawMoney(val, quantity);
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
            String message = null;
            boolean is_ex = true;
            if (val.equals("AUD")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getAud(),BigDecimal.ROUND_HALF_UP);
                message = account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("AZN")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getAzn(),BigDecimal.ROUND_HALF_UP);
                message = account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("GBP")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getGbp(),BigDecimal.ROUND_HALF_UP);
                message = account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("AMD")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getAmd(),BigDecimal.ROUND_HALF_UP);
                message = account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("BYN")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getByn(),BigDecimal.ROUND_HALF_UP);
                message = account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("BGN")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getBgn(),BigDecimal.ROUND_HALF_UP);
                message = account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("BRL")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getBrl(),BigDecimal.ROUND_HALF_UP);
                message = account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("HUF")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getHuf(),BigDecimal.ROUND_HALF_UP);
                message = account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("HKD")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getHkd(),BigDecimal.ROUND_HALF_UP);
                message = account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("DKK")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getDkk(),BigDecimal.ROUND_HALF_UP);
                message = account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("USD")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getUsd(),BigDecimal.ROUND_HALF_UP);
                message = account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("EUR")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getEur(),BigDecimal.ROUND_HALF_UP);
                message = account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("INR")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getInr(),BigDecimal.ROUND_HALF_UP);
                message = account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("KZT")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getKzt(),BigDecimal.ROUND_HALF_UP);
                message = account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("CAD")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getCad(),BigDecimal.ROUND_HALF_UP);
                message = account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("KGS")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getKgs(),BigDecimal.ROUND_HALF_UP);
                message = account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("CNY")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getCny(),BigDecimal.ROUND_HALF_UP);
                message = account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("MDL")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getMdl(),BigDecimal.ROUND_HALF_UP);
                message = account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("NOK")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getNok(),BigDecimal.ROUND_HALF_UP);
                message = account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("PLN")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getPln(),BigDecimal.ROUND_HALF_UP);
                message = account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("RON")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getRon(),BigDecimal.ROUND_HALF_UP);
                message = account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("XDR")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getXdr(),BigDecimal.ROUND_HALF_UP);
                message = account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("SGD")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getSgd(),BigDecimal.ROUND_HALF_UP);
                message = account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("TJS")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getTjs(),BigDecimal.ROUND_HALF_UP);
                message = account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("TRY")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getTry(),BigDecimal.ROUND_HALF_UP);
                message = account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("TMT")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getTmt(),BigDecimal.ROUND_HALF_UP);
                message = account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("UZS")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getUzs(),BigDecimal.ROUND_HALF_UP);
                message = account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("UAH")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getUah(),BigDecimal.ROUND_HALF_UP);
                message = account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("CZJ")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getCzj(),BigDecimal.ROUND_HALF_UP);
                message = account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("SEK")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getSek(),BigDecimal.ROUND_HALF_UP);
                message = account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("CHF")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getChf(),BigDecimal.ROUND_HALF_UP);
                message = account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("ZAR")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getZar(),BigDecimal.ROUND_HALF_UP);
                message = account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("KRW")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getKrw(),BigDecimal.ROUND_HALF_UP);
                message = account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (val.equals("JPY")) {
                BigDecimal temp = new BigDecimal("1");
                BigDecimal curs = temp.divide(valCurs.getRates().getJpy(),BigDecimal.ROUND_HALF_UP);
                message = account.withdrawMoney("RUB", quantity.multiply(curs));
                is_ex = false;
            }
            if (is_ex) {
                throw new WrongCurrency();
            }
            if (message.equals("ok")) {
                account.putMoney(val, quantity);
            }
        }
    }

    @PostMapping("/sell")
    public void sell(@RequestParam UUID uuid, String val, BigDecimal quantity) throws WrongCurrency, WrongUUID, NoMoney {
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
            String message = account.withdrawMoney(val, quantity);
            if (message.equals("ok")) {
                boolean is_ex = true;
                if (val.equals("AUD")) {
                    BigDecimal temp = new BigDecimal("1");
                    BigDecimal curs = temp.divide(valCurs.getRates().getAud(),BigDecimal.ROUND_HALF_UP);
                    account.putMoney("RUB", quantity.multiply(curs));
                }
                if (val.equals("AZN")) {
                    BigDecimal temp = new BigDecimal("1");
                    BigDecimal curs = temp.divide(valCurs.getRates().getAzn(),BigDecimal.ROUND_HALF_UP);
                    account.putMoney("RUB", quantity.multiply(curs));
                }
                if (val.equals("GBP")) {
                    BigDecimal temp = new BigDecimal("1");
                    BigDecimal curs = temp.divide(valCurs.getRates().getGbp(),BigDecimal.ROUND_HALF_UP);
                    account.putMoney("RUB", quantity.multiply(curs));
                }
                if (val.equals("AMD")) {
                    BigDecimal temp = new BigDecimal("1");
                    BigDecimal curs = temp.divide(valCurs.getRates().getAmd(),BigDecimal.ROUND_HALF_UP);
                    account.putMoney("RUB", quantity.multiply(curs));
                }
                if (val.equals("BYN")) {
                    BigDecimal temp = new BigDecimal("1");
                    BigDecimal curs = temp.divide(valCurs.getRates().getByn(),BigDecimal.ROUND_HALF_UP);
                    account.putMoney("RUB", quantity.multiply(curs));
                }
                if (val.equals("BGN")) {
                    BigDecimal temp = new BigDecimal("1");
                    BigDecimal curs = temp.divide(valCurs.getRates().getBgn(),BigDecimal.ROUND_HALF_UP);
                    account.putMoney("RUB", quantity.multiply(curs));
                }
                if (val.equals("BRL")) {
                    BigDecimal temp = new BigDecimal("1");
                    BigDecimal curs = temp.divide(valCurs.getRates().getBrl(),BigDecimal.ROUND_HALF_UP);
                    account.putMoney("RUB", quantity.multiply(curs));
                }
                if (val.equals("HUF")) {
                    BigDecimal temp = new BigDecimal("1");
                    BigDecimal curs = temp.divide(valCurs.getRates().getHuf(),BigDecimal.ROUND_HALF_UP);
                    account.putMoney("RUB", quantity.multiply(curs));
                }
                if (val.equals("HKD")) {
                    BigDecimal temp = new BigDecimal("1");
                    BigDecimal curs = temp.divide(valCurs.getRates().getHkd(),BigDecimal.ROUND_HALF_UP);
                    account.putMoney("RUB", quantity.multiply(curs));
                }
                if (val.equals("DKK")) {
                    BigDecimal temp = new BigDecimal("1");
                    BigDecimal curs = temp.divide(valCurs.getRates().getDkk(),BigDecimal.ROUND_HALF_UP);
                    account.putMoney("RUB", quantity.multiply(curs));
                }
                if (val.equals("USD")) {
                    BigDecimal temp = new BigDecimal("1");
                    BigDecimal curs = temp.divide(valCurs.getRates().getUsd(),BigDecimal.ROUND_HALF_UP);
                    account.putMoney("RUB", quantity.multiply(curs));
                }
                if (val.equals("EUR")) {
                    BigDecimal temp = new BigDecimal("1");
                    BigDecimal curs = temp.divide(valCurs.getRates().getEur(),BigDecimal.ROUND_HALF_UP);
                    account.putMoney("RUB", quantity.multiply(curs));
                }
                if (val.equals("INR")) {
                    BigDecimal temp = new BigDecimal("1");
                    BigDecimal curs = temp.divide(valCurs.getRates().getInr(),BigDecimal.ROUND_HALF_UP);
                    account.putMoney("RUB", quantity.multiply(curs));
                }
                if (val.equals("KZT")) {
                    BigDecimal temp = new BigDecimal("1");
                    BigDecimal curs = temp.divide(valCurs.getRates().getKzt(),BigDecimal.ROUND_HALF_UP);
                    account.putMoney("RUB", quantity.multiply(curs));
                }
                if (val.equals("CAD")) {
                    BigDecimal temp = new BigDecimal("1");
                    BigDecimal curs = temp.divide(valCurs.getRates().getCad(),BigDecimal.ROUND_HALF_UP);
                    account.putMoney("RUB", quantity.multiply(curs));
                }
                if (val.equals("KGS")) {
                    BigDecimal temp = new BigDecimal("1");
                    BigDecimal curs = temp.divide(valCurs.getRates().getKgs(),BigDecimal.ROUND_HALF_UP);
                    account.putMoney("RUB", quantity.multiply(curs));
                }
                if (val.equals("CNY")) {
                    BigDecimal temp = new BigDecimal("1");
                    BigDecimal curs = temp.divide(valCurs.getRates().getCny(),BigDecimal.ROUND_HALF_UP);
                    account.putMoney("RUB", quantity.multiply(curs));
                }
                if (val.equals("MDL")) {
                    BigDecimal temp = new BigDecimal("1");
                    BigDecimal curs = temp.divide(valCurs.getRates().getMdl(),BigDecimal.ROUND_HALF_UP);
                    account.putMoney("RUB", quantity.multiply(curs));
                }
                if (val.equals("NOK")) {
                    BigDecimal temp = new BigDecimal("1");
                    BigDecimal curs = temp.divide(valCurs.getRates().getNok(),BigDecimal.ROUND_HALF_UP);
                    account.putMoney("RUB", quantity.multiply(curs));
                }
                if (val.equals("PLN")) {
                    BigDecimal temp = new BigDecimal("1");
                    BigDecimal curs = temp.divide(valCurs.getRates().getPln(),BigDecimal.ROUND_HALF_UP);
                    account.putMoney("RUB", quantity.multiply(curs));
                }
                if (val.equals("RON")) {
                    BigDecimal temp = new BigDecimal("1");
                    BigDecimal curs = temp.divide(valCurs.getRates().getRon(),BigDecimal.ROUND_HALF_UP);
                    account.putMoney("RUB", quantity.multiply(curs));
                }
                if (val.equals("XDR")) {
                    BigDecimal temp = new BigDecimal("1");
                    BigDecimal curs = temp.divide(valCurs.getRates().getXdr(),BigDecimal.ROUND_HALF_UP);
                    account.putMoney("RUB", quantity.multiply(curs));
                }
                if (val.equals("SGD")) {
                    BigDecimal temp = new BigDecimal("1");
                    BigDecimal curs = temp.divide(valCurs.getRates().getSgd(),BigDecimal.ROUND_HALF_UP);
                    account.putMoney("RUB", quantity.multiply(curs));
                }
                if (val.equals("TJS")) {
                    BigDecimal temp = new BigDecimal("1");
                    BigDecimal curs = temp.divide(valCurs.getRates().getTjs(),BigDecimal.ROUND_HALF_UP);
                    account.putMoney("RUB", quantity.multiply(curs));
                }
                if (val.equals("TRY")) {
                    BigDecimal temp = new BigDecimal("1");
                    BigDecimal curs = temp.divide(valCurs.getRates().getTry(),BigDecimal.ROUND_HALF_UP);
                    account.putMoney("RUB", quantity.multiply(curs));
                }
                if (val.equals("TMT")) {
                    BigDecimal temp = new BigDecimal("1");
                    BigDecimal curs = temp.divide(valCurs.getRates().getTmt(),BigDecimal.ROUND_HALF_UP);
                    account.putMoney("RUB", quantity.multiply(curs));
                }
                if (val.equals("UZS")) {
                    BigDecimal temp = new BigDecimal("1");
                    BigDecimal curs = temp.divide(valCurs.getRates().getUzs(),BigDecimal.ROUND_HALF_UP);
                    account.putMoney("RUB", quantity.multiply(curs));
                }
                if (val.equals("UAH")) {
                    BigDecimal temp = new BigDecimal("1");
                    BigDecimal curs = temp.divide(valCurs.getRates().getUah(),BigDecimal.ROUND_HALF_UP);
                    account.putMoney("RUB", quantity.multiply(curs));
                }
                if (val.equals("CZJ")) {
                    BigDecimal temp = new BigDecimal("1");
                    BigDecimal curs = temp.divide(valCurs.getRates().getCzj(),BigDecimal.ROUND_HALF_UP);
                    account.putMoney("RUB", quantity.multiply(curs));
                }
                if (val.equals("SEK")) {
                    BigDecimal temp = new BigDecimal("1");
                    BigDecimal curs = temp.divide(valCurs.getRates().getSek(),BigDecimal.ROUND_HALF_UP);
                    account.putMoney("RUB", quantity.multiply(curs));
                }
                if (val.equals("CHF")) {
                    BigDecimal temp = new BigDecimal("1");
                    BigDecimal curs = temp.divide(valCurs.getRates().getChf(),BigDecimal.ROUND_HALF_UP);
                    account.putMoney("RUB", quantity.multiply(curs));
                }
                if (val.equals("ZAR")) {
                    BigDecimal temp = new BigDecimal("1");
                    BigDecimal curs = temp.divide(valCurs.getRates().getZar(),BigDecimal.ROUND_HALF_UP);
                    account.putMoney("RUB", quantity.multiply(curs));
                }
                if (val.equals("KRW")) {
                    BigDecimal temp = new BigDecimal("1");
                    BigDecimal curs = temp.divide(valCurs.getRates().getKrw(),BigDecimal.ROUND_HALF_UP);
                    account.putMoney("RUB", quantity.multiply(curs));
                }
                if (val.equals("JPY")) {
                    BigDecimal temp = new BigDecimal("1");
                    BigDecimal curs = temp.divide(valCurs.getRates().getJpy(),BigDecimal.ROUND_HALF_UP);
                    account.putMoney("RUB", quantity.multiply(curs));
                }
            }else{
                if (message.equals("no money")){
                    throw new NoMoney();
                }
                if (message.equals("wrong currency")){
                    throw new WrongCurrency();
                }
            }
        }
    }
}
