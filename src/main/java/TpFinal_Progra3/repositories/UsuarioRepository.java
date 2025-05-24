package TpFinal_Progra3.repositories;

import TpFinal_Progra3.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Buscar por email
    Optional<Usuario> findByEmail(String email);

    // Buscar por nombre
    List<Usuario> findByName(String name);

    // Buscar por apellido
    List<Usuario> findByLastName(String lastName);

    // Buscar por ID
    Optional<Usuario> findById(Long id);
}