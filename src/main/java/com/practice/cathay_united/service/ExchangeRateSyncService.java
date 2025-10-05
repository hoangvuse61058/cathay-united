package com.practice.cathay_united.service;

import com.practice.cathay_united.client.OandaClient;
import com.practice.cathay_united.controller.ExchangeRateController;
import com.practice.cathay_united.model.entity.ExchangeRate;
import com.practice.cathay_united.model.entity.dto.ExchangeRateDetail;
import com.practice.cathay_united.model.entity.dto.ExchangeRateResponse;
import com.practice.cathay_united.repository.ExchangeRateRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ExchangeRateSyncService {
    private static final Logger logger = LogManager.getLogger(ExchangeRateController.class);

    @Autowired
    private OandaClient oandaClient;

    @Autowired
    private ExchangeRateRepository repository;

    private static final String USD = "USD";
    private static final String VND = "VND";
    private static final String DATA_TYPE_CHART = "chart";
    private static final String START_DATE = "2025-03-26";
    private static final String END_DATE = "2025-03-27";

    @Scheduled(cron = "*/30 * * * * *")
    public void syncExchangeRates() {
        logger.info("[syncExchangeRates] Start to syncExchangeRates");
        ExchangeRateResponse response = oandaClient.getExchangeRates(VND, USD, DATA_TYPE_CHART, START_DATE, END_DATE);

        if (response != null && response.getResponse() != null) {
            for (ExchangeRateDetail detail : response.getResponse()) {
                ExchangeRate rate = new ExchangeRate();
                rate.setBaseCurrency(detail.getBase_currency());
                rate.setQuoteCurrency(detail.getQuote_currency());
                rate.setCloseTime(detail.getClose_time());
                rate.setAverageBid(detail.getAverage_bid());
                rate.setAverageAsk(detail.getAverage_ask());

                repository.save(rate);

                logger.info("[syncExchangeRates] syncExchangeRates with data: {}", rate);
            }
        }
    }
}
