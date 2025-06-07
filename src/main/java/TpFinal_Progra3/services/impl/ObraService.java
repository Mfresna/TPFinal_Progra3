package TpFinal_Progra3.services.impl;

import TpFinal_Progra3.model.entities.Obra;
import TpFinal_Progra3.repositories.ObraRepository;
import TpFinal_Progra3.services.interfaces.ObraServiceInt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ObraService implements ObraServiceInt {

    private final ObraRepository obraRepository;

    public ObraService(ObraRepository obraRepository) {
        this.obraRepository = obraRepository;
    }

    @Override
    public Obra crearObra(Obra obra) {
        return obraRepository.save(obra);
    }

    @Override
    public Optional<Obra> obtenerObra(Long id) {
        return obraRepository.findById(id);
    }

    @Override
    public List<Obra> listarObras() {
        return obraRepository.findAll();
    }

    @Override
    public boolean eliminarObra(Long id) {
        if (obraRepository.existsById(id)) {
            obraRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
