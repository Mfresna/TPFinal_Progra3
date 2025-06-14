package TpFinal_Progra3.model.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class EstudioArqDTO {

    @NotBlank(message = "El nombre del estudio no puede estar vacío.")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres.")
    private String nombre;

    private List< @Positive(message = "El ID de la obra debe ser un número positivo.")Long> obrasIds; // Lista de IDs de las obras asociadas
    private List< @Positive(message = "El ID del arquitecto debe ser un número positivo.")Long> arquitectosIds; // Lista de IDs de los arquitectos
    private String imagenUrl; // URL de la imagen asociada
}