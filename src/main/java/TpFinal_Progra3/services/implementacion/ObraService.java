package TpFinal_Progra3.services.implementacion;

import TpFinal_Progra3.exceptions.NotFoundException;
import TpFinal_Progra3.model.DTO.ObraDTO;
import TpFinal_Progra3.model.entities.EstudioArq;
import TpFinal_Progra3.model.entities.Obra;
import TpFinal_Progra3.model.mappers.implementacion.ObraMapper;
import TpFinal_Progra3.repositories.EstudioArqRepository;
import TpFinal_Progra3.repositories.ObraRepository;
import TpFinal_Progra3.services.interfaces.ObraServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ObraService implements ObraServiceInterface {

    private final ObraRepository obraRepository;
    private final EstudioArqRepository estudioArqRepository;
    private final ObraMapper obraMapper;

    @Override
    public ObraDTO crearObra(ObraDTO dto) {
        EstudioArq estudio = estudioArqRepository.findById(dto.getEstudioId())
                .orElseThrow(() -> new NotFoundException("Estudio no encontrado"));

        Obra obraGuardada = obraRepository.save(obraMapper.mapObra(dto,estudio));

        return obraMapper.mapDTO(obraGuardada);
    }

    @Override
    public ObraDTO obtenerObra(Long id) {
        return obraRepository.findById(id)
                .map(obraMapper::mapDTO)
                .orElseThrow(() -> new NotFoundException("Obra no encontrada con ID: " + id));
    }

    @Override
    public List<ObraDTO> listarObras() {
        return obraRepository.findAll()
                .stream()
                .map(obraMapper::mapDTO)
                .toList();
    }

    @Override
    public boolean eliminarObra(Long id) throws NotFoundException {
        if (!obraRepository.existsById(id)) {
            throw new NotFoundException("Obra no encontrada.");
        }
        obraRepository.deleteById(id);
        return true;
    }
}
