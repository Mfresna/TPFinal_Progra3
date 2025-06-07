package TpFinal_Progra3.services.interfaces;

import TpFinal_Progra3.model.entities.Obra;
import java.util.List;
import java.util.Optional;

public interface ObraServiceInterface {
    Obra crearObra(Obra obra);
    Optional<Obra> obtenerObra(Long id);
    List<Obra> listarObras();
    boolean eliminarObra(Long id);
}