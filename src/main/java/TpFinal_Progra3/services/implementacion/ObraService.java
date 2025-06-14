package TpFinal_Progra3.services.implementacion;

import TpFinal_Progra3.exceptions.NotFoundException;
import TpFinal_Progra3.model.DTO.ObraDTO;
import TpFinal_Progra3.model.entities.EstudioArq;
import TpFinal_Progra3.model.entities.Obra;
import TpFinal_Progra3.model.enums.CategoriaObra;
import TpFinal_Progra3.model.enums.EstadoObra;
import TpFinal_Progra3.model.mappers.implementacion.ObraMapper;
import TpFinal_Progra3.repositories.EstudioArqRepository;
import TpFinal_Progra3.repositories.ObraRepository;
import TpFinal_Progra3.services.OpenStreetMapService;
import TpFinal_Progra3.services.OpenStreetMapService;
import TpFinal_Progra3.services.interfaces.ObraServiceInterface;
import TpFinal_Progra3.specifications.ObraSpecification;
import TpFinal_Progra3.model.DTO.filtros.ObraFiltroDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
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


    @Override
    public ObraDTO crearObra(ObraDTO dto) {
        EstudioArq estudio = estudioArqRepository.findById(dto.getEstudioId())
                .orElseThrow(() -> new NotFoundException("Estudio no encontrado"));

        Obra obraGuardada = obraRepository.save(obraMapper.mapObra(dto, estudio));

        return obraMapper.mapDTO(obraGuardada);
    }

    @Override
    public ObraDTO obtenerObra(Long id) {
        return obraRepository.findById(id)
                .map(obraMapper::mapDTO)
                .orElseThrow(() -> new NotFoundException("Obra no encontrada con ID: " + id));
    }

    @Override
    public List<ObraDTO> listarObras() {
        return obraRepository.findAll()
                .stream()
                .map(obraMapper::mapDTO)
                .toList();
    }

    @Override
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


    // FILTRADO DE OBRAS
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
        // 1. Buscar la obra existente
        Obra obra = obraRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Obra no encontrada con ID: " + id));

        // 2. Verificar si existe el estudio de arquitectura
        EstudioArq estudio = estudioArqRepository.findById(obraDTO.getEstudioId())
                .orElseThrow(() -> new NotFoundException("Estudio de arquitectura no encontrado con ID: " + obraDTO.getEstudioId()));

        // 3. Actualizar campos de la obra
        obra.setNombre(obraDTO.getNombre());
        obra.setLatitud(obraDTO.getLatitud());
        obra.setLongitud(obraDTO.getLongitud());
        obra.setDescripcion(obraDTO.getDescripcion());
        obra.setAnioEstado(obraDTO.getAnioEstado());
        obra.setEstado(obraDTO.getEstado());
        obra.setCategoria(obraDTO.getCategoria());
        obra.setEstudio(estudio);

        // 4. Guardar y devolver la obra actualizada
        Obra obraActualizada = obraRepository.save(obra);
        return obraMapper.mapDTO(obraActualizada);
    }

}
