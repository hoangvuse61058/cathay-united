package com.practice.cathay_united.model.entity.dto;

public class ExchangeRateDetail {
    private String base_currency;
    private String quote_currency;
    private String close_time;
    private String average_bid;
    private String average_ask;
    private String high_bid;
    private String high_ask;
    private String low_bid;
    private String low_ask;

    public ExchangeRateDetail(String base_currency, String quote_currency, String close_time, String average_bid, String average_ask, String high_bid, String high_ask, String low_bid, String low_ask) {
        this.base_currency = base_currency;
        this.quote_currency = quote_currency;
        this.close_time = close_time;
        this.average_bid = average_bid;
        this.average_ask = average_ask;
        this.high_bid = high_bid;
        this.high_ask = high_ask;
        this.low_bid = low_bid;
        this.low_ask = low_ask;
    }

    public String getBase_currency() {
        return base_currency;
    }

    public void setBase_currency(String base_currency) {
        this.base_currency = base_currency;
    }

    public String getQuote_currency() {
        return quote_currency;
    }

    public void setQuote_currency(String quote_currency) {
        this.quote_currency = quote_currency;
    }

    public String getClose_time() {
        return close_time;
    }

    public void setClose_time(String close_time) {
        this.close_time = close_time;
    }

    public String getAverage_bid() {
        return average_bid;
    }

    public void setAverage_bid(String average_bid) {
        this.average_bid = average_bid;
    }

    public String getAverage_ask() {
        return average_ask;
    }

    public void setAverage_ask(String average_ask) {
        this.average_ask = average_ask;
    }

    public String getHigh_bid() {
        return high_bid;
    }

    public void setHigh_bid(String high_bid) {
        this.high_bid = high_bid;
    }

    public String getHigh_ask() {
        return high_ask;
    }

    public void setHigh_ask(String high_ask) {
        this.high_ask = high_ask;
    }

    public String getLow_bid() {
        return low_bid;
    }

    public void setLow_bid(String low_bid) {
        this.low_bid = low_bid;
    }

    public String getLow_ask() {
        return low_ask;
    }

    public void setLow_ask(String low_ask) {
        this.low_ask = low_ask;
    }
}

