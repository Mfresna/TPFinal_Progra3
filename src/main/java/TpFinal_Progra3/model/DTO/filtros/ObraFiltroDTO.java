package TpFinal_Progra3.model.DTO.filtros;

import TpFinal_Progra3.model.enums.CategoriaObra;
import TpFinal_Progra3.model.enums.EstadoObra;
import lombok.Data;

@Data
public class ObraFiltroDTO {
    private CategoriaObra categoria;
    private EstadoObra estado;
    private Long estudioId;
}
