package TpFinal_Progra3.services.interfaces;

import TpFinal_Progra3.exceptions.NotFoundException;
import TpFinal_Progra3.model.DTO.obras.ObraDTO;
import TpFinal_Progra3.model.DTO.obras.ObraResponseDTO;
import TpFinal_Progra3.model.DTO.filtros.ObraFiltroDTO;
import TpFinal_Progra3.model.entities.Imagen;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface ObraServiceInterface {
    public ObraResponseDTO crearObra(ObraDTO dto);
    public ObraResponseDTO obtenerObra(Long id);
    public void eliminarObra(Long id) throws NotFoundException;
    public String obraEnMapa(int zoom, Long id) throws NotFoundException;
    public List<ObraResponseDTO> obrasPorTerritorio(String ciudad, String pais);
    public List<ObraResponseDTO> obrasPorDistancia(HttpServletRequest request, Double distancia);
    public List<ObraResponseDTO> filtrarObras(ObraFiltroDTO filtro);
    public ObraResponseDTO modificarObra(Long id, ObraDTO obraDTO);
    public List<Imagen> listarImagenes(Long id);
    public void eliminarImagenes(Long id, List<String> urlImagenes);
    public ObraResponseDTO agregarImagenes(Long id, List<String> urlImagenes);
}