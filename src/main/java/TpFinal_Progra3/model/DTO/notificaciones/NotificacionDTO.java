package TpFinal_Progra3.model.DTO.notificaciones;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificacionDTO {

    @NotNull(message = "El ID del Receptor es obligatorio.")
    @Positive(message = "El ID del Receptor debe ser un número positivo.")
    private Long idReceptor;

    @NotBlank(message = "El mensaje no puede estar vacío.")
    @Size(min = 2, max = 500, message = "El mensaje debe tener entre 2 y 500 caracteres.")
    private String mensaje;
}
