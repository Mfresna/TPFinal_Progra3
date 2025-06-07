package TpFinal_Progra3.model.mappers.impl;

import TpFinal_Progra3.model.DTO.UsuarioDTO;
import TpFinal_Progra3.model.entities.Usuario;
import TpFinal_Progra3.security.model.entities.Credencial;
import TpFinal_Progra3.security.model.entities.Rol;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UsuarioMapper {

    public Usuario crearUsuario(UsuarioDTO dto, Credencial credencial) {
        return Usuario.builder()
                .email(dto.getEmail())
                .nombre(dto.getNombre())
                .apellido(dto.getApellido())
                .fechaNacimiento(dto.getFechaNacimiento())
                .isActivo(true)
                .credencial(credencial)
                .build();
    }

    public Credencial crearCredencial(UsuarioDTO dto, Rol rol) {
        return Credencial.builder()
                .email(dto.getEmail())
                .password(dto.getPassword()) // El Service luego la codifica
                .roles(Set.of(rol))
                .build();
    }
}
