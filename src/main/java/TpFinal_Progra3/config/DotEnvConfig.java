package TpFinal_Progra3.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class DotEnvConfig {

    //Como es un bloque static en una clase Configuration se ejecute antes que el resto
    static {

        Dotenv dotenv = Dotenv.load();

        //Carga las Variables en el sistema
        System.setProperty("spring.datasource.url", Objects.requireNonNull(dotenv.get("DB_URL")));
        System.setProperty("spring.datasource.username", Objects.requireNonNull(dotenv.get("DB_USERNAME")));
        System.setProperty("spring.datasource.password", Objects.requireNonNull(dotenv.get("DB_PASSWORD")));

        //SMTP-Google
        System.setProperty("spring.mail.username", Objects.requireNonNull(dotenv.get("EMAIL")));
        System.setProperty("spring.mail.password", Objects.requireNonNull(dotenv.get("EMAIL_PASSWORD")));

        //JWT
        System.setProperty("jwt.secret", Objects.requireNonNull(dotenv.get("JWT_SECRET")));

        //UbicacionIP
        System.setProperty("ip.location.api.key", Objects.requireNonNull(dotenv.get("IP_LOCATION_KEY")));
    }
}
