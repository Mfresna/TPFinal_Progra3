package TpFinal_Progra3.model.DTO;

import TpFinal_Progra3.security.model.enums.RolUsuario;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class UsuarioDTO {
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private LocalDate fechaNacimiento;
}
