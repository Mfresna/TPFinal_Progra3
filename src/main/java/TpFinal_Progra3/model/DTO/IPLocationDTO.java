package TpFinal_Progra3.model.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IPLocationDTO {
    //Recibe los Datos del JSON del service IPLocationService
    private String ip;
    private double latitud;
    private double longitud;
}
