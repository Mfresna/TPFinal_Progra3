package TpFinal_Progra3.repositories;

import TpFinal_Progra3.entities.Obra;
import TpFinal_Progra3.enums.CategoriaObra;
import TpFinal_Progra3.enums.EstadoObra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ObraRepository extends JpaRepository<Obra, Long> {

    // Buscar por ID
    Optional<Obra> findById(Long id);

    // Buscar obras por categoría
    List<Obra> findByCategoria(CategoriaObra categoria);

    // Buscar obras por estado
    List<Obra> findByEstado(EstadoObra estado);

    // Buscar obras de un estudio específico
    List<Obra> findByAnioEstado(Integer anio);

    // Buscar obras por nombre (ignorando mayúsculas y espacios)
    @Query("SELECT o FROM Obra o WHERE LOWER(TRIM(o.nombre)) = LOWER(TRIM(:nombre))")
    List<Obra> findByNombre(@Param("nombre") String nombre);

    /*// Buscar obras creadas en un año específico
    @Query("SELECT o FROM Obra o WHERE o.anioEstado = :anio")
    List<Obra> findByAnioEstado(@Param("anio") Integer anio);*/

    // Buscar por EstudioArquitectura
    @Query("SELECT o FROM Obra o WHERE o.estudio.id = :estudioId")
    List<Obra> findByEstudioId(@Param("estudioId") Long estudioId);

}
