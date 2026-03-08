package com.example.resto.dto;

import com.example.resto.model.Preference;
import com.example.resto.model.Zone;

import java.time.LocalDateTime;
import java.util.List;

public class SearchRequest {

    public LocalDateTime time;

    public int peopleCount;

    public Zone zone;

    public List<Preference> preferences;

}