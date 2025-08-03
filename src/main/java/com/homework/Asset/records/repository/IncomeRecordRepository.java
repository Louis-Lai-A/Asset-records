package com.homework.Asset.records.repository;

import com.homework.Asset.records.model.IncomeRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IncomeRecordRepository extends JpaRepository<IncomeRecord, Long> {
    // 自定義查詢：根據用戶ID和時間範圍查找收入記錄
    List<IncomeRecord> findByUser_IdAndOriginTimeBetween(Long userId, LocalDateTime start, LocalDateTime end);

    // 可選：根據收入類型和用戶ID查詢
    List<IncomeRecord> findByUserIdAndIncomeType(Long userId, String incomeType);

    // 可選：檢查收入編號是否唯一
    boolean existsByIncomeNo(String incomeNo);
}