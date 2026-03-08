package com.example.resto.dto;

import java.time.LocalDateTime;

public class ReservationDTO {
    private Long id;
    private String tableName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public ReservationDTO(Long id, String tableName, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = id;
        this.tableName = tableName;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Long getId() { return id; }
    public String getTableName() { return tableName; }
    public LocalDateTime getStartTime() { return startTime; }
    public LocalDateTime getEndTime() { return endTime; }
}