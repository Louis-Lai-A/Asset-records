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
public class LifeRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "consumption_no", nullable = false, unique = true)
    @JsonProperty("ConsumptionNo")
    private String consumptionNo;

    @Column(name = "consumption_type", nullable = false)
    @JsonProperty("ConsumptionType")
    private String consumptionType;

    @Column(name = "consumption_value", nullable = false, precision = 15, scale = 2)
    @JsonProperty("ConsumptionValue")
    private BigDecimal consumptionValue;

    @Column(name = "consumption_quantity", nullable = false)
    @JsonProperty("ConsumptionQuantity")
    private Integer consumptionQuantity;

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
