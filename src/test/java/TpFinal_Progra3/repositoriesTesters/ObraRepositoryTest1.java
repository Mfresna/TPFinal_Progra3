package TpFinal_Progra3.repositoriesTesters;

import TpFinal_Progra3.model.entities.Obra;
import TpFinal_Progra3.model.enums.CategoriaObra;
import TpFinal_Progra3.model.enums.EstadoObra;
import TpFinal_Progra3.repositories.ObraRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ObraRepositoryTest1 {

    @Autowired
    private ObraRepository obraRepository;

    @Test
    void testGuardarObra() {
        // Crear y guardar una obra en la BD
        Obra obra = new Obra();
        obra.setNombre("Casa Moderna");
        obra.setCategoria(CategoriaObra.ARQ_RESIDENCIAL);
        obra.setEstado(EstadoObra.CONSTRUCCION);
        obra.setLatitud(-34.6037);
        obra.setLongitud(-58.3816);
        obra.setDescripcion("Diseño moderno en Buenos Aires.");

        obra = obraRepository.save(obra);
        obraRepository.flush(); // ✅ Forzar escritura en BD

        // Buscar la obra por su ID para verificar que realmente se guardó
        Optional<Obra> obraEncontrada = obraRepository.findById(obra.getId());

        // Imprimir resultados
        obraEncontrada.ifPresent(o -> {
            System.out.println("✅ Obra guardada y encontrada:");
            System.out.println("ID: " + o.getId());
            System.out.println("Nombre: " + o.getNombre());
            System.out.println("Categoría: " + o.getCategoria());
            System.out.println("Estado: " + o.getEstado());
            System.out.println("Latitud: " + o.getLatitud());
            System.out.println("Longitud: " + o.getLongitud());
            System.out.println("Descripción: " + o.getDescripcion());
        });

        // Validaciones
        assertTrue(obraEncontrada.isPresent(), "La obra no fue encontrada en la BD");
        assertEquals("Casa Moderna", obraEncontrada.get().getNombre());
        assertEquals(CategoriaObra.ARQ_RESIDENCIAL, obraEncontrada.get().getCategoria());
        assertEquals(EstadoObra.CONSTRUCCION, obraEncontrada.get().getEstado());
    }
}