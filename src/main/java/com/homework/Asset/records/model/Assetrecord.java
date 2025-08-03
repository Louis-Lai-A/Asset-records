package com.homework.Asset.records.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "asset_records")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Assetrecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "asset_no", nullable = false, unique = true)
    @JsonProperty("AssetNo")
    private String assetNo;

    @Column(name = "project_type", nullable = false)
    @JsonProperty("ProjectType")
    private String projectType;

    @Column(name = "project_value", nullable = false, precision = 15, scale = 2)
    @JsonProperty("ProjectValue")
    private BigDecimal projectValue;

    @Column(name = "project_quantity", nullable = false)
    @JsonProperty("ProjectQuantity")
    private Integer projectQuantity;

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