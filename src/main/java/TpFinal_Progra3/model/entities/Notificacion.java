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

    @ManyToOne
    @JoinColumn(name = "emisor_id", nullable = false)
    private Usuario emisor;

    @ManyToOne
    @JoinColumn(name = "receptor_id", nullable = false)
    private Usuario receptor;

    private String mensaje;
    private LocalDateTime fecha;

    @Builder.Default //Hace que leido se defina false si no se especifica en builder
    @Column(nullable = false)
    private Boolean isLeido = false;
}