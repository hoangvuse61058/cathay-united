package com.practice.cathay_united.model.entity.mapper;

import com.practice.cathay_united.model.entity.Currency;
import com.practice.cathay_united.model.entity.dto.CurrencyRequest;
import com.practice.cathay_united.model.entity.dto.CurrencyResponse;

import java.util.List;
import java.util.stream.Collectors;

public class CurrencyMapper {

    public static List<CurrencyResponse> mapToResponse(List<Currency> currencies) {
        return currencies.stream()
                .map(c -> new CurrencyResponse(c.getCode(), c.getName()))
                .collect(Collectors.toList());
    }

    public static Currency mapToEntity(CurrencyRequest request) {
        Currency currency = new Currency();
        currency.setCode(request.getCode());
        currency.setName(request.getName());
        return currency;
    }
}
