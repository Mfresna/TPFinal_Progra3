package TpFinal_Progra3.controllers;

import TpFinal_Progra3.model.DTO.filtros.UsuarioFiltroDTO;
import TpFinal_Progra3.model.DTO.usuarios.UsuarioBasicoDTO;
import TpFinal_Progra3.model.DTO.usuarios.UsuarioDTO;
import TpFinal_Progra3.model.DTO.usuarios.UsuarioResponseDTO;
import TpFinal_Progra3.security.model.entities.Credencial;
import TpFinal_Progra3.security.model.enums.RolUsuario;
import TpFinal_Progra3.services.implementacion.UsuarioService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> resgistrarUsuario(@RequestBody @Valid UsuarioDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.registrarUsuario(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> obtenerObra(@PathVariable @Positive Long id) {
        return ResponseEntity.ok(usuarioService.obtenerUsuario(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> actualizarUsuario(@PathVariable @Positive Long id,
                                                             @Valid @RequestBody UsuarioBasicoDTO usrDto) {
        return ResponseEntity.ok(usuarioService.modificarUsuario(id, usrDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> cerrarCuenta(@PathVariable @Positive Long id) {
        //Solo para ADM y verificar que id no sea el del usuario logueado
        return ResponseEntity.ok(usuarioService.cerrarCuenta(id));
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

    @GetMapping("/me")
    public ResponseEntity<UsuarioResponseDTO> obtenerObra() {
        return ResponseEntity.ok(usuarioService.obtenerUsuarioLogueado());
    }

    //---------------NOTIFICACIONES----------------






}
