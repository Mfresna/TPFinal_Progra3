package TpFinal_Progra3.security.services;

import TpFinal_Progra3.model.DTO.UsuarioBasicoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/// MANEJADOR DE TOKENS
@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String jwtSecretKey;

    @Value("${jwt.expiration}")
    private Long jwtVencimiento;

    //Genera el token con el id y el mail, hace uso de <constructorToken>.
    public String generarToken(UsuarioBasicoDTO usuario) {

        //convierte el DTO en un MAP
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> dtoMap = objectMapper.convertValue(usuario, Map.class);

        return constructorToken(dtoMap,usuario.getId().toString(), jwtVencimiento);
    }

    //Construye un token
    private String constructorToken(Map<String, Object> atributos, String identificador, long expiration){
        return Jwts
                .builder()
                .setClaims(atributos)
                .setSubject(identificador)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    //Extrae una claim especifica
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    //Extrae todos los claims
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    //Obtiene la KEY para firmar el token.
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
