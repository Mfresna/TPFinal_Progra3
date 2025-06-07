package TpFinal_Progra3.model.mappers.impl;

import TpFinal_Progra3.model.DTO.ObraDTO;
import TpFinal_Progra3.model.entities.EstudioArq;
import TpFinal_Progra3.model.entities.Obra;
import org.springframework.stereotype.Component;

@Component
public class ObraMapper {

    // Convertir de DTO a entidad
    public Obra crearObra(ObraDTO dto, EstudioArq estudio) {
        return Obra.builder()
                .nombre(dto.getNombre())
                .latitud(dto.getLatitud())
                .longitud(dto.getLongitud())
                .descripcion(dto.getDescripcion())
                .anioEstado(dto.getAnioEstado())
                .estado(dto.getEstado())
                .categoria(dto.getCategoria())
                .estudio(estudio)
                .build();
    }

    // Convertir de entidad a DTO (si lo necesitás para devolver en un GET)
    public ObraDTO crearDTO(Obra obra) {
        ObraDTO dto = new ObraDTO();
        dto.setNombre(obra.getNombre());
        dto.setLatitud(obra.getLatitud());
        dto.setLongitud(obra.getLongitud());
        dto.setDescripcion(obra.getDescripcion());
        dto.setAnioEstado(obra.getAnioEstado());
        dto.setEstado(obra.getEstado());
        dto.setCategoria(obra.getCategoria());
        dto.setEstudioId(obra.getEstudio().getId());
        return dto;
    }
}
