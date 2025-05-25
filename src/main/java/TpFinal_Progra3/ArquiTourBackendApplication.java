package TpFinal_Progra3;

import TpFinal_Progra3.config.DotEnvConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ArquiTourBackendApplication {

	public static void main(String[] args) {

		DotEnvConfig.load();
		SpringApplication.run(ArquiTourBackendApplication.class, args);
	}

}
