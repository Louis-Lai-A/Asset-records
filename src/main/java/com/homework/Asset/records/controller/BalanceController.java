package com.homework.Asset.records.controller;

import com.homework.Asset.records.Exception.BusinessException;
import com.homework.Asset.records.service.BalanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.YearMonth;

@RestController
@RequestMapping("/api/balance")
public class BalanceController {
    private final BalanceService balanceService;

    public BalanceController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @GetMapping("/monthly/{userId}/{year}/{month}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<BigDecimal> getMonthlyBalance(
            @PathVariable Long userId,
            @PathVariable int year,
            @PathVariable int month) {
        if (year < 2000 || year > 2100 || month < 1 || month > 12) {
            throw new BusinessException("INVALID_DATE", "Year must be between 2000 and 2100, month between 1 and 12");
        }
        YearMonth yearMonth = YearMonth.of(year, month);
        BigDecimal balance = balanceService.calculateMonthlyBalance(userId, yearMonth);
        return ResponseEntity.ok(balance);
    }
}