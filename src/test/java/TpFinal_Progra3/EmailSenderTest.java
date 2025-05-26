package TpFinal_Progra3;

import TpFinal_Progra3.config.DotEnvConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmailSenderTest {

    @BeforeAll
    static void setup() {
        DotEnvConfig.load();
    }

    @Autowired
    private EmailService enviarmail;

    @Test
    void enviarMailTest() {
        enviarmail.mailResetPass("afuentes0491@gmail.com","ESTE ES UN TOKEN");
        System.out.println("HOLA");
    }

}
