package TpFinal_Progra3.services.interfaces;

import TpFinal_Progra3.model.DTO.ImagenDTO;
import java.util.List;

public interface ImagenServiceInterface {
    ImagenDTO crearImagen(ImagenDTO imagen);
    ImagenDTO obtenerImagen(Long id);
    List<ImagenDTO> listarImagenes();
    boolean eliminarImagen(Long id);
}
