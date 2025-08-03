package com.homework.Asset.records.service;

import com.homework.Asset.records.model.LifeRecord;
import com.homework.Asset.records.model.LifeRecord;
import com.homework.Asset.records.repository.LifeRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LifeRecordService {

    @Autowired
    private LifeRecordRepository lifeRecordRepository;

    public List<LifeRecord> getAllLifeRecords() {
        return lifeRecordRepository.findAll();
    }

    public LifeRecord getLifeRecordById(Long id) {
        return lifeRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Life record not found"));
    }

    public LifeRecord createLifeRecord(LifeRecord lifeRecord) {
        return lifeRecordRepository.save(lifeRecord);
    }

    public LifeRecord updateLifeRecord(Long id, LifeRecord lifeRecord) {
        LifeRecord existing = getLifeRecordById(id);
        existing.setConsumptionNo(lifeRecord.getConsumptionNo());
        existing.setConsumptionType(lifeRecord.getConsumptionType());
        existing.setConsumptionValue(lifeRecord.getConsumptionValue());
        existing.setConsumptionQuantity(lifeRecord.getConsumptionQuantity());
        existing.setOriginTime(lifeRecord.getOriginTime());
        return lifeRecordRepository.save(existing);
    }

    public void deleteLifeRecord(Long id) {
        lifeRecordRepository.deleteById(id);
    }
}
