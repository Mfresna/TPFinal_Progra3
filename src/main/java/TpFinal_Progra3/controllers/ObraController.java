package TpFinal_Progra3.controllers;

import TpFinal_Progra3.model.entities.Obra;
import TpFinal_Progra3.services.impl.ObraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/obras")
public class ObraController {

    private final ObraService obraService;

    public ObraController(ObraService obraService) {
        this.obraService = obraService;
    }

    @PostMapping
    public ResponseEntity<Obra> crearObra(@RequestBody Obra obra) {
        Obra creada = obraService.crearObra(obra);
        return ResponseEntity.ok(creada);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Obra> obtenerObra(@PathVariable Long id) {
        return obraService.obtenerObra(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Obra>> listarObras() {
        return ResponseEntity.ok(obraService.listarObras());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarObra(@PathVariable Long id) {
        if (obraService.eliminarObra(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

