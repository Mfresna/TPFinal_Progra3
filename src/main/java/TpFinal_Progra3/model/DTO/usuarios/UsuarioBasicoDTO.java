package TpFinal_Progra3.model.DTO.usuarios;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Builder
@Data
public class UsuarioBasicoDTO {

    @NotBlank(message = "El nombre es obligatorio.")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres.")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio.")
    @Size(min = 2, max = 50, message = "El apellido debe tener entre 2 y 50 caracteres.")
    private String apellido;

    @NotNull(message = "La fecha de nacimiento es obligatoria.")
    @Past(message = "La fecha de nacimiento debe ser una fecha pasada.")
    private LocalDate fechaNacimiento;

    @Size(max = 255, message = "La descripción no debe superar los 255 caracteres.")
    private String descripcion;

    @Pattern(
            regexp = "^(https?://).+\\.(jpg|jpeg|png|gif|bmp|webp)$",
            message = "La URL debe comenzar con http o https y terminar en una imagen válida (.jpg, .png, etc.).")
    private String imagenUrl;
}