package TpFinal_Progra3.controllers;

import TpFinal_Progra3.exceptions.NotFoundException;
import TpFinal_Progra3.model.DTO.ObraDTO;
import TpFinal_Progra3.model.DTO.filtros.ObraFiltroDTO;
import TpFinal_Progra3.model.entities.EstudioArq;
import TpFinal_Progra3.model.entities.Obra;
import TpFinal_Progra3.model.enums.CategoriaObra;
import TpFinal_Progra3.model.enums.EstadoObra;
import TpFinal_Progra3.model.mappers.implementacion.ObraMapper;
import TpFinal_Progra3.repositories.EstudioArqRepository;
import TpFinal_Progra3.services.implementacion.ObraService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/obras")
@RequiredArgsConstructor
public class ObraController {

    private final ObraService obraService;
    private final EstudioArqRepository estudioArqRepository;
    private final ObraMapper obraMapper;

    @PostMapping
    public ResponseEntity<ObraDTO> crearObra(@RequestBody @Valid ObraDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(obraService.crearObra(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ObraDTO> obtenerObra(@PathVariable Long id) {
        return ResponseEntity.ok(obraService.obtenerObra(id));
    }

    @GetMapping
    public ResponseEntity<List<ObraDTO>> listarObras() {
        return ResponseEntity.ok(obraService.listarObras());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarObra(@PathVariable Long id) {
        obraService.eliminarObra(id);
        return ResponseEntity.ok("Obra eliminada correctamente.");
    }

    /* Devuelve toda la pagina entera, esta bueno para ver en el navegador pero en postman se ve feo
    @GetMapping("/mapa/{id}")
    public void obraEnMapa(@PathVariable Long id,
                           @RequestParam(required = false,defaultValue = "16") @Min(1) @Max(19) int zoom,
                           HttpServletResponse response) throws IOException {
        //El link que devuelve service lo redirecciona con response en un 302 found
        response.sendRedirect(obraService.obraEnMapa(zoom,id));
    }*/

    @GetMapping("/mapa/{id}")
    public ResponseEntity<Map<String,String>> obraEnMapa(@PathVariable Long id,
                           @RequestParam(required = false,defaultValue = "16") @Min(1) @Max(19) int zoom) {
        return ResponseEntity.ok(Map.of("url:", obraService.obraEnMapa(zoom,id)));
    }

    @GetMapping("/area")
    public ResponseEntity<List<ObraDTO>> obrasPorTerritorio(@RequestParam(required = false) String ciudad,
                                                            @RequestParam String pais){
        return ResponseEntity.ok(obraService.obrasPorTerritorio(ciudad,pais));
    }




    @GetMapping("/filtrar")
    public ResponseEntity<List<ObraDTO>> filtrarObras(
            @RequestParam(required = false) CategoriaObra categoria,
            @RequestParam(required = false) EstadoObra estado,
            @RequestParam(required = false) Long estudioId) {

        ObraFiltroDTO filtro = new ObraFiltroDTO();
        filtro.setCategoria(categoria);
        filtro.setEstado(estado);
        filtro.setEstudioId(estudioId);

        return ResponseEntity.ok(obraService.filtrarObras(filtro));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ObraDTO> actualizarObra(
            @PathVariable Long id,
            @Valid @RequestBody ObraDTO obraDTO) {
        return ResponseEntity.ok(obraService.modificarObra(id, obraDTO));
    }





}
