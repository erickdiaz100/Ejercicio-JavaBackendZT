package com.example.cyclingdemo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Country {
    private String countryId;
    private String name;
    private String code;
    private List<CyclingTeam> cyclingTeams;
    private List<Cyclist> cyclists;
}
