package com.practice.cathay_united.service;

import com.practice.cathay_united.client.OandaClient;
import com.practice.cathay_united.model.entity.dto.ExchangeRateResponse;
import com.practice.cathay_united.model.entity.dto.ExchangeRateResponseApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class ExchangeRateService {

    private final OandaClient oandaClient;

    public ExchangeRateService(OandaClient oandaClient) {
        this.oandaClient = oandaClient;
    }

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    private static final String USD = "USD";
    private static final String DATA_TYPE_CHART = "chart";

    public ExchangeRateResponseApi getExchangeRates(String base, String startDate, String endDate) {
        ExchangeRateResponse exchangeRateResponse = oandaClient.getExchangeRates(base, USD, DATA_TYPE_CHART, startDate, endDate);

        ExchangeRateResponseApi  exchangeRateResponseApi = new ExchangeRateResponseApi();
        exchangeRateResponseApi.setExchangeRateDetail(exchangeRateResponse.getResponse());
        exchangeRateResponseApi.setUpdateTime(FORMATTER.format(LocalDateTime.now()));
        return exchangeRateResponseApi;
    }
}



