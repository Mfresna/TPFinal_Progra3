package TpFinal_Progra3.services.impl;

import TpFinal_Progra3.model.DTO.UsuarioDTO;
import TpFinal_Progra3.model.entities.Usuario;
import TpFinal_Progra3.model.mappers.impl.UsuarioMapper;
import TpFinal_Progra3.repositories.UsuarioRepository;
import TpFinal_Progra3.security.model.entities.Credencial;
import TpFinal_Progra3.security.model.entities.Rol;
import TpFinal_Progra3.security.model.enums.RolUsuario;

import TpFinal_Progra3.security.repositories.CredencialRepository;
import TpFinal_Progra3.security.repositories.RolRepository;
import TpFinal_Progra3.services.interfaces.UsuarioServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UsuarioServiceInt {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;
    private final CredencialRepository credencialRepository;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          RolRepository rolRepository,
                          UsuarioMapper usuarioMapper,
                          PasswordEncoder passwordEncoder,
                          CredencialRepository credencialRepository) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.usuarioMapper = usuarioMapper;
        this.passwordEncoder = passwordEncoder;
        this.credencialRepository = credencialRepository;
    }

    @Override
    public UsuarioDTO registrarUsuario(UsuarioDTO dto) {

        Usuario usuario = usuarioMapper.crearUsuario(dto);

        // Crear la credencial a partir del DTO y rol
//        Credencial credencial = usuarioMapper.crearCredencial(dto, rol);
//        credencial.setPassword(passwordEncoder.encode(credencial.getPassword()));

        // Crear el usuario pasándole la credencial
        usuario.getCredencial().setUsuario(usuario);
        if(rolRepository.findByRol(RolUsuario.ROL_USUARIO).isEmpty()){
            Rol rol = Rol.builder()
                    .rol(RolUsuario.ROL_USUARIO)
                    .build();
            rolRepository.save(rol);
        }
        credencialRepository.save(usuario.getCredencial());

        usuarioRepository.save(usuario);

        // Establecer el vínculo inverso: credencial conoce su usuario
//        credencial.setUsuario(usuario);
//        credencialRepository.save(credencial);

        // Guardar el usuario con su credencial y roles
        return usuarioMapper.crearDTO(usuario);
    }

}
