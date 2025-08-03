package com.homework.Asset.records.controller;

import com.homework.Asset.records.model.DebtRecord;
import com.homework.Asset.records.service.DebtRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/debt-records")
@Tag(name = "Debt Record Management", description = "APIs for managing debt records")
public class DebtRecordController {

    @Autowired
    private DebtRecordService debtRecordService;

    @GetMapping
    @Operation(summary = "Get all debt records")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved debt records")
    public ResponseEntity<List<DebtRecord>> getAllDebtRecords() {
        return ResponseEntity.ok(debtRecordService.getAllDebtRecords());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get debt record by ID")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved debt record")
    public ResponseEntity<DebtRecord> getDebtRecordById(@PathVariable Long id) {
        return ResponseEntity.ok(debtRecordService.getDebtRecordById(id));
    }

    @PostMapping
    @Operation(summary = "Create a new debt record")
    @ApiResponse(responseCode = "201", description = "Debt record created successfully")
    public ResponseEntity<DebtRecord> createDebtRecord(@RequestBody DebtRecord debtRecord) {
        return ResponseEntity.status(HttpStatus.CREATED).body(debtRecordService.createDebtRecord(debtRecord));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update debt record by ID")
    @ApiResponse(responseCode = "200", description = "Debt record updated successfully")
    public ResponseEntity<DebtRecord> updateDebtRecord(@PathVariable Long id, @RequestBody DebtRecord debtRecord) {
        return ResponseEntity.ok(debtRecordService.updateDebtRecord(id, debtRecord));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete debt record by ID")
    @ApiResponse(responseCode = "204", description = "Debt record deleted successfully")
    public ResponseEntity<Void> deleteDebtRecord(@PathVariable Long id) {
        debtRecordService.deleteDebtRecord(id);
        return ResponseEntity.noContent().build();
    }
}
