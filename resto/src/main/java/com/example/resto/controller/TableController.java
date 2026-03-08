package com.example.resto.controller;

import com.example.resto.model.Table;
import com.example.resto.service.TableService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tables")
public class TableController {

    private final TableService tableService;

    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    @GetMapping
    public List<Table> getAllTables() {
        return tableService.getAllTables();
    }

    @GetMapping("/recommend")
    public List<Table> recommend(
            @RequestParam int guests,
            @RequestParam String zone
    ) {
        return tableService.recommendTables(guests, zone);
    }
}