package TpFinal_Progra3.model.DTO.filtros;

import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class EstudioArqFiltroDTO {

    // Filtro por nombre parcial o completo (opcional)
    private String nombre;

    // Filtro por una obra asociada (opcional)
    @Positive(message = "El ID de la obra debe ser un número positivo.")
    private Long obraId;
}
