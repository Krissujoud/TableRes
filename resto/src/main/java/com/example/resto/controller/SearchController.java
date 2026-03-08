package com.example.resto.controller;

import com.example.resto.dto.SearchRequest;
import com.example.resto.dto.TableDTO;
import com.example.resto.model.Table;
import com.example.resto.repository.TableRepository;
import com.example.resto.service.RecommendationService;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SearchController {

    private final RecommendationService recommendationService;
    private final TableRepository tableRepository;

    public SearchController(RecommendationService recommendationService,
                            TableRepository tableRepository) {
        this.recommendationService = recommendationService;
        this.tableRepository = tableRepository;
    }

    @PostMapping("/search")
    public List<TableDTO> search(@RequestBody SearchRequest request) {

        List<Table> allTables = tableRepository.findAll();
        List<Table> recommended = recommendationService.recommend(request);

        List<TableDTO> result = new ArrayList<>();

        for (Table t : allTables) {

            TableDTO dto = new TableDTO();

            dto.id = t.getId();
            dto.x = t.getX();
            dto.y = t.getY();
            dto.seats = t.getSeats();

            dto.reserved = recommendationService.isReserved(t.getId());
            dto.recommended = recommended.contains(t);

            result.add(dto);
        }


        return result;
    }
}