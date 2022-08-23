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
public class CyclingTeam {
    private String id;
    private String name;
    private String teamCode;
    private Country country;
    private List<Cyclist> cyclists;
}
