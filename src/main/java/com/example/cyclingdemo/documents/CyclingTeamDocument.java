package com.example.cyclingdemo.documents;

import com.example.cyclingdemo.models.Country;
import com.example.cyclingdemo.models.Cyclist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "CyclingTeams")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CyclingTeamDocument {
    @Id
    private String cyclingTeamId;
    private String name;
    private String teamCode;
    private Country country;
    private List<Cyclist> cyclists;

}
