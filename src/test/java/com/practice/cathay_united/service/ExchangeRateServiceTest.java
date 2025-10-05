package com.practice.cathay_united.service;

import com.practice.cathay_united.client.OandaClient;
import com.practice.cathay_united.model.entity.dto.ExchangeRateDetail;
import com.practice.cathay_united.model.entity.dto.ExchangeRateResponse;
import com.practice.cathay_united.model.entity.dto.ExchangeRateResponseApi;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExchangeRateServiceTest {

    @Mock
    private OandaClient oandaClient;

    @InjectMocks
    private ExchangeRateService exchangeRateService;

    @Test
    void testGetExchangeRates_ReturnsCorrectResponse() {
        // Arrange
        String base = "VND";
        String startDate = "2025-10-01";
        String endDate = "2025-10-01";

        List<ExchangeRateDetail> mockDetails = List.of(
                new ExchangeRateDetail("VND", "USD", "2025-10-01T00:00:00Z",
                        "0.0000390558", "0.0000391034", "0.0000390957",
                        "0.0000391497", "0.0000389871", "0.0000390137")
        );

        ExchangeRateResponse mockResponse = new ExchangeRateResponse();
        mockResponse.setResponse(mockDetails);

        when(oandaClient.getExchangeRates(base, "USD", "chart", startDate, endDate))
                .thenReturn(mockResponse);

        // Act
        ExchangeRateResponseApi result = exchangeRateService.getExchangeRates(base, startDate, endDate);

        // Assert
        assertEquals(1, result.getExchangeRateDetail().size());
        verify(oandaClient).getExchangeRates(base, "USD", "chart", startDate, endDate);
    }
}
