package TpFinal_Progra3.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notificaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //NO SE USA RELACIONES JPA PARA NO SOBRECARGAR EL OBJETO NOTIFICACION
    @Column(nullable = false)
    private Long idEmisor; //PK de usuario Emisor

    @Column(nullable = false)
    private Long idReceptor; //PK de usuario Receptor

    private String mensaje;
    private LocalDateTime fecha;

    @Column(nullable = false)
    private Boolean isLeido = false;
}