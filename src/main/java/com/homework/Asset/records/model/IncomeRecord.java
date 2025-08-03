package com.homework.Asset.records.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "income_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncomeRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "income_no", nullable = false, unique = true)
    @JsonProperty("IncomeNo")
    private String incomeNo;

    @Column(name = "income_type", nullable = false)
    @JsonProperty("IncomeType")
    private String incomeType;

    @Column(name = "income_value", nullable = false, precision = 15, scale = 2)
    @JsonProperty("IncomeValue")
    private BigDecimal incomeValue;

    @Column(name = "income_quantity", nullable = false)
    @JsonProperty("IncomeQuantity")
    private Integer incomeQuantity;

    @Column(name = "origin_time", nullable = false)
    @JsonProperty("OriginTime")
    private LocalDateTime originTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}