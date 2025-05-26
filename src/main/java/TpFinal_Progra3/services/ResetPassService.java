package TpFinal_Progra3.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ResetPassService {

    public String generarToken(Long userId) {
        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) //El Token dura 24hs
                .signWith(SignatureAlgorithm.HS256, )
                .compact();
    }

    public Long desencriptarToken(String token, String emailUsuario) {
        Claims claims = Jwts.parser()
                .setSigningKey()
                .parseClaimsJws(token)
                .getBody();
        return Long.parseLong(claims.getSubject()); // Retorna el ID del usuario si el token es válido
    }
}
