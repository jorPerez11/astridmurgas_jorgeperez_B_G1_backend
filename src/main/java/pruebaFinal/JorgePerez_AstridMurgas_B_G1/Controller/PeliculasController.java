package pruebaFinal.JorgePerez_AstridMurgas_B_G1.Controller;

import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pruebaFinal.JorgePerez_AstridMurgas_B_G1.Exceptions.ExistingElementException;
import pruebaFinal.JorgePerez_AstridMurgas_B_G1.Models.DTO.PeliculasDTO;
import pruebaFinal.JorgePerez_AstridMurgas_B_G1.Service.PeliculasService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api")
@CrossOrigin
public class PeliculasController {

    @Autowired
    PeliculasService service;

    //GET
    @GetMapping("/peliculas/getPeliculas")
    public ResponseEntity<List<PeliculasDTO>> getPeliculas(){
        return new ResponseEntity<>(service.findAllPeliculas(), HttpStatus.OK);
    }

    //POST
    @PostMapping("/peliculas/postPelicula")
    public ResponseEntity<?> postPeliculas(@RequestBody @Valid PeliculasDTO dto){
        try{
            PeliculasDTO newPelicula = service.createPelicula(dto);
            return new ResponseEntity<>(newPelicula, HttpStatus.OK);
        }catch(ExistingElementException e){
            Map<String, String> errores = new HashMap<>();
            return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
        }catch(Exception e) {
            return new ResponseEntity<>("Ocurrio un error inesperado.", HttpStatus.BAD_REQUEST);
        }
    }

    //UPDATE (PATCH)
    @PatchMapping("/peliculas/updatePelicula/{id}")
    public ResponseEntity<?> updatePelicula(@RequestBody @Valid PeliculasDTO dto, @PathVariable Long id){
        try{
            PeliculasDTO updatedPelicula = service.updatePelicula(dto, id);
            return new ResponseEntity<>(updatedPelicula, HttpStatus.OK);
        }catch(ExistingElementException e){
            Map<String, String> errores = new HashMap<>();
            return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
        }catch(Exception e) {
            Map<String, String> errores = new HashMap<>();
            errores.put("Ocurrio un error inesperado.", e.getMessage());
            return new ResponseEntity<>(errores , HttpStatus.BAD_REQUEST);
        }
    }

    //DELETE
    @DeleteMapping("/peliculas/deletePelicula/{id}")
    public ResponseEntity<?> deletePelicula(@PathVariable Long id){
        try{
            service.deletePelicula(id);
            return new ResponseEntity<>("Se ha eiminado la pelicula con id: " + id, HttpStatus.OK);
        }catch(ExistingElementException e){
            Map<String, String> errores = new HashMap<>();
            return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
        }catch(Exception e) {
            return new ResponseEntity<>("Ocurrio un error inesperado.", HttpStatus.BAD_REQUEST);
        }
    }
}
