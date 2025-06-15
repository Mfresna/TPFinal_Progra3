package TpFinal_Progra3.controllers;

import TpFinal_Progra3.model.DTO.ImagenDTO;
import TpFinal_Progra3.model.entities.Imagen;
import TpFinal_Progra3.services.CloudinaryService;
import TpFinal_Progra3.services.implementacion.ImagenService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/imagenes")
@RequiredArgsConstructor
public class ImagenController {

    private final ImagenService imagenService;


    @GetMapping("/{id}")
    public ResponseEntity<ImagenDTO> obtenerImagen(@PathVariable @Positive Long id) {
        return ResponseEntity.ok(imagenService.obtenerImagen(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarImagen(@PathVariable @Positive Long id) {
        imagenService.eliminarImagen(id);
        return ResponseEntity.ok("Imagen eliminada correctamente.");
    }

    @PostMapping("/subir")
    public ResponseEntity<List<String>> subirImagenes(@RequestParam("archivos") List<MultipartFile> archivos){
        return ResponseEntity.ok(imagenService.subirImagenes(archivos));
    }
}