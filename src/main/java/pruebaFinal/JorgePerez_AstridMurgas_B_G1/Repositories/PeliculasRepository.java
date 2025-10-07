package pruebaFinal.JorgePerez_AstridMurgas_B_G1.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pruebaFinal.JorgePerez_AstridMurgas_B_G1.Entity.PeliculasEntity;

public interface PeliculasRepository extends JpaRepository<PeliculasEntity, Long> {
    boolean existsByTitulo(String string);
}
