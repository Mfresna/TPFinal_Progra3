package TpFinal_Progra3.services.impl;

import TpFinal_Progra3.model.DTO.UsuarioDTO;
import TpFinal_Progra3.model.entities.Usuario;
import TpFinal_Progra3.model.mappers.impl.UsuarioMapper;
import TpFinal_Progra3.repositories.UsuarioRepository;
import TpFinal_Progra3.security.model.entities.Credencial;
import TpFinal_Progra3.security.model.entities.Rol;
import TpFinal_Progra3.security.model.enums.RolUsuario;

import TpFinal_Progra3.security.repositories.RolRepository;
import TpFinal_Progra3.services.interfaces.UsuarioServiceInt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UsuarioServiceInt {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository,
                              RolRepository rolRepository,
                              UsuarioMapper usuarioMapper,
                              PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.usuarioMapper = usuarioMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Usuario registrarUsuario(UsuarioDTO dto) {
        Rol rol = rolRepository.findByRol(RolUsuario.ROL_USUARIO)
                .orElseThrow(() -> new RuntimeException("Rol por defecto no encontrado"));

        Credencial credencial = usuarioMapper.crearCredencial(dto, rol);
        credencial.setPassword(passwordEncoder.encode(credencial.getPassword()));

        Usuario usuario = usuarioMapper.crearUsuario(dto, credencial);

        return usuarioRepository.save(usuario);
    }
}
