package TpFinal_Progra3.services.interfaces;

import TpFinal_Progra3.model.DTO.ImagenDTO;
import TpFinal_Progra3.model.entities.Imagen;

public interface ImagenServiceInterface {
    Imagen crearImagen(ImagenDTO imagen);
    ImagenDTO obtenerImagen(Long id);
    Imagen obtenerImagen(String url);
    void eliminarImagen(Long id);
}
