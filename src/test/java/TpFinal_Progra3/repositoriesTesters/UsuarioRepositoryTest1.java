//package TpFinal_Progra3.repositoriesTesters;
//
//import TpFinal_Progra3.model.entities.Usuario;
//import TpFinal_Progra3.security.model.entities.Credencial;
//import TpFinal_Progra3.security.model.entities.Rol;
//import TpFinal_Progra3.security.model.enums.RolUsuario;
//import TpFinal_Progra3.repositories.UsuarioRepository;
//import TpFinal_Progra3.security.repositories.CredencialRepository;
//import TpFinal_Progra3.security.repositories.RolRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import java.time.LocalDate;
//import java.util.Set;
//import static org.assertj.core.api.Assertions.assertThat;
//
//@DataJpaTest
//public class UsuarioRepositoryTest {
//
//    @Autowired
//    private UsuarioRepository usuarioRepository;
//
//    @Autowired
//    private CredencialRepository credencialRepository;
//
//    @Autowired
//    private RolRepository rolRepository;
//
//    @Test
//    public void testGuardarYBuscarUsuarioPorEmail() {
//        // Crear y guardar Rol
//        Rol rol = Rol.builder().rol(RolUsuario.ROL_USUARIO).build();
//        rol = rolRepository.save(rol);
//
//        // Crear y guardar Credencial
//        Credencial credencial = Credencial.builder()
//                .email("test@example.com")
//                .password("clave123")
//                .roles(Set.of(rol))
//                .build();
//        credencial = credencialRepository.save(credencial);
//
//        // Crear y guardar Usuario
//        Usuario usuario = Usuario.builder()
//                .nombre("Ana")
//                .apellido("Pérez")
//                .email("test@example.com")
//                .fechaNacimiento(LocalDate.of(1995, 5, 15))
//                .isActivo(true)
//                .credencial(credencial)
//                .build();
//        usuarioRepository.save(usuario);
//
//        // Buscar por email
//        var resultado = usuarioRepository.findByEmail("test@example.com");
//
//        // Validaciones
//        assertThat(resultado).isPresent();
//        assertThat(resultado.get().getNombre()).isEqualTo("Ana");
//        assertThat(resultado.get().getCredencial().getEmail()).isEqualTo("test@example.com");
//    }
//}