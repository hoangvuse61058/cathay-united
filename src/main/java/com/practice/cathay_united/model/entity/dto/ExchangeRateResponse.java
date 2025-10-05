package com.practice.cathay_united.model.entity.dto;

import java.util.List;

public class ExchangeRateResponse {
    private List<ExchangeRateDetail> response;

    public List<ExchangeRateDetail> getResponse() {
        return response;
    }

    public void setResponse(List<ExchangeRateDetail> response) {
        this.response = response;
    }
}

