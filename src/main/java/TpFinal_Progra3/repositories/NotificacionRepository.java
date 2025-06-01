package TpFinal_Progra3.repositories;

import TpFinal_Progra3.model.entities.Notificacion;
import TpFinal_Progra3.model.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {

    List<Notificacion> findByReceptor(Usuario receptor);

    List<Notificacion> findByReceptorAndIsLeidoFalse(Usuario receptor);

    List<Notificacion> findByEmisor(Usuario emisor);
}
