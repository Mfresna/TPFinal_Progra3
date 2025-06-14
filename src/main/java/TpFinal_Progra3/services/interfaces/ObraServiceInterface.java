package TpFinal_Progra3.services.interfaces;

import TpFinal_Progra3.exceptions.NotFoundException;
import TpFinal_Progra3.model.DTO.ObraDTO;
import TpFinal_Progra3.model.DTO.filtros.ObraFiltroDTO;
import TpFinal_Progra3.model.entities.Obra;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Optional;

public interface ObraServiceInterface {
    ObraDTO crearObra(ObraDTO obra);
    ObraDTO obtenerObra(Long id);
    ObraDTO modificarObra(Long id, ObraDTO obraDTO);
    void eliminarObra(Long id);
    String obraEnMapa(int zoom, Long id)throws NotFoundException;
    List<ObraDTO> obrasPorTerritorio(String ciudad, String pais);
    List<ObraDTO> filtrarObras(ObraFiltroDTO filtro);
    List<ObraDTO> obrasPorDistancia(HttpServletRequest request, Double distancia);
}