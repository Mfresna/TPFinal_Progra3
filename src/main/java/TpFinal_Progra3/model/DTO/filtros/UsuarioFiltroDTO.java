package TpFinal_Progra3.model.DTO.filtros;

import TpFinal_Progra3.security.model.enums.RolUsuario;
import lombok.Data;

@Data
public class UsuarioFiltroDTO {
    private String nombre;
    private String apellido;
    private String email;
    private Boolean isActivo;
    private RolUsuario rol;
}
