package com.practice.cathay_united.service;

import com.practice.cathay_united.client.OandaClient;
import com.practice.cathay_united.model.entity.ExchangeRate;
import com.practice.cathay_united.model.entity.dto.ExchangeRateDetail;
import com.practice.cathay_united.model.entity.dto.ExchangeRateResponse;
import com.practice.cathay_united.repository.ExchangeRateRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExchangeRateSyncServiceTest {

    @Mock
    private OandaClient oandaClient;

    @Mock
    private ExchangeRateRepository repository;

    @InjectMocks
    private ExchangeRateSyncService exchangeRateSyncService;

    @Test
    void testSyncExchangeRates_SavesDataCorrectly() {
        // Arrange
        ExchangeRateDetail detail = new ExchangeRateDetail("VND", "USD", "2025-10-01T00:00:00Z",
                "0.0000390558", "0.0000391034", "0.0000390957",
                "0.0000391497", "0.0000389871", "0.0000390137");

        ExchangeRateResponse mockResponse = new ExchangeRateResponse();
        mockResponse.setResponse(List.of(detail));

        when(oandaClient.getExchangeRates("VND", "USD", "chart", "2025-03-26", "2025-03-27"))
                .thenReturn(mockResponse);

        // Act
        exchangeRateSyncService.syncExchangeRates();

        // Assert
        ArgumentCaptor<ExchangeRate> captor = ArgumentCaptor.forClass(ExchangeRate.class);
        verify(repository, times(1)).save(captor.capture());

        ExchangeRate saved = captor.getValue();
        assert saved.getBaseCurrency().equals("VND");
        assert saved.getQuoteCurrency().equals("USD");
        assert saved.getAverageBid().equals("0.0000390558");
        assert saved.getAverageAsk().equals("0.0000391034");
    }

    @Test
    void testSyncExchangeRates_NullResponse_NoSave() {
        when(oandaClient.getExchangeRates(any(), any(), any(), any(), any()))
                .thenReturn(null);

        exchangeRateSyncService.syncExchangeRates();

        verify(repository, never()).save(any());
    }

    @Test
    void testSyncExchangeRates_EmptyList_NoSave() {
        ExchangeRateResponse emptyResponse = new ExchangeRateResponse();
        emptyResponse.setResponse(null);

        when(oandaClient.getExchangeRates(any(), any(), any(), any(), any()))
                .thenReturn(emptyResponse);

        exchangeRateSyncService.syncExchangeRates();

        verify(repository, never()).save(any());
    }
}