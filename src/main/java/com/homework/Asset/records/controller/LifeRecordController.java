package com.homework.Asset.records.controller;

import com.homework.Asset.records.model.LifeRecord;
import com.homework.Asset.records.model.LifeRecord;
import com.homework.Asset.records.service.LifeRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/life-records")
@Tag(name = "Life Record Management", description = "APIs for managing life records")
public class LifeRecordController {

    @Autowired
    private LifeRecordService lifeRecordService;

    @GetMapping
    @Operation(summary = "Get all life records")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved life records")
    public ResponseEntity<List<LifeRecord>> getAllLifeRecords() {
        return ResponseEntity.ok(lifeRecordService.getAllLifeRecords());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get life record by ID")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved life record")
    public ResponseEntity<LifeRecord> getLifeRecordById(@PathVariable Long id) {
        return ResponseEntity.ok(lifeRecordService.getLifeRecordById(id));
    }

    @PostMapping
    @Operation(summary = "Create a new life record")
    @ApiResponse(responseCode = "201", description = "Life record created successfully")
    public ResponseEntity<LifeRecord> createLifeRecord(@RequestBody LifeRecord lifeRecord) {
        return ResponseEntity.status(HttpStatus.CREATED).body(lifeRecordService.createLifeRecord(lifeRecord));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update life record by ID")
    @ApiResponse(responseCode = "200", description = "Life record updated successfully")
    public ResponseEntity<LifeRecord> updateLifeRecord(@PathVariable Long id, @RequestBody LifeRecord lifeRecord) {
        return ResponseEntity.ok(lifeRecordService.updateLifeRecord(id, lifeRecord));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete life record by ID")
    @ApiResponse(responseCode = "204", description = "Life record deleted successfully")
    public ResponseEntity<Void> deleteLifeRecord(@PathVariable Long id) {
        lifeRecordService.deleteLifeRecord(id);
        return ResponseEntity.noContent().build();
    }
}