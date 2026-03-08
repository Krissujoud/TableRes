package com.example.resto.service;

import com.example.resto.model.Table;
import com.example.resto.model.Zone;
import com.example.resto.repository.TableRepository;
import com.example.resto.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TableService {

    private final TableRepository tableRepository;
    private final ReservationRepository reservationRepository;

    public TableService(TableRepository tableRepository,
                        ReservationRepository reservationRepository) {
        this.tableRepository = tableRepository;
        this.reservationRepository = reservationRepository;
    }

    // 1. Tagasta kõik lauad
    public List<Table> getAllTables() {
        return tableRepository.findAll();
    }

    // 2. Soovitusalgoritm: tagastab top 3 laudade ID-d
    public List<Table> recommendTables(int guests, String zoneStr) {

        Zone zone;
        try {
            zone = Zone.valueOf(zoneStr.toUpperCase().replace(" ", "_"));
        } catch (Exception e) {
            zone = null; // kui vale tsoon, tagasta kõik
        }

        LocalDateTime now = LocalDateTime.now();

        Zone finalZone = zone;
        List<Table> candidates = tableRepository.findAll().stream()
                .filter(t -> !isReserved(t.getId(), now))
                .filter(t -> finalZone == null || t.getZone() == finalZone)
                .collect(Collectors.toList());

        // lihtne skoor: väikseim laud, mis mahutab seltskonna
        candidates.sort(Comparator.comparingInt(t -> t.getSeats() - guests));

        // tagasta kuni 3 laud
        return candidates.stream().limit(3).collect(Collectors.toList());
    }

    // 3. Kontrolli, kas laud on broneeritud hetkel
    public boolean isReserved(Long tableId, LocalDateTime time) {
        return reservationRepository.findByTableId(tableId)
                .stream()
                .anyMatch(r -> !time.isBefore(r.getStartTime()) && !time.isAfter(r.getEndTime()));
    }
}