package TpFinal_Progra3.model.mappers.implementacion;

import TpFinal_Progra3.model.DTO.ObraDTO;
import TpFinal_Progra3.model.entities.EstudioArq;
import TpFinal_Progra3.model.entities.Obra;
import org.springframework.stereotype.Component;

@Component
public class ObraMapper {

    // Convertir de DTO a entidad
    public Obra mapObra(ObraDTO obraDto, EstudioArq estudio) {
        return Obra.builder()
                .nombre(obraDto.getNombre())
                .latitud(obraDto.getLatitud())
                .longitud(obraDto.getLongitud())
                .descripcion(obraDto.getDescripcion())
                .anioEstado(obraDto.getAnioEstado())
                .estado(obraDto.getEstado())
                .categoria(obraDto.getCategoria())
                .estudio(estudio)
                .build();
    }

    // Convertir de entidad a DTO (si lo necesitás para devolver en un GET)
    public ObraDTO mapDTO(Obra obra) {
        return ObraDTO.builder()
                .nombre(obra.getNombre())
                .latitud(obra.getLatitud())
                .longitud(obra.getLongitud())
                .descripcion(obra.getDescripcion())
                .anioEstado(obra.getAnioEstado())
                .estado(obra.getEstado())
                .categoria(obra.getCategoria())
                .estudioId(obra.getEstudio().getId())
                .build();
    }
}