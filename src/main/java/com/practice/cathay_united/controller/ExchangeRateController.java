package com.practice.cathay_united.controller;

import com.practice.cathay_united.model.entity.dto.ExchangeRateResponseApi;
import com.practice.cathay_united.service.ExchangeRateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rates")
@Tag(name = "Exchange Rate API", description = "Retrieve exchange rates from OANDA")
public class ExchangeRateController {
    private static final Logger logger = LogManager.getLogger(ExchangeRateController.class);

    @Autowired
    private ExchangeRateService service;

    @GetMapping
    @Operation(
            summary = "Get exchange rates",
            description = "Returns exchange rate data between the base currency and USD for a given date range"
    )
    public ExchangeRateResponseApi getRates(
            @Parameter(description = "Base currency code, e.g. VND") @RequestParam String base,
            @Parameter(description = "Start date in yyyy-MM-dd format") @RequestParam String start_date,
            @Parameter(description = "End date in yyyy-MM-dd format") @RequestParam String end_date
    ) {
        logger.info("[getRates] base: {}, start_date: {}, end_date: {}", base, start_date, end_date);
        return service.getExchangeRates(base, start_date, end_date);
    }
}