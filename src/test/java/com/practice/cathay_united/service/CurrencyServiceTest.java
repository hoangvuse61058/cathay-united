package com.practice.cathay_united.service;

import com.practice.cathay_united.model.entity.Currency;
import com.practice.cathay_united.model.entity.dto.CurrencyRequest;
import com.practice.cathay_united.model.entity.dto.CurrencyResponse;
import com.practice.cathay_united.repository.CurrencyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CurrencyServiceTest {

    @Mock
    private CurrencyRepository currencyRepository;

    @InjectMocks
    private CurrencyService currencyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllSorted() {
        List<Currency> mockCurrencies = List.of(
                new Currency(1L, "USD", "US Dollar"),
                new Currency(2L, "EUR", "Euro")
        );
        when(currencyRepository.findAllByOrderByCodeAsc()).thenReturn(mockCurrencies);

        List<CurrencyResponse> result = currencyService.getAllSorted();

        assertEquals(2, result.size());
        assertEquals("USD", result.get(0).getCode());
        verify(currencyRepository, times(1)).findAllByOrderByCodeAsc();
    }

    @Test
    void testAddCurrency() {
        CurrencyRequest request = new CurrencyRequest("JPY", "Japanese Yen");
        currencyService.addCurrency(request);

        verify(currencyRepository, times(1)).save(any(Currency.class));
    }

    @Test
    void testUpdateCurrency_Success() {
        Long id = 1L;
        Currency existing = new Currency(id, "GBP", "British Pound");
        CurrencyRequest request = new CurrencyRequest("GBP", "Pound Sterling");

        when(currencyRepository.findById(id)).thenReturn(Optional.of(existing));

        currencyService.updateCurrency(id, request);

        assertEquals("Pound Sterling", existing.getName());
        verify(currencyRepository).save(existing);
    }

    @Test
    void testUpdateCurrency_NotFound() {
        Long id = 99L;
        CurrencyRequest request = new CurrencyRequest("ABC", "Unknown");

        when(currencyRepository.findById(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                currencyService.updateCurrency(id, request));

        assertEquals("Currency not found: 99", exception.getMessage());
        verify(currencyRepository, never()).save(any());
    }

    @Test
    void testDeleteCurrency() {
        Long id = 5L;

        currencyService.deleteCurrency(id);

        verify(currencyRepository, times(1)).deleteById(id);
    }
}