package com.example.resto.config;

import com.example.resto.model.*;
import com.example.resto.repository.*;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

/**
 * loeme lauad sisse
 */
@Component
public class DataLoader {

    private final TableRepository tableRepo;
    private final ReservationRepository reservationRepo;

    public DataLoader(TableRepository tableRepo, ReservationRepository reservationRepo) {
        this.tableRepo = tableRepo;
        this.reservationRepo = reservationRepo;
    }

    @PostConstruct
    public void loadData() {

        // WINDOW ZONE
        // loome suvalised lauad meie restorani
        tableRepo.saveAll(List.of(
                new Table("T1",4,Zone.WINDOW,80,60),
                new Table("T2",4,Zone.WINDOW,200,60),
                new Table("T3",4,Zone.WINDOW,320,60),
                new Table("T4",4,Zone.WINDOW,440,60)
        ));

        // MAIN HALL
        tableRepo.saveAll(List.of(
                new Table("T5",2,Zone.MAIN_HALL,80,160),
                new Table("T6",2,Zone.MAIN_HALL,200,160),
                new Table("T7",4,Zone.MAIN_HALL,320,160),
                new Table("T8",4,Zone.MAIN_HALL,440,160),

                new Table("T9",2,Zone.MAIN_HALL,80,260),
                new Table("T10",2,Zone.MAIN_HALL,200,260),
                new Table("T11",6,Zone.MAIN_HALL,320,260),
                new Table("T12",6,Zone.MAIN_HALL,440,260)
        ));

        // TERRACE
        tableRepo.saveAll(List.of(
                new Table("T13",4,Zone.TERRACE,80,360),
                new Table("T14",4,Zone.TERRACE,200,360),
                new Table("T15",4,Zone.TERRACE,320,360),
                new Table("T16",4,Zone.TERRACE,440,360)
        ));

        // KIDS AREA
        tableRepo.saveAll(List.of(
                new Table("T17",4,Zone.KIDS_PLAYGROUND,80,460),
                new Table("T18",4,Zone.KIDS_PLAYGROUND,200,460),
                new Table("T19",4,Zone.KIDS_PLAYGROUND,320,460),
                new Table("T20",4,Zone.KIDS_PLAYGROUND,440,460)
        ));

        // suvalised
        Random random = new Random();

        for (Table table : tableRepo.findAll()) {

            if (random.nextDouble() < 0.35) {

                reservationRepo.save(
                        new Reservation(
                                table.getId(),
                                LocalDateTime.now().plusHours(1),
                                LocalDateTime.now().plusHours(3)
                        )
                );
            }
        }
    }
}