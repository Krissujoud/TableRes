package com.example.resto.service;

import com.example.resto.dto.SearchRequest;
import com.example.resto.model.*;
import com.example.resto.repository.*;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Soovitusalgoritmi aitas koostada TI.
 */
@Service
public class RecommendationService {

    private final TableRepository tableRepo;
    private final ReservationRepository reservationRepo;

    // Süstime reposid teenusesse
    public RecommendationService(TableRepository tableRepo, ReservationRepository reservationRepo) {
        this.tableRepo = tableRepo;
        this.reservationRepo = reservationRepo;
    }

    // Tagastab kuni 3 sobivaimat lauda päringu järgi
    public List<Table> recommend(SearchRequest request) {

        // Võtame kõik lauad
        List<Table> tables = tableRepo.findAll();

        // Filtreerime tsooni ja istekohtade järgi
        tables = tables.stream()
                .filter(t -> t.getZone() == request.zone)
                .filter(t -> t.getSeats() >= request.peopleCount)
                .collect(Collectors.toList());

        // Arvutame skoorid
        Map<Table, Integer> scores = new HashMap<>();

        for (Table table : tables) {

            // Vähem ülearuseid kohti = parem skoor
            int score = 100 - (table.getSeats() - request.peopleCount) * 15;

            // Eelistused annavad boonuspunkte
            if (request.preferences != null) {
                if (request.preferences.contains(Preference.WINDOW) && table.isWindowSeat())
                    score += 20;

                if (request.preferences.contains(Preference.QUIET) && table.isQuiet())
                    score += 20;

                if (request.preferences.contains(Preference.KIDS_AREA) && table.isNearKids())
                    score += 20;
            }

            scores.put(table, score);
        }

        // Sorteerime skoori järgi ja võtame top 3
        return scores.entrySet()
                .stream()
                .sorted((a, b) -> b.getValue() - a.getValue())
                .map(Map.Entry::getKey)
                .limit(3)
                .collect(Collectors.toList());
    }

    // Kontrollib, kas laud on broneeritud (lihtne olemasolukontroll)
    public boolean isReserved(Long tableId) {
        return reservationRepo.findAll()
                .stream()
                .anyMatch(r -> r.getTableId().equals(tableId));
    }
}
