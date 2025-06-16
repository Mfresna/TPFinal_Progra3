package TpFinal_Progra3.model.DTO.obras;

import TpFinal_Progra3.model.enums.CategoriaObra;
import TpFinal_Progra3.model.enums.EstadoObra;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ObraResponseDTO {
    private Long id;
    private String nombre;
    private Double latitud;
    private Double longitud;
    private String descripcion;
    private Integer anioEstado;
    private EstadoObra estado;
    private CategoriaObra categoria;
    private Long estudioId;
    private List<String> urlsImagenes;
}
