package TpFinal_Progra3.services.implementacion;

import TpFinal_Progra3.exceptions.NotFoundException;
import TpFinal_Progra3.model.DTO.EstudioArqDTO;
import TpFinal_Progra3.model.entities.EstudioArq;
import TpFinal_Progra3.model.mappers.implementacion.EstudioArqMapper;
import TpFinal_Progra3.repositories.EstudioArqRepository;
import TpFinal_Progra3.services.interfaces.EstudioArqServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstudioArqService implements EstudioArqServiceInterface {

    private final EstudioArqRepository estudioArqRepository;
    private final EstudioArqMapper estudioArqMapper;

    @Override
    public EstudioArqDTO crearEstudio(EstudioArqDTO dto) {
        EstudioArq estudioGuardado = estudioArqRepository.save(estudioArqMapper.mapEstudio(dto));
        return estudioArqMapper.mapDTO(estudioGuardado);
    }

    @Override
    public EstudioArqDTO obtenerEstudio(Long id) {
        return estudioArqRepository.findById(id)
                .map(estudioArqMapper::mapDTO)
                .orElseThrow(() -> new NotFoundException("Estudio no encontrado con ID: " + id));
    }

    @Override
    public List<EstudioArqDTO> listarEstudios() {
        return estudioArqRepository.findAll()
                .stream()
                .map(estudioArqMapper::mapDTO)
                .toList();
    }

    @Override
    public boolean eliminarEstudio(Long id) {
        if (!estudioArqRepository.existsById(id)) {
            throw new NotFoundException("Estudio no encontrado.");
        }
        estudioArqRepository.deleteById(id);
        return true;
    }
}
