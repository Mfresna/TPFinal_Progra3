package TpFinal_Progra3.services.implementacion;

import TpFinal_Progra3.exceptions.NotFoundException;
import TpFinal_Progra3.model.DTO.EstudioArqBasicoDTO;
import TpFinal_Progra3.model.DTO.EstudioArqDTO;
import TpFinal_Progra3.model.DTO.filtros.EstudioArqFiltroDTO;
import TpFinal_Progra3.model.entities.EstudioArq;
import TpFinal_Progra3.model.entities.Imagen;
import TpFinal_Progra3.model.mappers.EstudioArqMapper;
import TpFinal_Progra3.repositories.EstudioArqRepository;
import TpFinal_Progra3.services.interfaces.EstudioArqServiceInterface;
import TpFinal_Progra3.specifications.EstudioArqSpecification;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstudioArqService implements EstudioArqServiceInterface {

    private final EstudioArqRepository estudioArqRepository;
    private final EstudioArqMapper estudioArqMapper;
    private final ImagenService imagenService;

    public EstudioArqDTO crearEstudio(EstudioArqBasicoDTO dto){
        Imagen img = imagenService.obtenerImagen(dto.getImagenUrl());
        return estudioArqMapper.mapDTO(estudioArqRepository.save(estudioArqMapper.mapEstudio(dto, img)));
    }

    public EstudioArqDTO obtenerEstudio(Long id) {
        return estudioArqRepository.findById(id)
                .map(estudioArqMapper::mapDTO)
                .orElseThrow(() -> new NotFoundException("Estudio no encontrado con ID: " + id));
    }

    public List<EstudioArqDTO> filtrarEstudios(EstudioArqFiltroDTO filtro) {
        List<EstudioArq> estudiosFiltrados = estudioArqRepository.findAll(EstudioArqSpecification.filtrar(filtro));

        if (estudiosFiltrados.isEmpty()) {
            throw new NotFoundException("No se encontraron estudios con los filtros especificados.");
        }

        return estudiosFiltrados.stream()
                .map(estudioArqMapper::mapDTO)
                .toList();
    }

//ESPERAR A TENER USUARIOS Y ROLES PARA ESTA FUNCIONALIDAD
//    @Transactional
//    public EstudioArqDTO agregarArquitectoAEstudio(Long estudioId, Long arquitectoId) {
//        EstudioArq estudio = estudioArqRepository.findById(estudioId)
//                .orElseThrow(() -> new NotFoundException("Estudio no encontrado con ID: " + estudioId));
//
//        Usuario arquitecto = usuarioRepository.findById(arquitectoId)
//                .orElseThrow(() -> new NotFoundException("Usuario no encontrado con ID: " + arquitectoId));
//
//        // Validar que el usuario esté activo
//        if (!arquitecto.isActivo()) {
//            throw new BadRequestException("El usuario no está activo."); // ver si puedo usar Coordenada exception pero cambiarle el nombre a Solicitud invalida exception
//        }
//
//        // Validar que tenga el rol de ARQUITECTO
//        boolean esArquitecto = arquitecto.getCredencial().getRoles().stream()
//                .anyMatch(rol -> rol.getRol().name().equalsIgnoreCase("ARQUITECTO"));
//
//        if (!esArquitecto) {
//            throw new BadRequestException("El usuario no tiene el rol de ARQUITECTO.");
//        }
//
//        // Agregar el arquitecto si no está ya presente
//        if (!estudio.getArquitectos().contains(arquitecto)) {
//            estudio.getArquitectos().add(arquitecto);
//            estudioArqRepository.save(estudio);
//        }
//
//        return estudioArqMapper.mapDTO(estudio);
//    }

    @Transactional
    public EstudioArqDTO modificarEstudio(Long id, EstudioArqDTO dto) {
        EstudioArq estudio = estudioArqRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Estudio no encontrado con ID: " + id));

        // Actualizar campos propios
        estudio.setNombre(dto.getNombre());

        // Actualizar imagen si corresponde
        if (dto.getImagenUrl() != null && !dto.getImagenUrl().isBlank()) {
            Imagen nuevaImagen = Imagen.builder().url(dto.getImagenUrl()).build();
            estudio.setImagen(nuevaImagen);
        }

        EstudioArq actualizado = estudioArqRepository.save(estudio);
        return estudioArqMapper.mapDTO(actualizado);
    }





}
