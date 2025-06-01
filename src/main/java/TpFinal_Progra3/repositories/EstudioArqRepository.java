package TpFinal_Progra3.repositories;

import TpFinal_Progra3.model.entities.EstudioArq;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface EstudioArqRepository extends JpaRepository<EstudioArq, Long> {

    //Es una lista pq el campo nombre no es unique entonces puede ser que recibamos dos objetos
    List<EstudioArq> findByNombreIgnoreCase(String nombre);

    List<EstudioArq> findByNombreContainingIgnoreCase(String nombre);

    //Buscar por ID
    Optional<EstudioArq> findById(Long id);
}
