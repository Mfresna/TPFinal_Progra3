package TpFinal_Progra3.controllers;

import TpFinal_Progra3.model.DTO.filtros.UsuarioFiltroDTO;
import TpFinal_Progra3.model.DTO.usuarios.UsuarioBasicoDTO;
import TpFinal_Progra3.model.DTO.usuarios.UsuarioDTO;
import TpFinal_Progra3.model.DTO.usuarios.UsuarioResponseDTO;
import TpFinal_Progra3.security.model.enums.RolUsuario;
import TpFinal_Progra3.services.implementacion.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

//    @PostMapping
//    public ResponseEntity<UsuarioResponseDTO> resgistrarUsuario(@RequestBody @Valid UsuarioDTO dto) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.registrarUsuario(dto));
//    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> obtenerUsuario(@PathVariable @Positive Long id) {
        return ResponseEntity.ok(usuarioService.obtenerUsuario(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> actualizarUsuario(@PathVariable @Positive Long id,
                                                                @RequestBody @Valid UsuarioBasicoDTO usrDto) {
        return ResponseEntity.ok(usuarioService.modificarUsuario(id, usrDto));
    }

    @PatchMapping("/imagenPerfil")
    public ResponseEntity<UsuarioResponseDTO> actualizarImagenPerfil(HttpServletRequest request,
                                                                     @RequestBody @Pattern(regexp = "^(https?://).+\\.(jpg|jpeg|png|gif|bmp|webp)$")
                                                                     String urlBorrar) {
        //Actualiza solo la imagen de perfil del usuario
        return ResponseEntity.ok(usuarioService.actualizarImagenPerfil(request, urlBorrar));
    }

    @PatchMapping("/{id}")
    //@PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<String> cambiarEstadoDeCuenta(HttpServletRequest request,
                                                    @PathVariable @Positive Long id,
                                                    @RequestParam Boolean habilitacion) {
        if(habilitacion){
            return ResponseEntity.ok(usuarioService.habilitarCuenta(id, request));
        }else{
            return ResponseEntity.ok(usuarioService.inhabilitarCuenta(id, request));
        }
    }

    @GetMapping("/filtrar")
    public ResponseEntity<List<UsuarioResponseDTO>> filtrarUsuarios(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String apellido,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) Boolean isActivo,
            @RequestParam(required = false) RolUsuario rol) {

        UsuarioFiltroDTO filtro = new UsuarioFiltroDTO();
        filtro.setNombre(nombre);
        filtro.setApellido(apellido);
        filtro.setEmail(email);
        filtro.setIsActivo(isActivo);
        filtro.setRol(rol);

        return ResponseEntity.ok(usuarioService.filtrarUsuarios(filtro));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me")
    public ResponseEntity<UsuarioResponseDTO> obtenerMiPerfil(HttpServletRequest request) {
        return ResponseEntity.ok(usuarioService.obtenerMiPerfil(request));
    }

}
