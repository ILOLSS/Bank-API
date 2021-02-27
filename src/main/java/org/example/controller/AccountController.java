package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.repo.DateBase;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.example.model.Account;
import org.example.model.ValCurs;

import java.io.IOException;
import java.io.StringReader;
import java.util.UUID;

@RestController
public class AccountController {

    @PostMapping("/createAccount")
    public String createAccount(@RequestParam String name, String surname, String dateOfBirth, int seriesOfPassport, int numberOfPassport) {
        Account account = new Account(name, surname, dateOfBirth, seriesOfPassport, numberOfPassport);
        DateBase.putAccount(account.uuid, account);
        return account.uuid.toString();
    }

    @GetMapping("/balance")
    public double balance(@RequestParam UUID uuid, String currency) {
        return DateBase.getAccount(uuid).getMoney(currency);
    }

    @PostMapping("/put")
    public void put(@RequestParam UUID uuid, String currency, int quantity) {
        Account account = DateBase.getAccount(uuid);
        account.putMoney(currency, quantity);
    }

    @PostMapping("/withdraw")
    public String withdraw(@RequestParam UUID uuid, String currency, int quantity) {
        Account account = DateBase.getAccount(uuid);
        return account.withdrawMoney(currency, quantity);
    }

    @GetMapping("/getAccount")
    public Account get(@RequestParam UUID uuid) {
        return DateBase.getAccount(uuid);
    }

    @PostMapping("/buy")
    public void buy(@RequestParam UUID uuid, String val, int quantity) {
        RestTemplate restTemplate = new RestTemplate();
        //MappingJacksonHttpMessageConverter converter = new MappingJacksonHttpMessageConverter();
        //converter.setObjectMapper(new ObjectMapper());
        //restTemplate.getMessageConverters().add(converter);
        String jsonString = restTemplate.getForObject("https://www.cbr-xml-daily.ru/latest.js", String.class);
        StringReader reader = new StringReader(jsonString);
        ObjectMapper mapper = new ObjectMapper();
        ValCurs valCurs = null;
        try {
            valCurs = mapper.readValue(reader, ValCurs.class);
            //System.out.println(valCurs.getRates().getEur());
        } catch (IOException e) {
            e.printStackTrace();
        }
        double curs; //стоимость 1 валюты в рублях
        Account account = DateBase.getAccount(uuid);
        account.putMoney(val, quantity);
        if (val == "AUD") {
            curs = 1 / valCurs.getRates().getAud();
            account.withdrawMoney("RUB", quantity * curs);
        }
        if (val == "AZN") {
            curs = 1 / valCurs.getRates().getAzn();
            account.withdrawMoney("RUB", quantity * curs);
        }
        if (val == "GBP") {
            curs = 1 / valCurs.getRates().getGbp();
            account.withdrawMoney("RUB", quantity * curs);
        }
        if (val == "AMD") {
            curs = 1 / valCurs.getRates().getAmd();
            account.withdrawMoney("RUB", quantity * curs);
        }
        if (val == "BYN") {
            curs = 1 / valCurs.getRates().getByn();
            account.withdrawMoney("RUB", quantity * curs);
        }
        if (val == "BGN") {
            curs = 1 / valCurs.getRates().getBgn();
            account.withdrawMoney("RUB", quantity * curs);
        }
        if (val == "BRL") {
            curs = 1 / valCurs.getRates().getBrl();
            account.withdrawMoney("RUB", quantity * curs);
        }
        if (val == "HUF") {
            curs = 1 / valCurs.getRates().getHuf();
            account.withdrawMoney("RUB", quantity * curs);
        }
        if (val == "HKD") {
            curs = 1 / valCurs.getRates().getHkd();
            account.withdrawMoney("RUB", quantity * curs);
        }
        if (val == "DKK") {
            curs = 1 / valCurs.getRates().getDkk();
            account.withdrawMoney("RUB", quantity * curs);
        }
        if (val == "USD") {
            curs = 1 / valCurs.getRates().getUsd();
            account.withdrawMoney("RUB", quantity * curs);
        }
        if (val == "EUR") {
            curs = 1 / valCurs.getRates().getEur();
            account.withdrawMoney("RUB", quantity * curs);
        }
        if (val == "INR") {
            curs = 1 / valCurs.getRates().getInr();
            account.withdrawMoney("RUB", quantity * curs);
        }
        if (val == "KZT") {
            curs = 1 / valCurs.getRates().getKzt();
            account.withdrawMoney("RUB", quantity * curs);
        }
        if (val == "CAD") {
            curs = 1 / valCurs.getRates().getCad();
            account.withdrawMoney("RUB", quantity * curs);
        }
        if (val == "KGS") {
            curs = 1 / valCurs.getRates().getKgs();
            account.withdrawMoney("RUB", quantity * curs);
        }
        if (val == "CNY") {
            curs = 1 / valCurs.getRates().getCny();
            account.withdrawMoney("RUB", quantity * curs);
        }
        if (val == "MDL") {
            curs = 1 / valCurs.getRates().getMdl();
            account.withdrawMoney("RUB", quantity * curs);
        }
        if (val == "NOK") {
            curs = 1 / valCurs.getRates().getNok();
            account.withdrawMoney("RUB", quantity * curs);
        }
        if (val == "PLN") {
            curs = 1 / valCurs.getRates().getPln();
            account.withdrawMoney("RUB", quantity * curs);
        }
        if (val == "RON") {
            curs = 1 / valCurs.getRates().getRon();
            account.withdrawMoney("RUB", quantity * curs);
        }
        if (val == "XDR") {
            curs = 1 / valCurs.getRates().getXdr();
            account.withdrawMoney("RUB", quantity * curs);
        }
        if (val == "SGD") {
            curs = 1 / valCurs.getRates().getSgb();
            account.withdrawMoney("RUB", quantity * curs);
        }
        if (val == "TJS") {
            curs = 1 / valCurs.getRates().getTjs();
            account.withdrawMoney("RUB", quantity * curs);
        }
        if (val == "TRY") {
            curs = 1 / valCurs.getRates().getTry();
            account.withdrawMoney("RUB", quantity * curs);
        }
        if (val == "TMT") {
            curs = 1 / valCurs.getRates().getTmt();
            account.withdrawMoney("RUB", quantity * curs);
        }
        if (val == "UZS") {
            curs = 1 / valCurs.getRates().getUzs();
            account.withdrawMoney("RUB", quantity * curs);
        }
        if (val == "UAH") {
            curs = 1 / valCurs.getRates().getUah();
            account.withdrawMoney("RUB", quantity * curs);
        }
        if (val == "CZJ") {
            curs = 1 / valCurs.getRates().getCzj();
            account.withdrawMoney("RUB", quantity * curs);
        }
        if (val == "SEK") {
            curs = 1 / valCurs.getRates().getSek();
            account.withdrawMoney("RUB", quantity * curs);
        }
        if (val == "CHF") {
            curs = 1 / valCurs.getRates().getChf();
            account.withdrawMoney("RUB", quantity * curs);
        }
        if (val == "ZAR") {
            curs = 1 / valCurs.getRates().getZar();
            account.withdrawMoney("RUB", quantity * curs);
        }
        if (val == "KRW") {
            curs = 1 / valCurs.getRates().getKrw();
            account.withdrawMoney("RUB", quantity * curs);
        }
        if (val == "JPY") {
            curs = 1 / valCurs.getRates().getJpy();
            account.withdrawMoney("RUB", quantity * curs);
        }
    }

    @PostMapping("/sell")
    public void sell(@RequestParam UUID uuid, String val, int quantity) {
        RestTemplate restTemplate = new RestTemplate();
        //MappingJacksonHttpMessageConverter converter = new MappingJacksonHttpMessageConverter();
        //converter.setObjectMapper(new ObjectMapper());
        //restTemplate.getMessageConverters().add(converter);
        String jsonString = restTemplate.getForObject("https://www.cbr-xml-daily.ru/latest.js", String.class);
        StringReader reader = new StringReader(jsonString);
        ObjectMapper mapper = new ObjectMapper();
        ValCurs valCurs = null;
        try {
            valCurs = mapper.readValue(reader, ValCurs.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        double curs; //стоимость 1 валюты в рублях
        Account account = DateBase.getAccount(uuid);
        account.withdrawMoney(val, quantity);
        if (val == "AUD") {
            curs = 1 / valCurs.getRates().getAud();
            account.putMoney("RUB", quantity * curs);
        }
        if (val == "AZN") {
            curs = 1 / valCurs.getRates().getAzn();
            account.putMoney("RUB", quantity * curs);
        }
        if (val == "GBP") {
            curs = 1 / valCurs.getRates().getGbp();
            account.putMoney("RUB", quantity * curs);
        }
        if (val == "AMD") {
            curs = 1 / valCurs.getRates().getAmd();
            account.putMoney("RUB", quantity * curs);
        }
        if (val == "BYN") {
            curs = 1 / valCurs.getRates().getByn();
            account.putMoney("RUB", quantity * curs);
        }
        if (val == "BGN") {
            curs = 1 / valCurs.getRates().getBgn();
            account.putMoney("RUB", quantity * curs);
        }
        if (val == "BRL") {
            curs = 1 / valCurs.getRates().getBrl();
            account.putMoney("RUB", quantity * curs);
        }
        if (val == "HUF") {
            curs = 1 / valCurs.getRates().getHuf();
            account.putMoney("RUB", quantity * curs);
        }
        if (val == "HKD") {
            curs = 1 / valCurs.getRates().getHkd();
            account.putMoney("RUB", quantity * curs);
        }
        if (val == "DKK") {
            curs = 1 / valCurs.getRates().getDkk();
            account.putMoney("RUB", quantity * curs);
        }
        if (val == "USD") {
            curs = 1 / valCurs.getRates().getUsd();
            account.putMoney("RUB", quantity * curs);
        }
        if (val == "EUR") {
            curs = 1 / valCurs.getRates().getEur();
            account.putMoney("RUB", quantity * curs);
        }
        if (val == "INR") {
            curs = 1 / valCurs.getRates().getInr();
            account.putMoney("RUB", quantity * curs);
        }
        if (val == "KZT") {
            curs = 1 / valCurs.getRates().getKzt();
            account.putMoney("RUB", quantity * curs);
        }
        if (val == "CAD") {
            curs = 1 / valCurs.getRates().getCad();
            account.putMoney("RUB", quantity * curs);
        }
        if (val == "KGS") {
            curs = 1 / valCurs.getRates().getKgs();
            account.putMoney("RUB", quantity * curs);
        }
        if (val == "CNY") {
            curs = 1 / valCurs.getRates().getCny();
            account.putMoney("RUB", quantity * curs);
        }
        if (val == "MDL") {
            curs = 1 / valCurs.getRates().getMdl();
            account.putMoney("RUB", quantity * curs);
        }
        if (val == "NOK") {
            curs = 1 / valCurs.getRates().getNok();
            account.putMoney("RUB", quantity * curs);
        }
        if (val == "PLN") {
            curs = 1 / valCurs.getRates().getPln();
            account.putMoney("RUB", quantity * curs);
        }
        if (val == "RON") {
            curs = 1 / valCurs.getRates().getRon();
            account.putMoney("RUB", quantity * curs);
        }
        if (val == "XDR") {
            curs = 1 / valCurs.getRates().getXdr();
            account.putMoney("RUB", quantity * curs);
        }
        if (val == "SGD") {
            curs = 1 / valCurs.getRates().getSgb();
            account.putMoney("RUB", quantity * curs);
        }
        if (val == "TJS") {
            curs = 1 / valCurs.getRates().getTjs();
            account.putMoney("RUB", quantity * curs);
        }
        if (val == "TRY") {
            curs = 1 / valCurs.getRates().getTry();
            account.putMoney("RUB", quantity * curs);
        }
        if (val == "TMT") {
            curs = 1 / valCurs.getRates().getTmt();
            account.putMoney("RUB", quantity * curs);
        }
        if (val == "UZS") {
            curs = 1 / valCurs.getRates().getUzs();
            account.putMoney("RUB", quantity * curs);
        }
        if (val == "UAH") {
            curs = 1 / valCurs.getRates().getUah();
            account.putMoney("RUB", quantity * curs);
        }
        if (val == "CZJ") {
            curs = 1 / valCurs.getRates().getCzj();
            account.putMoney("RUB", quantity * curs);
        }
        if (val == "SEK") {
            curs = 1 / valCurs.getRates().getSek();
            account.putMoney("RUB", quantity * curs);
        }
        if (val == "CHF") {
            curs = 1 / valCurs.getRates().getChf();
            account.putMoney("RUB", quantity * curs);
        }
        if (val == "ZAR") {
            curs = 1 / valCurs.getRates().getZar();
            account.putMoney("RUB", quantity * curs);
        }
        if (val == "KRW") {
            curs = 1 / valCurs.getRates().getKrw();
            account.putMoney("RUB", quantity * curs);
        }
        if (val == "JPY") {
            curs = 1 / valCurs.getRates().getJpy();
            account.putMoney("RUB", quantity * curs);
        }
    }
}
