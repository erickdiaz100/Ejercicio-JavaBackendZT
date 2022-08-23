package com.example.cyclingdemo.documents;

import com.example.cyclingdemo.models.Country;
import com.example.cyclingdemo.models.CyclingTeam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Cyclits")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CyclistDocument {
    @Id
    private String cyclistId;
    private String fullName;
    private String competitorNumber;
    private Country country;
    private CyclingTeam cyclingTeam;

}
