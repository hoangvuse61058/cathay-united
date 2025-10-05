package com.practice.cathay_united.model.entity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CurrencyRequest {

    @NotBlank(message = "Currency code must not be blank")
    @Size(max = 3, message = "Currency code must be at most 3 characters")
    private String code;

    @NotBlank(message = "Currency name must not be blank")
    private String name;

    public CurrencyRequest(String code, String name) {
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
