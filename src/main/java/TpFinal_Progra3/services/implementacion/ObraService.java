package TpFinal_Progra3.services.implementacion;

import TpFinal_Progra3.exceptions.IPLocationException;
import TpFinal_Progra3.exceptions.NotFoundException;
import TpFinal_Progra3.model.DTO.IPLocationDTO;
import TpFinal_Progra3.model.DTO.ObraDTO;
import TpFinal_Progra3.model.entities.EstudioArq;
import TpFinal_Progra3.model.entities.Imagen;
import TpFinal_Progra3.model.entities.Obra;
import TpFinal_Progra3.model.mappers.ObraMapper;
import TpFinal_Progra3.repositories.EstudioArqRepository;
import TpFinal_Progra3.repositories.ObraRepository;
import TpFinal_Progra3.services.CloudinaryService;
import TpFinal_Progra3.services.IPLocationService;
import TpFinal_Progra3.services.OpenStreetMapService;
import TpFinal_Progra3.services.interfaces.ObraServiceInterface;
import TpFinal_Progra3.specifications.ObraSpecification;
import TpFinal_Progra3.model.DTO.filtros.ObraFiltroDTO;
import TpFinal_Progra3.utils.CoordenadasUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ObraService implements ObraServiceInterface{

    private final ObraRepository obraRepository;
    private final EstudioArqRepository estudioArqRepository;
    private final ObraMapper obraMapper;
    private final OpenStreetMapService openStreetMapService;
    private final IPLocationService ipLocationService;
    private final ImagenService imagenService;

    public ObraDTO crearObra(ObraDTO dto) {
        EstudioArq estudio = estudioArqRepository.findById(dto.getEstudioId())
                .orElseThrow(() -> new NotFoundException("Estudio no encontrado"));

        List<Imagen> imagenesObra = dto.getUrlsImagenes().stream()
                .map(imagenService::obtenerImagen)
                .toList();

        Obra obraGuardada = obraRepository.save(obraMapper.mapObra(dto,estudio,imagenesObra));

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

        // Aplicar los filtro y Mapear resultados
        return obraRepository.findAll(ObraSpecification.filtrar(filtro))
                .stream()
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

    public void eliminarImagenes(Long id, List<String> urlImagenes){
        Obra obra = obraRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Obra no encontrada con ID: " + id));

        obra.getImagenes().removeIf(imagen -> urlImagenes.contains(imagen.getUrl()));

        obraRepository.save(obra);
    }
}
