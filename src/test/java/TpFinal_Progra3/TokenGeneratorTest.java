package TpFinal_Progra3;

import TpFinal_Progra3.model.DTO.UsuarioBasicoDTO;
import TpFinal_Progra3.security.services.JwtService;
import TpFinal_Progra3.services.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TokenGeneratorTest {

    @Autowired
    private JwtService jwt;

    @Test
    void generadorDeToken() {
        String token = jwt.generarToken(UsuarioBasicoDTO.builder()
                .id(5L)
                .email("matias@gmail.com")
                .nombre("Matias")
                .apellido("Fresnadillo")
                .isActivo(true)
                .build());

        System.out.println(token);

        System.out.println(jwt.extractUsername(token));
    }




}
