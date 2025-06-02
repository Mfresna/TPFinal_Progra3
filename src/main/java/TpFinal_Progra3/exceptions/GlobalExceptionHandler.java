package TpFinal_Progra3.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailNoEnviadoException.class)
    public ResponseEntity<String> handlerEnvioEmail(EmailNoEnviadoException e) {
        if(e.getStatus() != null){
            return ResponseEntity.status(e.getStatus()).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
    }

    @ExceptionHandler(CoordenadaException.class)
    public ResponseEntity<String> handlerCoordenadasinvalidas(CoordenadaException e) {
        if(e.getStatus() != null){
            return ResponseEntity.status(e.getStatus()).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

}
