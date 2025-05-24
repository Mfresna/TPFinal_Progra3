package TpFinal_Progra3.repositories;

import TpFinal_Progra3.entities.EstudioArquitectura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface EstudioArquitecturaRepository extends JpaRepository<EstudioArquitectura, Long> {

    //Buscar Estudio por ID
    Optional<EstudioArquitectura> findById(Long id);

    // Buscar Estudio por nombre (Ignorando mayúsculas/minúsculas)
    List<EstudioArquitectura> findByNombreContainingIgnoreCase(String nombre);
}


