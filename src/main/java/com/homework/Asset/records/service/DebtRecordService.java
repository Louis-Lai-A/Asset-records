package com.homework.Asset.records.service;

import com.homework.Asset.records.model.DebtRecord;
import com.homework.Asset.records.repository.DebtRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DebtRecordService {

    @Autowired
    private DebtRecordRepository debtRecordRepository;

    public List<DebtRecord> getAllDebtRecords() {
        return debtRecordRepository.findAll();
    }

    public DebtRecord getDebtRecordById(Long id) {
        return debtRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Debt record not found"));
    }

    public DebtRecord createDebtRecord(DebtRecord debtRecord) {
        return debtRecordRepository.save(debtRecord);
    }

    public DebtRecord updateDebtRecord(Long id, DebtRecord debtRecord) {
        DebtRecord existing = getDebtRecordById(id);
        existing.setDebtNo(debtRecord.getDebtNo());
        existing.setDebtType(debtRecord.getDebtType());
        existing.setDebtValue(debtRecord.getDebtValue());
        existing.setDebtQuantity(debtRecord.getDebtQuantity());
        existing.setOriginTime(debtRecord.getOriginTime());
        return debtRecordRepository.save(existing);
    }

    public void deleteDebtRecord(Long id) {
        debtRecordRepository.deleteById(id);
    }
}
