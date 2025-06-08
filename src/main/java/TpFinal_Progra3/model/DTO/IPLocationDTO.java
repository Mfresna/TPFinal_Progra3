package TpFinal_Progra3.model.DTO;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IPLocationDTO {
    //Recibe los Datos del JSON del service IPLocationService
    @NotBlank(message = "La dirección IP no puede estar vacía o ser nula.")
    private String ip;

    @NotNull(message = "La latitud no puede ser nula.")
    @DecimalMin(value = "-90.0", inclusive = true, message = "La latitud debe ser mayor o igual a -90.0.")
    @DecimalMax(value = "90.0", inclusive = true, message = "La latitud debe ser menor o igual a 90.0.")
    private double latitud;

    @NotNull(message = "La longitud no puede ser nula.")
    @DecimalMin(value = "-180.0", inclusive = true, message = "La longitud debe ser mayor o igual a -180.0.")
    @DecimalMax(value = "180.0", inclusive = true, message = "La longitud debe ser menor o igual a 180.0.")
    private double longitud;
}
