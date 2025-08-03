package com.homework.Asset.records.repository;

import com.homework.Asset.records.model.DebtRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DebtRecordRepository extends JpaRepository<DebtRecord, Long> {
    List<DebtRecord> findByUser_IdAndOriginTimeBetween(Long userId, LocalDateTime start, LocalDateTime end);
}