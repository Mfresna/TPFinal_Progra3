package TpFinal_Progra3.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * DTO para {@link TpFinal_Progra3.model.entities.Usuario}
 */
@Data
@AllArgsConstructor
@Builder
public class UsuarioBasicoDTO {
    private Long id;
    private String email;
    private String nombre;
    private String apellido;
    private Boolean isActivo;
}