/*package TpFinal_Progra3.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class ResetPassService {


    private static final String SECRET_KEY = "TuClaveSecretaSegura"; // ⚠️ Mejor usar una variable de entorno

    // 🔹 Generar Token con duración de 24h
    public String generarToken(Long userId) {
        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setIssuedAt(new Date())  // Fecha de emisión
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // Expiración en 24h
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)  // Firma con clave secreta
                .compact();
    }

    // 🔹 Validar y obtener datos del Token
    public Long desencriptarToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)  // Validamos con la misma clave secreta
                    .parseClaimsJws(token)
                    .getBody();

            return Long.parseLong(claims.getSubject());  // Devuelve el ID del usuario si el token es válido
        } catch (Exception e) {
            return null; // Si el token no es válido o expiró
        }
    }
}

 */
