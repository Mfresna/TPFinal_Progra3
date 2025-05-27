package TpFinal_Progra3;

import TpFinal_Progra3.config.DotEnvConfig;
import TpFinal_Progra3.security.services.JwtService;
import TpFinal_Progra3.services.EmailService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmailSenderTest {

    @Autowired
    private EmailService enviarmail;

    @Test
    void enviarMailTest() {
        enviarmail.mailResetPass("afuentes0491@gmail.com","ESTE ES UN TOKEN");
        System.out.println("HOLA");
    }

}
