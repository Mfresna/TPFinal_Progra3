package TpFinal_Progra3.model.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ImagenDTO {
    private Long id;

    @NotBlank(message = "El nombre de la imagen no puede estar vacío.")
    private String nombre;

    @NotBlank(message = "El tipo de la imagen es obligatorio.")
    private String tipo; // JPG, PNG, etc.

    @NotBlank(message = "La URL de la imagen es obligatoria.")
    private String url;
}