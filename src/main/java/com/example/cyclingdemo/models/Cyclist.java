package com.example.cyclingdemo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Cyclist {
    private String id;
    private String fullName;
    private Integer competitorNumber;
    private Country country;
    private CyclingTeam cyclingTeam;
}
