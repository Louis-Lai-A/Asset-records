package com.homework.Asset.records.controller;

import com.homework.Asset.records.model.Assetrecord;
import com.homework.Asset.records.service.AssetrecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assets")
public class AssetrecordController {

    @Autowired
    private AssetrecordService assetrecordService;

    @GetMapping
    @Operation(summary = "Get all asset records")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved assets")
    public ResponseEntity<List<Assetrecord>> getAllAssets() {
        return ResponseEntity.ok(assetrecordService.getAllAssets());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get asset by ID")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved asset")
    public ResponseEntity<Assetrecord> getAssetById(@PathVariable Long id) {
        return ResponseEntity.ok(assetrecordService.getAssetById(id));
    }

    @PostMapping
    @Operation(summary = "Create a new asset record")
    @ApiResponse(responseCode = "201", description = "Asset created successfully")
    public ResponseEntity<Assetrecord> createAsset(@RequestBody Assetrecord assetrecord) {
        return ResponseEntity.status(HttpStatus.CREATED).body(assetrecordService.createAsset(assetrecord));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update asset by ID")
    @ApiResponse(responseCode = "200", description = "Asset updated successfully")
    public ResponseEntity<Assetrecord> updateAsset(@PathVariable Long id, @RequestBody Assetrecord assetrecord) {
        return ResponseEntity.ok(assetrecordService.updateAsset(id, assetrecord));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete asset by ID")
    @ApiResponse(responseCode = "204", description = "Asset deleted successfully")
    public ResponseEntity<Void> deleteAsset(@PathVariable Long id) {
        assetrecordService.deleteAsset(id);
        return ResponseEntity.noContent().build();
    }
}