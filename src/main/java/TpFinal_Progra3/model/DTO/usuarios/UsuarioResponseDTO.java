package TpFinal_Progra3.model.DTO.usuarios;

import TpFinal_Progra3.model.DTO.FavoritoBasicoDTO;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Builder
@Data
public class UsuarioResponseDTO {
    private Long id;
    private String email;
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private String descripcion;
    private String urlImagen;
    private List<Long> idEstudios;
    private List<FavoritoBasicoDTO>listaFavoritos;
}



