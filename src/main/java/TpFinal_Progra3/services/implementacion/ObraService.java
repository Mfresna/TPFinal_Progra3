package TpFinal_Progra3.services.implementacion;

import TpFinal_Progra3.exceptions.IPLocationException;
import TpFinal_Progra3.exceptions.NotFoundException;
import TpFinal_Progra3.model.DTO.IPLocationDTO;
import TpFinal_Progra3.model.DTO.ObraDTO;
import TpFinal_Progra3.model.entities.EstudioArq;
import TpFinal_Progra3.model.entities.Obra;
import TpFinal_Progra3.model.enums.CategoriaObra;
import TpFinal_Progra3.model.enums.EstadoObra;
import TpFinal_Progra3.model.mappers.implementacion.ObraMapper;
import TpFinal_Progra3.repositories.EstudioArqRepository;
import TpFinal_Progra3.repositories.ObraRepository;
import TpFinal_Progra3.services.IPLocationService;
import TpFinal_Progra3.services.OpenStreetMapService;
import TpFinal_Progra3.services.OpenStreetMapService;
import TpFinal_Progra3.services.interfaces.ObraServiceInterface;
import TpFinal_Progra3.specifications.ObraSpecification;
import TpFinal_Progra3.model.DTO.filtros.ObraFiltroDTO;
import TpFinal_Progra3.utils.CoordenadasUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ObraService implements ObraServiceInterface {

    private final ObraRepository obraRepository;
    private final EstudioArqRepository estudioArqRepository;
    private final ObraMapper obraMapper;
    private final OpenStreetMapService openStreetMapService;
    private final IPLocationService ipLocationService;

    public ObraDTO crearObra(ObraDTO dto) {
        EstudioArq estudio = estudioArqRepository.findById(dto.getEstudioId())
                .orElseThrow(() -> new NotFoundException("Estudio no encontrado"));

        Obra obraGuardada = obraRepository.save(obraMapper.mapObra(dto, estudio));

        return obraMapper.mapDTO(obraGuardada);
    }

    public ObraDTO obtenerObra(Long id) {
        return obraRepository.findById(id)
                .map(obraMapper::mapDTO)
                .orElseThrow(() -> new NotFoundException("Obra no encontrada con ID: " + id));
    }

    public void eliminarObra(Long id) throws NotFoundException {
        if (!obraRepository.existsById(id)) {
            throw new NotFoundException("Obra no encontrada.");
        }
        obraRepository.deleteById(id);
    }

    public String obraEnMapa(int zoom, Long id)throws NotFoundException{
        Obra obra = obraRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Obra no encontrada con ID: " + id));

        return openStreetMapService.generarMapaConMarcador(obra.getLatitud(), obra.getLongitud(), zoom);
    }

    public List<ObraDTO> obrasPorTerritorio(String ciudad, String pais){
        Map<String, Double> coordenadas = openStreetMapService.areaDeCiudadPais(ciudad,pais);
        return obraRepository.findByLatitudBetweenAndLongitudBetween(
                coordenadas.get("latMin"),
                coordenadas.get("latMax"),
                coordenadas.get("lonMin"),
                coordenadas.get("lonMax"))
                .stream()
                .map(obraMapper::mapDTO)
                .toList();
    }

    public List<ObraDTO> obrasPorDistancia(HttpServletRequest request, Double distancia){

        IPLocationDTO ipLocationUsuario =
                ipLocationService.obtenerUbicacion(ipLocationService.obtenerIpCliente(request))
                        .orElseThrow(() -> new IPLocationException(HttpStatus.CONFLICT,"No se puede obtener la Localizacion por IP"));

        Map<String,Double> coordenadas = CoordenadasUtils.areaDeBusqueda(ipLocationUsuario, distancia);

        return obraRepository.findByLatitudBetweenAndLongitudBetween(
                        coordenadas.get("latMin"),
                        coordenadas.get("latMax"),
                        coordenadas.get("lonMin"),
                        coordenadas.get("lonMax"))
                .stream()
                .map(obraMapper::mapDTO)
                .toList();
    }

    public List<ObraDTO> filtrarObras(ObraFiltroDTO filtro) {

        // Verificar existencia del estudio
        if (filtro.getEstudioId() != null) {
            boolean existeEstudio = estudioArqRepository.existsById(filtro.getEstudioId());
            if (!existeEstudio) {
                throw new NotFoundException("El estudio de arquitectura con ID " + filtro.getEstudioId() + " no existe.");
            }
        }

        // Aplicar los filtros
        List<Obra> obrasFiltradas = obraRepository.findAll(ObraSpecification.filtrar(filtro));

        // Verificar si hay resultados
        if (obrasFiltradas.isEmpty()) {
            throw new NotFoundException("No se encontraron obras con los filtros especificados.");
        }

        // Mapear resultados
        return obrasFiltradas.stream()
                .map(obraMapper::mapDTO)
                .toList();
    }

    public ObraDTO modificarObra(Long id, ObraDTO obraDTO) {
        Obra obra = obraRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Obra no encontrada con ID: " + id));

        EstudioArq estudio = estudioArqRepository.findById(obraDTO.getEstudioId())
                .orElseThrow(() -> new NotFoundException("Estudio de arquitectura no encontrado con ID: " + obraDTO.getEstudioId()));

        obra.setNombre(obraDTO.getNombre());
        obra.setLatitud(obraDTO.getLatitud());
        obra.setLongitud(obraDTO.getLongitud());
        obra.setDescripcion(obraDTO.getDescripcion());
        obra.setAnioEstado(obraDTO.getAnioEstado());
        obra.setEstado(obraDTO.getEstado());
        obra.setCategoria(obraDTO.getCategoria());
        obra.setEstudio(estudio);

        Obra obraActualizada = obraRepository.save(obra);
        return obraMapper.mapDTO(obraActualizada);
    }

}
