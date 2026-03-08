package com.example.resto.service;

import com.example.resto.dto.ReservationDTO;
import com.example.resto.model.Reservation;
import com.example.resto.model.Table;
import com.example.resto.repository.ReservationRepository;
import com.example.resto.repository.TableRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final TableRepository tableRepository;

    public ReservationService(ReservationRepository reservationRepository, TableRepository tableRepository) {
        this.reservationRepository = reservationRepository;
        this.tableRepository = tableRepository;
    }

    public List<ReservationDTO> getAllReservations() {
        return reservationRepository.findAll().stream().map(res -> {
            // Otsime broneeringuga seotud laua
            Table table = tableRepository.findById(res.getTableId()).orElseThrow();
            return new ReservationDTO(res.getId(), table.getName(), res.getStartTime(), res.getEndTime());
        }).collect(Collectors.toList());
    }
}