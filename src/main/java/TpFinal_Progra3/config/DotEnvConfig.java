package TpFinal_Progra3.config;

import io.github.cdimascio.dotenv.Dotenv;

public class DotEnvConfig {
    public static void load() {
        Dotenv dotenv = Dotenv.load();

        //Carga las Variables en el sistema
        System.setProperty("spring.datasource.url", dotenv.get("DB_URL"));
        System.setProperty("spring.datasource.username", dotenv.get("DB_USERNAME"));
        System.setProperty("spring.datasource.password", dotenv.get("DB_PASSWORD"));

    }
}
