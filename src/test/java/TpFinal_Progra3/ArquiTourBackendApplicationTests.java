package TpFinal_Progra3;

import TpFinal_Progra3.config.DotEnvConfig;
import TpFinal_Progra3.enums.CategoriaObra;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ArquiTourBackendApplicationTests {

	@Test
	void contextLoads() {
		DotEnvConfig.load();
		System.out.println("HOLA");
	}

}
