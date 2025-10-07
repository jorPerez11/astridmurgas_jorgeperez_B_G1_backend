package pruebaFinal.JorgePerez_AstridMurgas_B_G1.Entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "PELICULAS")

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PeliculasEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PELICULAS")
    @SequenceGenerator(name = "SEQ_PELICULAS", sequenceName = "SEQ_PELICULAS", allocationSize = 1)
    @Column(name = "ID_PELICULA")
    private Long id;

    @Column(name = "TITULO", unique = true)
    private String titulo;

    @Column(name = "DIRECTOR")
    private String director;

    @Column(name = "GENERO")
    private String genero;

    @Column(name = "ANO_ESTRENO")
    private Long anoEstreno;

    @Column(name = "DURACION_MIN")
    private Double duracionMin;

    @Column(name = "FECHA_CREACION")
    private LocalDateTime fechaCreacion;

}
