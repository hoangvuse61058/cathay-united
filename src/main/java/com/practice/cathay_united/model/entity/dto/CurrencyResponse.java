package com.practice.cathay_united.model.entity.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

public class CurrencyResponse {
    private String code;
    private String name;

    public CurrencyResponse(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
