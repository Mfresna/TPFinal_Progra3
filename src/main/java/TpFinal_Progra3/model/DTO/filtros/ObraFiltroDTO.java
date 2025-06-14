package TpFinal_Progra3.model.DTO.filtros;

import TpFinal_Progra3.model.enums.CategoriaObra;
import TpFinal_Progra3.model.enums.EstadoObra;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ObraFiltroDTO {
    private CategoriaObra categoria;
    private EstadoObra estado;

    @Positive(message = "El ID del estudio debe ser un número positivo.")
    private Long estudioId;
}
