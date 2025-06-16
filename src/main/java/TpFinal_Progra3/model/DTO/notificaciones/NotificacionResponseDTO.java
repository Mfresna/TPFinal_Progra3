package TpFinal_Progra3.model.DTO.notificaciones;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class NotificacionResponseDTO {
    private Long id;

    private Long emisorId;
    private String emisorEmail;

    private Long receptorId;
    private String receptorEmail;

    private String mensaje;
    private LocalDateTime fecha;

    private Boolean isLeido;
}