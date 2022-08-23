package com.example.cyclingdemo.documents;

import com.example.cyclingdemo.models.CyclingTeam;
import com.example.cyclingdemo.models.Cyclist;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Countries")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CountryDocument {
    @Id
    private String countryId;
    private String name;
    private String code;
    private List<CyclingTeam> cyclingTeams;
    private List<Cyclist> cyclists;
}
