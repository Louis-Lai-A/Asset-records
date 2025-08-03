package com.homework.Asset.records.service;

import com.homework.Asset.records.Exception.BusinessException;
import com.homework.Asset.records.model.Assetrecord;
import com.homework.Asset.records.model.DebtRecord;
import com.homework.Asset.records.model.IncomeRecord;
import com.homework.Asset.records.model.LifeRecord;
import com.homework.Asset.records.repository.AssetRecordRepository;
import com.homework.Asset.records.repository.DebtRecordRepository;
import com.homework.Asset.records.repository.IncomeRecordRepository;
import com.homework.Asset.records.repository.LifeRecordRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;

@Service
public class BalanceService {
    private final AssetRecordRepository assetRepository;
    private final DebtRecordRepository debtRepository;
    private final LifeRecordRepository lifeRepository;
    private final IncomeRecordRepository incomeRepository;

    public BalanceService(AssetRecordRepository assetRepository,
                          DebtRecordRepository debtRepository,
                          LifeRecordRepository lifeRepository,
                          IncomeRecordRepository incomeRepository) {
        this.assetRepository = assetRepository;
        this.debtRepository = debtRepository;
        this.lifeRepository = lifeRepository;
        this.incomeRepository = incomeRepository;
    }

    public BigDecimal calculateMonthlyBalance(Long userId, YearMonth month) {
        if (userId == null || month == null) {
            throw new BusinessException("INVALID_INPUT", "User ID or month cannot be null");
        }

        LocalDateTime start = month.atDay(1).atStartOfDay();
        LocalDateTime end = month.atEndOfMonth().atTime(23, 59, 59);

        try {
            BigDecimal totalIncome = incomeRepository.findByUser_IdAndOriginTimeBetween(userId, start, end)
                    .stream()
                    .map(IncomeRecord::getIncomeValue)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            BigDecimal totalAssets = assetRepository.findByUser_IdAndOriginTimeBetween(userId, start, end)
                    .stream()
                    .map(Assetrecord::getProjectValue)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            BigDecimal totalDebts = debtRepository.findByUser_IdAndOriginTimeBetween(userId, start, end)
                    .stream()
                    .map(DebtRecord::getDebtValue)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            BigDecimal totalExpenses = lifeRepository.findByUser_IdAndOriginTimeBetween(userId, start, end)
                    .stream()
                    .map(LifeRecord::getConsumptionValue)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            return totalIncome.add(totalAssets).subtract(totalDebts).subtract(totalExpenses);
        } catch (Exception e) {
            throw new BusinessException("CALCULATION_ERROR", "Failed to calculate balance: " + e.getMessage());
        }
    }
}