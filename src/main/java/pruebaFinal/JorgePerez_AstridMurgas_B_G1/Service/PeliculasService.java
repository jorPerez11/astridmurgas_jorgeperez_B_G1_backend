package pruebaFinal.JorgePerez_AstridMurgas_B_G1.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pruebaFinal.JorgePerez_AstridMurgas_B_G1.Entity.PeliculasEntity;
import pruebaFinal.JorgePerez_AstridMurgas_B_G1.Exceptions.ExistingElementException;
import pruebaFinal.JorgePerez_AstridMurgas_B_G1.Models.DTO.PeliculasDTO;
import pruebaFinal.JorgePerez_AstridMurgas_B_G1.Repositories.PeliculasRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PeliculasService {

    @Autowired
    PeliculasRepository repo;

    //GET
    public List<PeliculasDTO> findAllPeliculas(){
        List<PeliculasEntity> peliculas = repo.findAll();
        return peliculas.stream().map(this::ConvertToPeliculasDTO).collect(Collectors.toList());
    }

    //POST
    public PeliculasDTO createPelicula(PeliculasDTO dto){
        //RESTRICCION UNIQUE (PARA TITULO)
        if(repo.existsByTitulo(dto.getTitulo())){
            throw new ExistingElementException("El titulo de la película ya esta registrada");
        }

        PeliculasEntity entity = new PeliculasEntity();

        entity.setId(dto.getId());
        entity.setTitulo(dto.getTitulo());
        entity.setDirector(dto.getDirector());
        entity.setGenero(dto.getGenero());
        entity.setAnoEstreno(dto.getAnoEstreno());
        entity.setDuracionMin(dto.getDuracionMin());
        entity.setFechaCreacion(dto.getFechaCreacion());

        return ConvertToPeliculasDTO(repo.save(entity));
    }

    //UPDATE
    public PeliculasDTO updatePelicula(PeliculasDTO dto, Long id){
        //VERIFICAR SI EXISTE REGISTRO
        PeliculasEntity entity = repo.findById(id).orElseThrow(()-> new IllegalArgumentException("La película no esta registrada en la base de datos"));

        entity.setTitulo(dto.getTitulo());
        entity.setDirector(dto.getDirector());
        entity.setGenero(dto.getGenero());
        entity.setAnoEstreno(dto.getAnoEstreno());
        entity.setDuracionMin(dto.getDuracionMin());
        entity.setFechaCreacion(dto.getFechaCreacion());

        return ConvertToPeliculasDTO(repo.save(entity));
    }


    //DELETE
    public void deletePelicula(Long id){
        //VERIFICAR SI EXISTE REGISTRO
        PeliculasEntity entity = repo.findById(id).orElseThrow(()-> new IllegalArgumentException("La película no esta registrada en la base de datos"));

        repo.delete(entity);
    }

    //TO DTO
    private PeliculasDTO ConvertToPeliculasDTO(PeliculasEntity entity) {
        PeliculasDTO dto = new PeliculasDTO();

        dto.setTitulo(entity.getTitulo());
        dto.setDirector(entity.getDirector());
        dto.setGenero(entity.getGenero());
        dto.setAnoEstreno(entity.getAnoEstreno());
        dto.setDuracionMin(entity.getDuracionMin());
        dto.setFechaCreacion(entity.getFechaCreacion());

        return dto;
    }
}
