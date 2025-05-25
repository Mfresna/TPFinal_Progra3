package TpFinal_Progra3.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notificaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //NO SE USA RELACIONES JPA PARA NO SOBRECARGAR EL OBJETO NOTIFICACION
    private Long idEmisor; //PK de usuario Emisor
    private Long idReceptor; //PK de usuario Receptor

    private String mensaje;
    private LocalDateTime fecha;
    private Boolean leido;
}