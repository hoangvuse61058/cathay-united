package com.practice.cathay_united.client;

import com.practice.cathay_united.model.entity.dto.ExchangeRateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "oandaClient", url = "https://fxds-public-exchange-rates-api.oanda.com")
public interface OandaClient {

    @GetMapping("/cc-api/currencies")
    ExchangeRateResponse getExchangeRates(
            @RequestParam("base") String base,
            @RequestParam("quote") String quote,
            @RequestParam("data_type") String dataType,
            @RequestParam("start_date") String startDate,
            @RequestParam("end_date") String endDate
    );
}

