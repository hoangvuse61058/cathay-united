package com.practice.cathay_united.service;

import com.practice.cathay_united.model.entity.Currency;
import com.practice.cathay_united.model.entity.dto.CurrencyRequest;
import com.practice.cathay_united.model.entity.dto.CurrencyResponse;
import com.practice.cathay_united.model.entity.mapper.CurrencyMapper;
import com.practice.cathay_united.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyService {

    @Autowired
    private final CurrencyRepository currencyRepository;

    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public List<CurrencyResponse> getAllSorted() {
        List<Currency> currencies = currencyRepository.findAllByOrderByCodeAsc();

        return CurrencyMapper.mapToResponse(currencies);
    }

    public void addCurrency(CurrencyRequest request) {
        Currency currency = CurrencyMapper.mapToEntity(request);
        currencyRepository.save(currency);
    }

    public void updateCurrency(Long id, CurrencyRequest request) {
        Currency existing = currencyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Currency not found: " + id));
        existing.setName(request.getName());

        currencyRepository.save(existing);
    }

    public void deleteCurrency(Long id) {
        currencyRepository.deleteById(id);
    }
}
