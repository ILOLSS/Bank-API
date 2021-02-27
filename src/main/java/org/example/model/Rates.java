package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Rates {
    @JsonProperty("AUD")
    private double aud;
    @JsonProperty("AZN")
    private double azn;
    @JsonProperty("GBP")
    private double gbp;
    @JsonProperty("AMD")
    private double amd;
    @JsonProperty("BYN")
    private double byn;
    @JsonProperty("BGN")
    private double bgn;
    @JsonProperty("BRL")
    private double brl;
    @JsonProperty("HUF")
    private double huf;
    @JsonProperty("HKD")
    private double hkd;
    @JsonProperty("DKK")
    private double dkk;
    @JsonProperty("USD")
    private double usd;
    @JsonProperty("EUR")
    private double eur;
    @JsonProperty("INR")
    private double inr;
    @JsonProperty("KZT")
    private double kzt;
    @JsonProperty("CAD")
    private double cad;
    @JsonProperty("KGS")
    private double kgs;
    @JsonProperty("CNY")
    private double cny;
    @JsonProperty("MDL")
    private double mdl;
    @JsonProperty("NOK")
    private double nok;
    @JsonProperty("PLN")
    private double pln;
    @JsonProperty("RON")
    private double ron;
    @JsonProperty("XDR")
    private double xdr;
    @JsonProperty("SGD")
    private double sgb;
    @JsonProperty("TJS")
    private double tjs;
    @JsonProperty("TRY")
    private double Try;
    @JsonProperty("TMT")
    private double tmt;
    @JsonProperty("UZS")
    private double uzs;
    @JsonProperty("UAH")
    private double uah;
    @JsonProperty("CZJ")
    private double czj;
    @JsonProperty("SEK")
    private double sek;
    @JsonProperty("CHF")
    private double chf;
    @JsonProperty("ZAR")
    private double zar;
    @JsonProperty("KRW")
    private double krw;
    @JsonProperty("JPY")
    private double jpy;

    public double getAud() {
        return aud;
    }

    public double getAzn() {
        return azn;
    }

    public double getGbp() {
        return gbp;
    }

    public double getAmd() {
        return amd;
    }

    public double getByn() {
        return byn;
    }

    public double getBgn() {
        return bgn;
    }

    public double getBrl() {
        return brl;
    }

    public double getHuf() {
        return huf;
    }

    public double getHkd() {
        return hkd;
    }

    public double getDkk() {
        return dkk;
    }

    public double getUsd() {
        return usd;
    }

    public double getEur() {
        return eur;
    }

    public double getInr() {
        return inr;
    }

    public double getKzt() {
        return kzt;
    }

    public double getCad() {
        return cad;
    }

    public double getKgs() {
        return kgs;
    }

    public double getCny() {
        return cny;
    }

    public double getMdl() {
        return mdl;
    }

    public double getNok() {
        return nok;
    }

    public double getPln() {
        return pln;
    }

    public double getRon() {
        return ron;
    }

    public double getXdr() {
        return xdr;
    }

    public double getSgb() {
        return sgb;
    }

    public double getTjs() {
        return tjs;
    }

    public double getTry() {
        return Try;
    }

    public double getTmt() {
        return tmt;
    }

    public double getUzs() {
        return uzs;
    }

    public double getUah() {
        return uah;
    }

    public double getCzj() {
        return czj;
    }

    public double getSek() {
        return sek;
    }

    public double getChf() {
        return chf;
    }

    public double getZar() {
        return zar;
    }

    public double getKrw() {
        return krw;
    }

    public double getJpy() {
        return jpy;
    }

    public void setAud(double aud) {
        this.aud = aud;
    }

    public void setAzn(double azn) {
        this.azn = azn;
    }

    public void setGbp(double gbp) {
        this.gbp = gbp;
    }

    public void setAmd(double amd) {
        this.amd = amd;
    }

    public void setByn(double byn) {
        this.byn = byn;
    }

    public void setBgn(double bgn) {
        this.bgn = bgn;
    }

    public void setBrl(double brl) {
        this.brl = brl;
    }

    public void setHuf(double huf) {
        this.huf = huf;
    }

    public void setHkd(double hkd) {
        this.hkd = hkd;
    }

    public void setDkk(double dkk) {
        this.dkk = dkk;
    }

    public void setUsd(double usd) {
        this.usd = usd;
    }

    public void setEur(double eur) {
        this.eur = eur;
    }

    public void setInr(double inr) {
        this.inr = inr;
    }

    public void setKzt(double kzt) {
        this.kzt = kzt;
    }

    public void setCad(double cad) {
        this.cad = cad;
    }

    public void setKgs(double kgs) {
        this.kgs = kgs;
    }

    public void setCny(double cny) {
        this.cny = cny;
    }

    public void setMdl(double mdl) {
        this.mdl = mdl;
    }

    public void setNok(double nok) {
        this.nok = nok;
    }

    public void setPln(double pln) {
        this.pln = pln;
    }

    public void setRon(double ron) {
        this.ron = ron;
    }

    public void setXdr(double xdr) {
        this.xdr = xdr;
    }

    public void setSgb(double sgb) {
        this.sgb = sgb;
    }

    public void setTjs(double tjs) {
        this.tjs = tjs;
    }

    public void setTry(double Try) {
        this.Try = Try;
    }

    public void setTmt(double tmt) {
        this.tmt = tmt;
    }

    public void setUzs(double uzs) {
        this.uzs = uzs;
    }

    public void setUah(double uah) {
        this.uah = uah;
    }

    public void setCzj(double czj) {
        this.czj = czj;
    }

    public void setSek(double sek) {
        this.sek = sek;
    }

    public void setChf(double chf) {
        this.chf = chf;
    }

    public void setZar(double zar) {
        this.zar = zar;
    }

    public void setKrw(double krw) {
        this.krw = krw;
    }

    public void setJpy(double jpy) {
        this.jpy = jpy;
    }
}
