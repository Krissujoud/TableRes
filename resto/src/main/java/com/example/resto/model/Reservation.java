package com.example.resto.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long tableId; // viide tabelile ID kaudu

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Reservation() {}

    public Reservation(Long tableId, LocalDateTime startTime, LocalDateTime endTime) {
        this.tableId = tableId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Long getId() { return id; }
    public Long getTableId() { return tableId; }
    public LocalDateTime getStartTime() { return startTime; }
    public LocalDateTime getEndTime() { return endTime; }
}