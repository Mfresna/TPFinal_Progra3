/*package TpFinal_Progra3.repositories;

import TpFinal_Progra3.entities.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImagenRepository extends JpaRepository<Imagen, Long> {

    // Buscar imagen por nombre
    Optional<Imagen> findByNombre(String nombre);

    // Buscar imagen por URL (única en la BD)
    Optional<Imagen> findByUrl(String url);
}*/