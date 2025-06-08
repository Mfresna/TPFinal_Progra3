package TpFinal_Progra3.services.implementacion;

import TpFinal_Progra3.exceptions.NotFoundException;
import TpFinal_Progra3.model.DTO.ImagenDTO;
import TpFinal_Progra3.model.entities.Imagen;
import TpFinal_Progra3.model.mappers.implementacion.ImagenMapper;
import TpFinal_Progra3.repositories.ImagenRepository;
import TpFinal_Progra3.services.interfaces.ImagenServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImagenService implements ImagenServiceInterface {

    private final ImagenRepository imagenRepository;
    private final ImagenMapper imagenMapper;

    @Override
    public ImagenDTO crearImagen(ImagenDTO dto) {
        Imagen imagenGuardada = imagenRepository.save(imagenMapper.mapImagen(dto));
        return imagenMapper.mapDTO(imagenGuardada);
    }

    @Override
    public ImagenDTO obtenerImagen(Long id) {
        return imagenRepository.findById(id)
                .map(imagenMapper::mapDTO)
                .orElseThrow(() -> new NotFoundException("Imagen no encontrada con ID: " + id));
    }

    @Override
    public List<ImagenDTO> listarImagenes() {
        return imagenRepository.findAll()
                .stream()
                .map(imagenMapper::mapDTO)
                .toList();
    }

    @Override
    public boolean eliminarImagen(Long id) {
        if (!imagenRepository.existsById(id)) {
            throw new NotFoundException("Imagen no encontrada.");
        }
        imagenRepository.deleteById(id);
        return true;
    }
}
