package TpFinal_Progra3.services;

import TpFinal_Progra3.model.DTO.IPLocationDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Optional;

@Service
public class IPLocationService {

    @Value("${ip.location.api.key}")
    private String apiKey;

    private final WebClient webClient = WebClient.create("http://api.ipstack.com");

    // Extrae la IP del cliente desde la solicitud HTTP
    public String obtenerIpCliente(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();   //Devuelve la ip del punto de salida (no siempre es la del cliente)
        } else {
            ip = ip.split(",")[0];  //ip -> 192.168.20.5 , 10.0.0.1 (Toma al primer IP que es del cliente)
        }
        return ip;
    }

    //Devuelve un DTO con la ubicacion del cliente
    public Mono<IPLocationDTO> obtenerUbicacion(String ip) {
            return webClient.get().uri(uriBuilder -> uriBuilder
                            .path("/" + ip)
                            .queryParam("access_key", apiKey)
                            .build())
                    .retrieve()
                    .bodyToMono(Map.class)
                    .map(json ->{
                        System.out.println(json);
                        return IPLocationDTO.builder()
                                .ip(ip)
                                //.latitud((Double) json.get("latitude"))
                                //.longitud((Double) json.get("longitude"))
                                .build();
                    });
            //Si se lanza un error devuelve un optional vacio

    }

    private IPLocationDTO mapearAUbicacion(String ip, Map<String, Object> json) {
        return IPLocationDTO.builder()
                .ip(ip)
                .latitud(extraerDouble(json.get("latitude")))
                .longitud(extraerDouble(json.get("longitude")))
                .build();
    }

    private Double extraerDouble(Object valor) {
        return valor instanceof Number ? ((Number) valor).doubleValue() : null;
    }

}
