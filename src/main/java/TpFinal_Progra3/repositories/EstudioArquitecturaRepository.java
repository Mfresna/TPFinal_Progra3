package TpFinal_Progra3.repositories;

import TpFinal_Progra3.model.entities.EstudioArquitectura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface EstudioArquitecturaRepository extends JpaRepository<EstudioArquitectura, Long> {

    Optional<EstudioArquitectura> findByNombre(String nombre);

    List<EstudioArquitectura> findByNombreContainingIgnoreCase(String nombre);

    //Buscar por ID
    Optional<EstudioArquitectura> findById(Long id);
}
