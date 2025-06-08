package TpFinal_Progra3.security;

import TpFinal_Progra3.security.model.DTO.AuthRequest;
import TpFinal_Progra3.security.model.DTO.AuthResponse;
import TpFinal_Progra3.security.services.AuthService;
import TpFinal_Progra3.security.services.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    public AuthController(AuthService authService, JwtService jwtService) {
        this.authService = authService;
        this.jwtService = jwtService;
    }

    //Recibe por PostMan un AuthRequest con USR y PASS
    @PostMapping()
    public ResponseEntity<AuthResponse> authenticateUser(@RequestBody AuthRequest authRequest){
        UserDetails usuario = authService.authenticate(authRequest);
        String token = jwtService.generarToken(usuario);
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
