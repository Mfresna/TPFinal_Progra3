package TpFinal_Progra3.model.mappers.impl;

import TpFinal_Progra3.model.DTO.UsuarioBasicoDTO;
import TpFinal_Progra3.model.entities.Usuario;
import TpFinal_Progra3.model.mappers.interfaces.IMapper;
import org.springframework.stereotype.Component;

@Component
public class UsuarioBasicoMapper implements IMapper<Usuario, UsuarioBasicoDTO> {

    @Override
    public UsuarioBasicoDTO mapTo(Usuario usuario) {
        return UsuarioBasicoDTO.builder()
                .id(usuario.getId())
                .email(usuario.getEmail())
                .nombre(usuario.getNombre())
                .apellido(usuario.getApellido())
                .isActivo(usuario.getIsActivo())
                .build();
    }

    @Override
    public Usuario mapFrom(UsuarioBasicoDTO dto) {
        return Usuario.builder()
                .email(dto.getEmail())
                .nombre(dto.getNombre())
                .apellido(dto.getApellido())
                .isActivo(dto.getIsActivo())
                .build();
    }
}
