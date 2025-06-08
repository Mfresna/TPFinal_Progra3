package TpFinal_Progra3.services.interfaces;

import TpFinal_Progra3.model.DTO.ObraDTO;
import TpFinal_Progra3.model.entities.Obra;
import java.util.List;
import java.util.Optional;

public interface ObraServiceInterface {
    ObraDTO crearObra(ObraDTO obra);
    ObraDTO obtenerObra(Long id);
    List<ObraDTO> listarObras();
    boolean eliminarObra(Long id);
}