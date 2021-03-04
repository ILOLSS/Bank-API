package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Rates {
    @JsonProperty("AUD")
    private BigDecimal aud;
    @JsonProperty("AZN")
    private BigDecimal azn;
    @JsonProperty("GBP")
    private BigDecimal gbp;
    @JsonProperty("AMD")
    private BigDecimal amd;
    @JsonProperty("BYN")
    private BigDecimal byn;
    @JsonProperty("BGN")
    private BigDecimal bgn;
    @JsonProperty("BRL")
    private BigDecimal brl;
    @JsonProperty("HUF")
    private BigDecimal huf;
    @JsonProperty("HKD")
    private BigDecimal hkd;
    @JsonProperty("DKK")
    private BigDecimal dkk;
    @JsonProperty("USD")
    private BigDecimal usd;
    @JsonProperty("EUR")
    private BigDecimal eur;
    @JsonProperty("INR")
    private BigDecimal inr;
    @JsonProperty("KZT")
    private BigDecimal kzt;
    @JsonProperty("CAD")
    private BigDecimal cad;
    @JsonProperty("KGS")
    private BigDecimal kgs;
    @JsonProperty("CNY")
    private BigDecimal cny;
    @JsonProperty("MDL")
    private BigDecimal mdl;
    @JsonProperty("NOK")
    private BigDecimal nok;
    @JsonProperty("PLN")
    private BigDecimal pln;
    @JsonProperty("RON")
    private BigDecimal ron;
    @JsonProperty("XDR")
    private BigDecimal xdr;
    @JsonProperty("SGD")
    private BigDecimal sgd;
    @JsonProperty("TJS")
    private BigDecimal tjs;
    @JsonProperty("TRY")
    private BigDecimal Try;
    @JsonProperty("TMT")
    private BigDecimal tmt;
    @JsonProperty("UZS")
    private BigDecimal uzs;
    @JsonProperty("UAH")
    private BigDecimal uah;
    @JsonProperty("CZJ")
    private BigDecimal czj;
    @JsonProperty("SEK")
    private BigDecimal sek;
    @JsonProperty("CHF")
    private BigDecimal chf;
    @JsonProperty("ZAR")
    private BigDecimal zar;
    @JsonProperty("KRW")
    private BigDecimal krw;
    @JsonProperty("JPY")
    private BigDecimal jpy;

}
