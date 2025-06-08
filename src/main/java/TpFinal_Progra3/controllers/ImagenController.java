package TpFinal_Progra3.controllers;

import TpFinal_Progra3.model.DTO.ImagenDTO;
import TpFinal_Progra3.services.implementacion.ImagenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/imagenes")
@RequiredArgsConstructor
public class ImagenController {

    private final ImagenService imagenService;

    @PostMapping
    public ResponseEntity<ImagenDTO> crearImagen(@RequestBody @Valid ImagenDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(imagenService.crearImagen(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImagenDTO> obtenerImagen(@PathVariable Long id) {
        return ResponseEntity.ok(imagenService.obtenerImagen(id));
    }

    @GetMapping
    public ResponseEntity<List<ImagenDTO>> listarImagenes() {
        return ResponseEntity.ok(imagenService.listarImagenes());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarImagen(@PathVariable Long id) {
        imagenService.eliminarImagen(id);
        return ResponseEntity.ok("Imagen eliminada correctamente.");
    }
}