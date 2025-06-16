package TpFinal_Progra3.services.implementacion;

import TpFinal_Progra3.exceptions.CredencialException;
import TpFinal_Progra3.exceptions.NotFoundException;
import TpFinal_Progra3.exceptions.ProcesoInvalidoException;
import TpFinal_Progra3.model.DTO.filtros.UsuarioFiltroDTO;
import TpFinal_Progra3.model.DTO.obras.ObraDTO;
import TpFinal_Progra3.model.DTO.obras.ObraResponseDTO;
import TpFinal_Progra3.model.DTO.usuarios.UsuarioBasicoDTO;
import TpFinal_Progra3.model.DTO.usuarios.UsuarioDTO;
import TpFinal_Progra3.model.DTO.usuarios.UsuarioResponseDTO;
import TpFinal_Progra3.model.entities.EstudioArq;
import TpFinal_Progra3.model.entities.Imagen;
import TpFinal_Progra3.model.entities.Obra;
import TpFinal_Progra3.model.entities.Usuario;
import TpFinal_Progra3.model.mappers.ObraMapper;
import TpFinal_Progra3.model.mappers.UsuarioMapper;
import TpFinal_Progra3.repositories.UsuarioRepository;
import TpFinal_Progra3.security.model.entities.Credencial;
import TpFinal_Progra3.security.model.entities.Rol;
import TpFinal_Progra3.security.model.enums.RolUsuario;
import TpFinal_Progra3.security.repositories.RolRepository;
import TpFinal_Progra3.security.services.JwtService;
import TpFinal_Progra3.specifications.UsuarioSpecification;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final RolRepository RolRepository;
    private final ImagenService imagenService;
    private final JwtService jwtService;

    //VERIFICAR
//    public UsuarioResponseDTO registrarUsuario(UsuarioDTO dto) {
//        //Validar la existencia de un email
//        if(usuarioRepository.existsByEmailIgnoreCase(dto.getEmail())){
//            throw new CredencialException("El email ya existe en la base de datos.");
//        }
//
//        //CREAMOS UNA CREDENCIAL PARA PRUEBA, DESPUES CAMBIAR
//        Rol rol = Rol.builder()
//                .rol(RolUsuario.ROLE_USUARIO)
//                .build();
//        Credencial credencial = Credencial.builder()
//                .email(dto.getEmail())
//                .password(dto.getPassword())
//                .roles(Set.of(rol))
//                .build();
//        //--------------------------
//
//        Imagen img = null;
//        if(dto.getImagenUrl() != null) {
//           img=imagenService.obtenerImagen(dto.getImagenUrl());
//        }
//
//        Usuario usuario = usuarioMapper.mapUsuario(dto, img);
//        usuario.setCredencial(credencial);
//
//        return usuarioMapper.mapResponseDTO(usuarioRepository.save(usuario));
//
//    }

    public Usuario buscarUsuario(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado con ID: " + id));
    }

    public UsuarioResponseDTO obtenerUsuario(Long id) {
        return usuarioMapper.mapResponseDTO(buscarUsuario(id));
    }

    public List<UsuarioResponseDTO> filtrarUsuarios(UsuarioFiltroDTO filtro) {
        // Validar existencia del rol si se solicita
        if (filtro.getRol() != null && RolRepository.findByRol(filtro.getRol()).isEmpty()) {
            throw new NotFoundException("El rol " + filtro.getRol() + " no existe.");
        }

        return usuarioRepository.findAll(UsuarioSpecification.filtrar(filtro))
                .stream()
                .map(usuarioMapper::mapResponseDTO)
                .toList();
    }

    public UsuarioResponseDTO modificarUsuario(Long id, UsuarioBasicoDTO usrDto) {
        Usuario usuario = buscarUsuario(id);

        usuario.setNombre(usrDto.getNombre());
        usuario.setApellido(usrDto.getApellido());
        usuario.setFechaNacimiento(usrDto.getFechaNacimiento());
        usuario.setDescripcion(usrDto.getDescripcion());
        usuario.setImagen(Optional.ofNullable(usrDto.getUrlImagen())
                .map(imagenService::obtenerImagen)
                .orElse(usuario.getImagen()));

        return usuarioMapper.mapResponseDTO(usuarioRepository.save(usuario));
    }

    public UsuarioResponseDTO actualizarImagenPerfil(HttpServletRequest request, String url) {
        //Usuario Logeado
        Usuario usuario = buscarUsuario(obtenerMiPerfil(request).getId());
        System.out.println(usuario);
        System.out.println(usuario.getImagen());
        usuario.setImagen(imagenService.obtenerImagen(url));
        System.out.println(usuario);

        return usuarioMapper.mapResponseDTO(usuarioRepository.save(usuario));
    }

    public String inhabilitarCuenta(Long id,HttpServletRequest request) {
        if(obtenerMiPerfil(request).getId().equals(id)){
            throw new ProcesoInvalidoException("El usuario " + id + " no puede inhabilitar su propia cuenta.");
        }

        Usuario usr = buscarUsuario(id);
        if(!usr.getIsActivo()){
            throw new ProcesoInvalidoException("El usuario " + id + " ya se encontraba inhabilitado");
        }
        usr.setIsActivo(false);
        usuarioRepository.save(usr);

        return ("El usuario " + id + ", email "+ usr.getEmail() + " se ha inhabilitado correctamente.");
    }

    public String habilitarCuenta(Long id,HttpServletRequest request) {
        if(obtenerMiPerfil(request).getId().equals(id)){
            throw new ProcesoInvalidoException("El usuario " + id + " no puede habilitar su propia cuenta.");
        }

        Usuario usr = buscarUsuario(id);
        if(usr.getIsActivo()){
            throw new ProcesoInvalidoException("El usuario " + id + " ya se encontraba habilitado");
        }
        usr.setIsActivo(true);
        usuarioRepository.save(usr);

        return ("El usuario " + id + ", email "+ usr.getEmail() + "se ha habilitado correctamente.");
    }

    public UsuarioResponseDTO obtenerMiPerfil(HttpServletRequest request){

        String tokenHeader = request.getHeader("Authorization");

        if (tokenHeader == null || !tokenHeader.startsWith("Bearer ")) {
            throw new CredencialException(HttpStatus.UNAUTHORIZED,"No se identificó el Token proporcionado.");
        }

        String emailUsuario = jwtService.extractUsername(tokenHeader.substring(7));

        return usuarioRepository.findByEmailIgnoreCase(emailUsuario)
                .map(usuarioMapper::mapResponseDTO)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado a partir del Token recibido."));
    }

}

