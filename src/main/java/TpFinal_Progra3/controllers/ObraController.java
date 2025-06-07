package TpFinal_Progra3.controllers;

import TpFinal_Progra3.model.DTO.ObraDTO;
import TpFinal_Progra3.model.entities.EstudioArq;
import TpFinal_Progra3.model.entities.Obra;
import TpFinal_Progra3.model.mappers.implementacion.ObraMapper;
import TpFinal_Progra3.repositories.EstudioArqRepository;
import TpFinal_Progra3.services.implementacion.ObraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/obras")
public class ObraController {

    private final ObraService obraService;
    private final EstudioArqRepository estudioArqRepository;
    private final ObraMapper obraMapper;

    public ObraController(ObraService obraService, EstudioArqRepository estudioArqRepository, ObraMapper obraMapper) {
        this.obraService = obraService;
        this.estudioArqRepository = estudioArqRepository;
        this.obraMapper = obraMapper;
    }

    @PostMapping
    public ResponseEntity<Obra> crearObra(@RequestBody ObraDTO dto) {
        EstudioArq estudio = estudioArqRepository.findById(dto.getEstudioId())
                .orElseThrow(() -> new RuntimeException("Estudio no encontrado"));

        Obra obra = obraMapper.crearObra(dto, estudio);
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
