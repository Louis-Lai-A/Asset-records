package com.homework.Asset.records.service;

import com.homework.Asset.records.model.Assetrecord;
import com.homework.Asset.records.repository.AssetRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetrecordService {

    @Autowired
    private AssetRecordRepository assetRecordRepository;

    public List<Assetrecord> getAllAssets() {
        return assetRecordRepository.findAll();
    }

    public Assetrecord getAssetById(Long id) {
        return assetRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asset not found"));
    }

    public Assetrecord createAsset(Assetrecord assetrecord) {
        return assetRecordRepository.save(assetrecord);
    }

    public Assetrecord updateAsset(Long id, Assetrecord assetrecord) {
        Assetrecord existing = getAssetById(id);
        existing.setAssetNo(assetrecord.getAssetNo());
        existing.setProjectType(assetrecord.getProjectType());
        existing.setProjectValue(assetrecord.getProjectValue());
        existing.setProjectQuantity(assetrecord.getProjectQuantity());
        existing.setOriginTime(assetrecord.getOriginTime());
        return assetRecordRepository.save(existing);
    }

    public void deleteAsset(Long id) {
        assetRecordRepository.deleteById(id);
    }
}
