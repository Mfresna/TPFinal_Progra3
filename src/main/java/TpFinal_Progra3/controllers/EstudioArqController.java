package TpFinal_Progra3.controllers;

import TpFinal_Progra3.model.DTO.EstudioArqDTO;
import TpFinal_Progra3.model.DTO.filtros.EstudioArqFiltroDTO;
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

    @GetMapping("/filtrar")
    public ResponseEntity<List<EstudioArqDTO>> filtrarEstudios(@RequestParam(required = false) String nombre,
                                                               @RequestParam(required = false) Long obraId) {

        EstudioArqFiltroDTO filtro = new EstudioArqFiltroDTO();
        filtro.setNombre(nombre);
        filtro.setObraId(obraId);

        return ResponseEntity.ok(estudioArqService.filtrarEstudios(filtro));
    }

//    @PutMapping("/{estudioId}/arquitectos/{arquitectoId}")
//    public ResponseEntity<EstudioArqDTO> agregarArquitecto(
//            @PathVariable Long estudioId,
//            @PathVariable Long arquitectoId) {
//
//        EstudioArqDTO actualizado = estudioArqService.agregarArquitectoAEstudio(estudioId, arquitectoId);
//        return ResponseEntity.ok(actualizado);
//    }

    @PutMapping("/{id}")
    public ResponseEntity<EstudioArqDTO> actualizarEstudio(@PathVariable Long id,
                                                           @Valid @RequestBody EstudioArqDTO dto) {
        return ResponseEntity.ok(estudioArqService.modificarEstudio(id, dto));
    }

}