package pruebaFinal.JorgePerez_AstridMurgas_B_G1.Models.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class PeliculasDTO {
    private Long id;

    @NotBlank(message = "El título no puede ser nulo")
    private String titulo;

    @Size(max = 120, message = "El nombre del director no puede superar los 120 caracteres")
    private String director;

    @Size(max = 60, message = "El género no puede superar los 60 caracters")
    private String genero;

    private Long anoEstreno;

    private Double duracionMin;

}
