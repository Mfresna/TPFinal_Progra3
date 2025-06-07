package TpFinal_Progra3.model.mappers.impl;

import TpFinal_Progra3.model.DTO.UsuarioDTO;
import TpFinal_Progra3.model.entities.Usuario;
import TpFinal_Progra3.security.model.entities.Credencial;
import TpFinal_Progra3.security.model.entities.Rol;
import TpFinal_Progra3.security.model.enums.RolUsuario;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UsuarioMapper {

    public Usuario crearUsuario(UsuarioDTO dto) {
        return Usuario.builder()
                .email(dto.getEmail())
                .nombre(dto.getNombre())
                .apellido(dto.getApellido())
                .fechaNacimiento(dto.getFechaNacimiento())
                .credencial(Credencial.builder()
                        .email(dto.getEmail())
                        .password(dto.getPassword())
                        .roles(Set.of(Rol.builder().rol(RolUsuario.ROL_USUARIO).build())) // Asignar un rol por defecto
                        .build())
                .build();
    }

    public UsuarioDTO crearDTO(Usuario usr) {
        return UsuarioDTO.builder()
                .email(usr.getEmail())
                .nombre(usr.getNombre())
                .apellido(usr.getApellido())
                .fechaNacimiento(usr.getFechaNacimiento())
                .password(usr.getCredencial().getPassword())
                .build();
    }

//    public Credencial crearCredencial(UsuarioDTO dto, Rol rol) {
//        return Credencial.builder()
//                .email(dto.getEmail())
//                .password(dto.getPassword()) // El Service luego la codifica
//                .roles(Set.of(rol))
//                .build();
//    }
}
