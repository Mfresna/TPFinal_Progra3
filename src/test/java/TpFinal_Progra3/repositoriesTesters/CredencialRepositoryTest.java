package TpFinal_Progra3.repositoriesTesters;

import TpFinal_Progra3.security.model.entities.Credencial;
import TpFinal_Progra3.security.model.enums.RolUsuario;
import TpFinal_Progra3.security.repositories.CredencialRepository;
import TpFinal_Progra3.security.repositories.RolRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.Set;

/**
 * <b>TEST PASSED OK</b>
 */
@SpringBootTest
public class CredencialRepositoryTest {

    @Autowired
    private CredencialRepository credencialRepository;

    @Autowired
    private RolRepository rolRepository;

    /**
     * VERIFICAR QUE EXISTA UN ROL_USUARIO EN LA DB
     */
    @Test
    void testGuardarCredencial() {

        Credencial credencial = Credencial.builder()
                .email("test@gmail.com")
                .password("test")
                .roles(Set.of(rolRepository.findByRol(RolUsuario.ROL_USUARIO).get()))
                .build();

        credencialRepository.save(credencial);
    }

    @Test
    void testBuscarPorEmail() {
        Optional<Credencial> credencial = credencialRepository.findByEmail("test@gmail.com");
        credencial.ifPresent(System.out::println);
    }

    @Test
    void testExisteEmail() {
        System.out.println("Existe el Email: " + credencialRepository.existsByEmail("test@gmail.com"));
    }


}
