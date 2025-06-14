package TpFinal_Progra3.services.interfaces;

import TpFinal_Progra3.model.DTO.EstudioArqDTO;
import TpFinal_Progra3.model.DTO.filtros.EstudioArqFiltroDTO;

import java.util.List;

public interface EstudioArqServiceInterface {
    EstudioArqDTO crearEstudio(EstudioArqDTO estudio);
    EstudioArqDTO obtenerEstudio(Long id);
    List<EstudioArqDTO> filtrarEstudios(EstudioArqFiltroDTO filtro);
    //EstudioArqDTO agregarArquitectoAEstudio(Long estudioId, Long arquitectoId);
    EstudioArqDTO modificarEstudio(Long id, EstudioArqDTO dto);
}