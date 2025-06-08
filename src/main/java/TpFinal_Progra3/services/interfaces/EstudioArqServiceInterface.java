package TpFinal_Progra3.services.interfaces;

import TpFinal_Progra3.model.DTO.EstudioArqDTO;
import java.util.List;

public interface EstudioArqServiceInterface {
    EstudioArqDTO crearEstudio(EstudioArqDTO estudio);
    EstudioArqDTO obtenerEstudio(Long id);
    List<EstudioArqDTO> listarEstudios();
    boolean eliminarEstudio(Long id);
}