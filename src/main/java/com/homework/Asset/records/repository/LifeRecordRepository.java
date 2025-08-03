package com.homework.Asset.records.repository;

import com.homework.Asset.records.model.Assetrecord;
import com.homework.Asset.records.model.LifeRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LifeRecordRepository extends JpaRepository<LifeRecord, Long> {
    List<LifeRecord> findByUser_IdAndOriginTimeBetween(Long userId, LocalDateTime start, LocalDateTime end);
}