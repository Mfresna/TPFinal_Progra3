package TpFinal_Progra3.model.DTO.obras;

import TpFinal_Progra3.model.entities.Imagen;
import TpFinal_Progra3.model.enums.CategoriaObra;
import TpFinal_Progra3.model.enums.EstadoObra;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ObraDTO {

    @NotBlank(message = "El nombre es obligatorio.")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres.")
    private String nombre;

    @NotNull(message = "La latitud no puede ser nula.")
    @DecimalMin(value = "-90.0", inclusive = true, message = "La latitud debe ser mayor o igual a -90.0.")
    @DecimalMax(value = "90.0", inclusive = true, message = "La latitud debe ser menor o igual a 90.0.")
    private Double latitud;

    @NotNull(message = "La longitud no puede ser nula.")
    @DecimalMin(value = "-180.0", inclusive = true, message = "La longitud debe ser mayor o igual a -180.0.")
    @DecimalMax(value = "180.0", inclusive = true, message = "La longitud debe ser menor o igual a 180.0.")
    private Double longitud;

    @NotBlank(message = "Debe ingresar una descripcion de la Obra.")
    @Size(max = 16000000)
    private String descripcion;

    @NotNull(message = "El año del estado no puede ser nulo.")
    private Integer anioEstado;

    @NotNull(message = "El estado de la obra es obligatorio.")
    private EstadoObra estado;

    @NotNull(message = "La categoría de la obra es obligatoria.")
    private CategoriaObra categoria;

    @NotNull(message = "El ID del estudio es obligatorio.")
    @Positive(message = "El ID del estudio debe ser un número positivo.")
    private Long estudioId;

    private List<@Size(max = 2048, message = "La URL no debe superar los 2048 caracteres.")
                @Pattern(regexp = "^(https?://).+\\.(jpg|jpeg|png|gif|bmp|webp)$",
                        message = "La URL debe comenzar con http o https y terminar en una imagen válida (.jpg, .png, etc.).")
            String> urlsImagenes;
}