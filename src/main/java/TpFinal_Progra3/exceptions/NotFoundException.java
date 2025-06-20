package TpFinal_Progra3.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

    public class NotFoundException extends ResponseStatusException {
    public NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
        public NotFoundException(HttpStatus status, String mensaje) {
            super(status, mensaje);
        }
}
