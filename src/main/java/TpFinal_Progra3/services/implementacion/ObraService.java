package TpFinal_Progra3.services.implementacion;

import TpFinal_Progra3.exceptions.NotFoundException;
import TpFinal_Progra3.model.DTO.ObraDTO;
import TpFinal_Progra3.model.entities.EstudioArq;
import TpFinal_Progra3.model.entities.Obra;
import TpFinal_Progra3.model.mappers.implementacion.ObraMapper;
import TpFinal_Progra3.repositories.EstudioArqRepository;
import TpFinal_Progra3.repositories.ObraRepository;
import TpFinal_Progra3.services.OpenStreetMapService;
import TpFinal_Progra3.services.interfaces.ObraServiceInterface;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
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

        Obra obraGuardada = obraRepository.save(obraMapper.mapObra(dto,estudio));

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
    public boolean eliminarObra(Long id) throws NotFoundException {
        if (!obraRepository.existsById(id)) {
            throw new NotFoundException("Obra no encontrada.");
        }
        obraRepository.deleteById(id);
        return true;
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

}
