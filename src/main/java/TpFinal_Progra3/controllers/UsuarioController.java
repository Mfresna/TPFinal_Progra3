package TpFinal_Progra3.controllers;

import TpFinal_Progra3.model.DTO.UsuarioDTO;
import TpFinal_Progra3.model.entities.Usuario;
import TpFinal_Progra3.services.impl.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> registrarUsuario(@RequestBody UsuarioDTO dto) {
        Usuario nuevoUsuario = usuarioService.registrarUsuario(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }
}
