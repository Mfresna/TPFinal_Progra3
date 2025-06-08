package TpFinal_Progra3.model.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class EstudioArqDTO {
    private Long id;

    @NotBlank(message = "El nombre del estudio no puede estar vacío.")
    private String nombre;

    private List<Long> obrasIds; // Lista de IDs de las obras asociadas
    private List<Long> arquitectosIds; // Lista de IDs de los arquitectos
    private String imagenUrl; // URL de la imagen asociada
}