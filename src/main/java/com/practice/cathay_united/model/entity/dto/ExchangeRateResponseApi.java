package com.practice.cathay_united.model.entity.dto;

import java.util.List;

public class ExchangeRateResponseApi {

    private String updateTime; // Định dạng: yyyy/MM/dd HH:mm:ss

    private List<ExchangeRateDetail> exchangeRateDetail;


    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public List<ExchangeRateDetail> getExchangeRateDetail() {
        return exchangeRateDetail;
    }

    public void setExchangeRateDetail(List<ExchangeRateDetail> exchangeRateDetail) {
        this.exchangeRateDetail = exchangeRateDetail;
    }
}

