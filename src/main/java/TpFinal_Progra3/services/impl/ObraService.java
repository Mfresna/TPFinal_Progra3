package TpFinal_Progra3.services.impl;

import TpFinal_Progra3.model.DTO.ObraDTO;
import TpFinal_Progra3.model.entities.EstudioArq;
import TpFinal_Progra3.model.entities.Obra;
import TpFinal_Progra3.model.mappers.impl.ObraMapper;
import TpFinal_Progra3.repositories.EstudioArqRepository;
import TpFinal_Progra3.repositories.ObraRepository;
import TpFinal_Progra3.services.interfaces.ObraServiceInt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ObraService implements ObraServiceInt {

    private final ObraRepository obraRepository;
    private final EstudioArqRepository estudioArqRepository;
    private final ObraMapper obraMapper;

    public ObraService(ObraRepository obraRepository, EstudioArqRepository estudioArqRepository, ObraMapper obraMapper) {
        this.obraRepository = obraRepository;
        this.estudioArqRepository = estudioArqRepository;
        this.obraMapper = obraMapper;
    }
    //FALTA PODER GENERAR LOS ESTUDIOS SI NO EXISTEN AL CREAR LA OBRA

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
