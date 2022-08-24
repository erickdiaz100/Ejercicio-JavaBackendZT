package com.example.cyclingdemo.documents;

import com.example.cyclingdemo.models.CyclingTeam;
import com.example.cyclingdemo.models.Cyclist;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Document(collection = "Countries")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CountryDocument {
    @Id
    private String countryId;
    @NotNull(message = "El nombre no puede ser nulo")

    private String name;
    @NotNull(message = "El codigo unico no puede ser nulo")
    @Size(max = 3)
    @Indexed(unique = true)
    private String code;
    private List<CyclingTeam> cyclingTeams;
    private List<Cyclist> cyclists;
}
