package com.example.cyclingdemo.documents;

import com.example.cyclingdemo.models.Country;
import com.example.cyclingdemo.models.CyclingTeam;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.convert.DataSizeUnit;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Document(collection = "Cyclits")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CyclistDocument {
    @Id
    private String cyclistId;
    @NotNull(message = "El nombre completo no puede ser nulo")
    private String fullName;
    @NotNull(message = "El numero de competidor no puede ser nulo")
    @Indexed(unique = true)
@Size(max = 3)
    private Integer competitorNumber;
    private Country country;
    private CyclingTeam cyclingTeam;

}
