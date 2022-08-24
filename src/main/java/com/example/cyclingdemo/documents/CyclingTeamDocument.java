package com.example.cyclingdemo.documents;

import com.example.cyclingdemo.models.Country;
import com.example.cyclingdemo.models.Cyclist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Document(collection = "CyclingTeams")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CyclingTeamDocument {
    @Id
    private String cyclingTeamId;
    @NotNull(message = "El nombre no puede ser nulo")
    private String name;
    @NotNull(message = "El codigo del equipo no puede ser nulo")
    @Size(max = 3)
    @Indexed(unique = true)
    private String teamCode;
    private Country country;
    @Size.List(@Size(max = 8))
    private List<Cyclist> cyclists;

}
