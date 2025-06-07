package TpFinal_Progra3.model.DTO;

import TpFinal_Progra3.model.enums.CategoriaObra;
import TpFinal_Progra3.model.enums.EstadoObra;
import lombok.Data;

@Data
public class ObraDTO {
    private String nombre;
    private Double latitud;
    private Double longitud;
    private String descripcion;
    private Integer anioEstado;
    private EstadoObra estado;
    private CategoriaObra categoria;
    private Long estudioId;
}
