package TpFinal_Progra3.model.mappers.implementacion;

import TpFinal_Progra3.model.DTO.ImagenDTO;
import TpFinal_Progra3.model.entities.Imagen;
import org.springframework.stereotype.Component;

@Component
public class ImagenMapper {

    // Convertir de DTO a entidad
    public Imagen mapImagen(ImagenDTO dto) {
        return Imagen.builder()
                .nombre(dto.getNombre())
                .tipo(dto.getTipo())
                .url(dto.getUrl())
                .build();
    }

    // Convertir de entidad a DTO (si lo necesitás para devolver en un GET)
    public ImagenDTO mapDTO(Imagen imagen) {
        return ImagenDTO.builder()
                .id(imagen.getId())
                .nombre(imagen.getNombre())
                .tipo(imagen.getTipo())
                .url(imagen.getUrl())
                .build();
    }
}
