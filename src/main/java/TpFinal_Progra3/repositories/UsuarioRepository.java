package TpFinal_Progra3.repositories;

import TpFinal_Progra3.model.entities.Usuario;
import TpFinal_Progra3.security.model.enums.RolUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Buscar usuario por ID
    Optional<Usuario> findById(Long id);

    // Buscar usuario por correo electrónico
    Optional<Usuario> findByEmail(String email);

    // Buscar usuarios por nombre
    List<Usuario> findByNombreContainingIgnoreCase(String nombre);

    // Buscar usuarios por apellido
    List<Usuario> findByApellidoContainingIgnoreCase(String apellido);

    // Buscar usuarios activos
    List<Usuario> findByIsActivoTrue();

    // Buscar usuarios inactivos
    List<Usuario> findByIsActivoFalse();

    // Obtener todos los usuarios con un rol específico
    List<Usuario> findByRol(RolUsuario rol);

    // Buscar usuarios que pertenezcan a un estudio específico
    @Query("SELECT u FROM Usuario u JOIN u.estudios e WHERE e.id = :estudioId")
    List<Usuario> findByEstudioId(@Param("estudioId") Long estudioId);
}