package com.practice.cathay_united.controller;

import com.practice.cathay_united.model.entity.dto.CurrencyRequest;
import com.practice.cathay_united.model.entity.dto.CurrencyResponse;
import com.practice.cathay_united.service.CurrencyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/currencies")
@Tag(name = "Currency API", description = "Manage currency data")
public class CurrencyController {
    private static final Logger logger = LogManager.getLogger(CurrencyController.class);

    @Autowired
    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping
    @Operation(summary = "Get all currencies", description = "Returns a list of all currencies sorted by code")
    public List<CurrencyResponse> getAllCurrencies() {
        logger.info("[getAllCurrencies]");
        List<CurrencyResponse> response = currencyService.getAllSorted();
        logger.info("[getAllCurrencies] Response: {}", response);

        return response;
    }

    @PostMapping
    @Operation(summary = "Add a new currency", description = "Creates a new currency entry")
    public void addCurrency(@Valid @RequestBody CurrencyRequest request) {
        logger.info("[addCurrency] request: {}", request);
        currencyService.addCurrency(request);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update currency", description = "Updates the name of an existing currency by ID")
    public void updateCurrency(@PathVariable Long id, @Valid @RequestBody CurrencyRequest request) {
        logger.info("[updateCurrency] request: {}", request);
        currencyService.updateCurrency(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete currency", description = "Deletes a currency by ID")
    public void deleteCurrency(@PathVariable Long id) {
        logger.info("[deleteCurrency] request: {}", id);
        currencyService.deleteCurrency(id);
    }
}
