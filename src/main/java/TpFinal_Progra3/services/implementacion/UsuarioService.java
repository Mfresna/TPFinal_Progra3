package TpFinal_Progra3.services.implementacion;

import TpFinal_Progra3.exceptions.CredencialException;
import TpFinal_Progra3.exceptions.NotFoundException;
import TpFinal_Progra3.model.DTO.filtros.UsuarioFiltroDTO;
import TpFinal_Progra3.model.DTO.obras.ObraResponseDTO;
import TpFinal_Progra3.model.DTO.usuarios.UsuarioDTO;
import TpFinal_Progra3.model.DTO.usuarios.UsuarioResponseDTO;
import TpFinal_Progra3.model.mappers.ObraMapper;
import TpFinal_Progra3.model.mappers.UsuarioMapper;
import TpFinal_Progra3.repositories.UsuarioRepository;
import TpFinal_Progra3.security.model.entities.Credencial;
import TpFinal_Progra3.security.model.enums.RolUsuario;
import TpFinal_Progra3.security.repositories.RolRepository;
import TpFinal_Progra3.specifications.UsuarioSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final RolRepository RolRepository;


    public UsuarioResponseDTO registrarUsuario(UsuarioDTO dto) {
        //Validar la existencia de un email
        if(usuarioRepository.existsByEmailIgnoreCase(dto.getEmail())){
            throw new CredencialException("El email ya existe en la base de datos.");
        }
        //CREAMOS UNA CREDENCIAL PARA PRUEBA, DESPUES CAMBIAR
        Credencial credencial = Credencial.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())

    }

    public UsuarioResponseDTO obtenerUsuario(Long id) {
        return usuarioRepository.findById(id)
                .map(usuarioMapper::mapResponseDTO)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrada con ID: " + id));
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


}
