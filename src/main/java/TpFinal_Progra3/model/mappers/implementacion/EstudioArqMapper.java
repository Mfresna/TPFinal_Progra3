package TpFinal_Progra3.model.mappers.implementacion;

import TpFinal_Progra3.model.DTO.EstudioArqDTO;
import TpFinal_Progra3.model.entities.EstudioArq;
import TpFinal_Progra3.model.entities.Obra;
import TpFinal_Progra3.model.entities.Usuario;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class EstudioArqMapper {

    // Convertir de DTO a entidad
    public EstudioArq mapEstudio(EstudioArqDTO dto) {
        return EstudioArq.builder()
                .nombre(dto.getNombre())
                .build();
    }

    // Convertir de entidad a DTO
    public EstudioArqDTO mapDTO(EstudioArq estudio) {
        return EstudioArqDTO.builder()
                .nombre(estudio.getNombre())
                .obrasIds(estudio.getObras() != null ? estudio.getObras().stream().map(Obra::getId).collect(Collectors.toList()) : new ArrayList<>())
                .arquitectosIds(estudio.getArquitectos() != null ? estudio.getArquitectos().stream().map(Usuario::getId).collect(Collectors.toList()) : new ArrayList<>())
                .imagenUrl(estudio.getImagen() != null ? estudio.getImagen().getUrl() : null)
                .build();
    }
}
