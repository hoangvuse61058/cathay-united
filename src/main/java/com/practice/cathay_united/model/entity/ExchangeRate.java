package com.practice.cathay_united.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "exchange_rates")
public class ExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String baseCurrency;
    private String quoteCurrency;
    private String closeTime;
    private String averageBid;
    private String averageAsk;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getQuoteCurrency() {
        return quoteCurrency;
    }

    public void setQuoteCurrency(String quoteCurrency) {
        this.quoteCurrency = quoteCurrency;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public String getAverageBid() {
        return averageBid;
    }

    public void setAverageBid(String averageBid) {
        this.averageBid = averageBid;
    }

    public String getAverageAsk() {
        return averageAsk;
    }

    public void setAverageAsk(String averageAsk) {
        this.averageAsk = averageAsk;
    }
}
