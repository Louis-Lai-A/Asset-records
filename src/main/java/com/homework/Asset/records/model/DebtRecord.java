package com.homework.Asset.records.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class DebtRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "debt_no", nullable = false, unique = true)
    @JsonProperty("DebtNo")
    private String debtNo;

    @Column(name = "debt_type", nullable = false)
    @JsonProperty("DebtType")
    private String debtType;

    @Column(name = "debt_value", nullable = false, precision = 15, scale = 2)
    @JsonProperty("DebtValue")
    private BigDecimal debtValue;

    @Column(name = "debt_quantity", nullable = false)
    @JsonProperty("DebtQuantity")
    private Integer debtQuantity;

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