package TpFinal_Progra3.model.mappers;

import TpFinal_Progra3.model.DTO.FavoritoBasicoDTO;
import TpFinal_Progra3.model.DTO.usuarios.UsuarioDTO;
import TpFinal_Progra3.model.DTO.usuarios.UsuarioResponseDTO;
import TpFinal_Progra3.model.entities.EstudioArq;
import TpFinal_Progra3.model.entities.Imagen;
import TpFinal_Progra3.model.entities.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    // Convertir de DTO a entidad
    public Usuario mapObra(UsuarioDTO usuarioDTO, Imagen imagen) {
        return Usuario.builder()
                .email(usuarioDTO.getEmail())
                .nombre(usuarioDTO.getNombre())
                .apellido(usuarioDTO.getApellido())
                .fechaNacimiento(usuarioDTO.getFechaNacimiento())
                .descripcion(usuarioDTO.getDescripcion())
                .imagen(imagen)
                .build();
    }

    // Convertir de entidad a DTO
    public UsuarioDTO mapDTO(Usuario usuario) {
        return UsuarioDTO.builder()

                .build();
    }


    // Convertir de entidad a DTO (si lo necesitás para devolver en un GET)
    public UsuarioResponseDTO mapResponseDTO(Usuario usuario) {
        return UsuarioResponseDTO.builder()
                .id(usuario.getId())
                .email(usuario.getEmail())
                .nombre(usuario.getNombre())
                .apellido(usuario.getApellido())
                .fechaNacimiento(usuario.getFechaNacimiento())
                .descripcion(usuario.getDescripcion())
                .urlImagen(usuario.getImagen().getUrl())
                .idEstudios(usuario.getEstudios().stream().map(EstudioArq::getId).toList())
                .listaFavoritos(usuario.getListaFavoritos().stream()
                        .map(f -> FavoritoBasicoDTO.builder()
                                .id(f.getId())
                                .nombre(f.getNombreLista())
                                .build())
                        .toList())
                .build();
    }



}
