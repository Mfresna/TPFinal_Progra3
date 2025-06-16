package TpFinal_Progra3.controllers;

import TpFinal_Progra3.model.DTO.notificaciones.NotificacionDTO;
import TpFinal_Progra3.model.DTO.notificaciones.NotificacionResponseDTO;
import TpFinal_Progra3.services.implementacion.NotificacionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@PreAuthorize("isAuthenticated()")
@RequestMapping("/notificaciones")
@RequiredArgsConstructor
public class NotificacionController {

    private final NotificacionService notificacionService;

    @PostMapping
    public ResponseEntity<NotificacionResponseDTO> crearNotificacion(HttpServletRequest request,
                                                                     @RequestBody @Valid NotificacionDTO dto) {
        return ResponseEntity.ok(notificacionService.crearNotificacion(request, dto));
    }

    @GetMapping("/recibidas")
    public ResponseEntity<List<NotificacionResponseDTO>> notificacionesRecibidas(HttpServletRequest request,
                                                                           @RequestParam(required = false) Boolean isLeido){
        System.out.println(isLeido);
        return ResponseEntity.ok(notificacionService.obtenerRecibidas(request,isLeido));
    }

    @GetMapping("/enviadas")
    public ResponseEntity<List<NotificacionResponseDTO>> notificacionesEnviadas(HttpServletRequest request) {
        return ResponseEntity.ok(notificacionService.obtenerEnviadas(request));
    }

}
