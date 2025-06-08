package TpFinal_Progra3.controllers;

import TpFinal_Progra3.model.DTO.EstudioArqDTO;
import TpFinal_Progra3.services.implementacion.EstudioArqService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudios")
@RequiredArgsConstructor
public class EstudioArqController {

    private final EstudioArqService estudioArqService;

    @PostMapping
    public ResponseEntity<EstudioArqDTO> crearEstudio(@RequestBody @Valid EstudioArqDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(estudioArqService.crearEstudio(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstudioArqDTO> obtenerEstudio(@PathVariable Long id) {
        return ResponseEntity.ok(estudioArqService.obtenerEstudio(id));
    }

    @GetMapping
    public ResponseEntity<List<EstudioArqDTO>> listarEstudios() {
        return ResponseEntity.ok(estudioArqService.listarEstudios());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarEstudio(@PathVariable Long id) {
        estudioArqService.eliminarEstudio(id);
        return ResponseEntity.ok("Estudio eliminado correctamente.");
    }
}