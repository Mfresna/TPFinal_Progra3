package TpFinal_Progra3.repositoryTesters;
import TpFinal_Progra3.config.DotEnvConfig;
import TpFinal_Progra3.entities.Usuario;
import TpFinal_Progra3.enums.RolUsuario;
import TpFinal_Progra3.repositories.UsuarioRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UsuarioRepositoryTest {

    @BeforeAll
    static void setup() {
        DotEnvConfig.load();
    }// Cargar variables de entorno antes de los test

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    void testGuardarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setEmail("test@email.com");
        usuario.setPassword("SecurePass123");
        usuario.setNombre("Agustina");
        usuario.setApellido("Gomez");
        usuario.setRol(RolUsuario.ROL_ARQUITECTO);
        usuario.setDescripcion("Arquitecta con experiencia en diseño urbano");
        usuario.setFechaNacimiento(LocalDate.of(1990, 5, 20));
        usuario.setIsActivo(true);

        usuarioRepository.save(usuario);
        usuarioRepository.flush(); // Forzar escritura en la BD

        System.out.println("Usuario guardado: " + usuario);
    }

    @Test
    void testBuscarPorEmail() {
        Optional<Usuario> encontrado = usuarioRepository.findByEmail("test@email.com");

        // Verificar si el usuario está presente y mostrar sus datos
        encontrado.ifPresent(usuario -> {
            System.out.println("Usuario encontrado:");
            System.out.println("ID: " + usuario.getId());
            System.out.println("Nombre: " + usuario.getNombre());
            System.out.println("Apellido: " + usuario.getApellido());
            System.out.println("Email: " + usuario.getEmail());
            System.out.println("Rol: " + usuario.getRol());
            System.out.println("Activo: " + usuario.getIsActivo());
        });

        // Validaciones
        assertTrue(encontrado.isPresent(), "El usuario no se encontró en la BD");
        assertEquals("Agustina", encontrado.get().getNombre());
        assertEquals("Gomez", encontrado.get().getApellido());
        assertEquals(RolUsuario.ROL_ARQUITECTO, encontrado.get().getRol());
    }

    @Test
    void testBuscarUsuariosActivos() {
        // Crear usuarios
        Usuario usuarioActivo = new Usuario();
        usuarioActivo.setEmail("activo@email.com");
        usuarioActivo.setPassword("SecurePass123");
        usuarioActivo.setNombre("Laura");
        usuarioActivo.setApellido("Fernandez");
        usuarioActivo.setIsActivo(true);
        usuarioActivo.setRol(RolUsuario.ROL_ARQUITECTO); // ✅ Rol asignado correctamente
        usuarioRepository.save(usuarioActivo);

        Usuario usuarioInactivo = new Usuario();
        usuarioInactivo.setEmail("inactivo@email.com");
        usuarioInactivo.setPassword("SecurePass123");
        usuarioInactivo.setNombre("Carlos");
        usuarioInactivo.setApellido("Perez");
        usuarioInactivo.setIsActivo(false);
        usuarioInactivo.setRol(RolUsuario.ROL_ADMINISTRADOR); // ✅ Rol asignado correctamente
        usuarioRepository.save(usuarioInactivo);

        usuarioRepository.flush(); // Forzar escritura en la BD

        // Buscar solo usuarios activos
        List<Usuario> usuariosActivos = usuarioRepository.findByIsActivoTrue();
        // Imprimir cada usuario encontrado
        System.out.println("Usuarios activos encontrados:");
        usuariosActivos.forEach(usuario -> {
            System.out.println("ID: " + usuario.getId());
            System.out.println("Nombre: " + usuario.getNombre());
            System.out.println("Apellido: " + usuario.getApellido());
            System.out.println("Email: " + usuario.getEmail());
            System.out.println("Rol: " + usuario.getRol());
            System.out.println("Activo: " + usuario.getIsActivo());
            System.out.println("-----------------------------");
        });


        // Validaciones
        assertFalse(usuariosActivos.isEmpty(), "No se encontraron usuarios activos");
        assertEquals(1, usuariosActivos.size(), "Se encontró más de un usuario activo");
        assertEquals("Laura", usuariosActivos.get(0).getNombre());
        assertEquals(RolUsuario.ROL_ARQUITECTO, usuariosActivos.get(0).getRol()); // ✅ Validando que el rol sea correcto
    }

   /* @Test
    void testBuscarUsuariosPorEstudioId() {
        // Crear estudio
        EstudioArquitectura estudio = new EstudioArquitectura();
        estudio.setNombre("Estudio Moderna");
        estudio = estudioRepository.save(estudio);

        // Crear usuarios y asignarlos al estudio
        Usuario usuario1 = new Usuario();
        usuario1.setEmail("usuario1@email.com");
        usuario1.setPassword("SecurePass123");
        usuario1.setNombre("Agustina");
        usuario1.setApellido("Gomez");
        usuario1.setRol(RolUsuario.ROL_ARQUITECTO);
        usuario1.setIsActivo(true);
        usuario1.getEstudios().add(estudio); // Asignar estudio
        usuarioRepository.save(usuario1);

        Usuario usuario2 = new Usuario();
        usuario2.setEmail("usuario2@email.com");
        usuario2.setPassword("SecurePass123");
        usuario2.setNombre("Carlos");
        usuario2.setApellido("Perez");
        usuario2.setRol(RolUsuario.ROL_ARQUITECTO);
        usuario2.setIsActivo(true);
        usuario2.getEstudios().add(estudio); // Asignar estudio
        usuarioRepository.save(usuario2);

        usuarioRepository.flush(); // Forzar escritura en BD

        // Buscar usuarios que pertenecen al estudio creado
        List<Usuario> usuariosEstudio = usuarioRepository.findByEstudioId(estudio.getId());

        // 📌 Imprimir detalles de los usuarios encontrados
        System.out.println("Usuarios en el estudio " + estudio.getNombre() + ":");
        usuariosEstudio.forEach(usuario -> System.out.println("Nombre: " + usuario.getNombre() + ", Email: " + usuario.getEmail()));

        // Validaciones
        assertFalse(usuariosEstudio.isEmpty(), "No se encontraron usuarios en el estudio");
        assertEquals(2, usuariosEstudio.size(), "La cantidad de usuarios es incorrecta");
        assertEquals("Agustina", usuariosEstudio.get(0).getNombre());
        assertEquals("Carlos", usuariosEstudio.get(1).getNombre());
    }
    */


}